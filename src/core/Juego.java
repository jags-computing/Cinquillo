/**
 * Representa el juego del Cinquillo-Oro, con sus reglas
 * Se recomienda una implementación modular.
 */
package src.core;

import src.iu.IU;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Juego {

    private final IU iu;
    
    private final int puntosPartida = 4;
    
    private int puntosAsDeOro = 2;
    
    private Baraja miBaraja;
    
    private Collection<String> jugadores;
    
    private List<Jugador> listaJugadores;
    
    private int jugadorActual;
    
    private int cartasRepartir;
    
    private int numJugadores;
    
    private Mesa mesaJuego;

    
    
    public Juego(IU iu) {
        this.iu = iu;
        
    }

   

    public void jugar() {

        iu.mostrarMensaje("¡Bienvenidos jugadores al...!\n");
        
        titulo();

        jugadores = iu.pedirDatosJugadores();

         //Entero que podrá ser 12 o 16 en función del número de jugadores y representa cuantas cartas se le repartirán a cada jugador   

        numJugadores = jugadores.size();
        
        cartasRepartir = 48 / numJugadores;

        jugadorActual = (int) (Math.random() * numJugadores);
        
        listaJugadores = insertarJugadores();
        
        mesaJuego = new Mesa();
        
        
        
        boolean fin = false;
        
        do {
            miBaraja = new Baraja();
            miBaraja.barajar();
            vaciarManos();
            repartirCartas();
            iu.mostrarMensaje(IU.GREEN + "El jugador que inicia la partida es: " + this.listaJugadores.get(jugadorActual).getNombre() + "." + "\n");
            muestraManos();
            partida();
            puntosAsDeOro += 2;
            if(mesaJuego.asOrosColocado()) {
                fin = true;
            }
            mesaJuego.vaciaMesa();
                
        } while(!fin);
        
        resultadosFinales();
    }
    


    //Métodos auxiliares

    public void muestraManos() {
        for (Jugador jugador : this.listaJugadores) {
                    iu.mostrarMensaje(IU.PURPLE + "--" + jugador.getNombre() + "--" + "\n");
                    iu.mostrarMensaje(jugador.mostrarMano());
        }
    }
    
    public void vaciarManos() {
        for (Jugador jugador : this.listaJugadores) {
                jugador.vaciaMano();
        }
    }

    public List <Jugador> insertarJugadores() {
        //Se añaden ordenadamente los jugadores a la lista
        List <Jugador> nuevoJugador = new ArrayList<>();

        for (String jugador : this.jugadores) {
            nuevoJugador.add(new Jugador(jugador));
        }
        
        return nuevoJugador;
    }
    
    public void repartirCartas() {
        //Se reparten las cartas
        for (Jugador jugador : this.listaJugadores) {
            for (int i = 0; i < this.cartasRepartir; i++) {
                jugador.insertar(this.miBaraja.sacarCarta());
            }
        }
    }
    
    public void partida() {
        boolean finPartida = false;
       
        do {
            
        if (this.listaJugadores.get(this.jugadorActual).comprobarMano(this.mesaJuego) == true) {
                    colocarEnMesaMenu(this.listaJugadores.get(this.jugadorActual));
                } else {
                    iu.mostrarMensaje("\n");
                    iu.mostrarMensaje(IU.RED + "¡Vaya...! " + this.listaJugadores.get(this.jugadorActual).getNombre() + " no puede colocar cartas ¡Que pase el siguiente!");
                }

                if (this.listaJugadores.get(this.jugadorActual).getNumCartas() == 0) {
                    iu.mostrarMensaje(IU.GREEN + "¡¡¡PARTIDA TERMINADA!!!" + "\n");
                    iu.mostrarMensaje(IU.GREEN + "El ganador de esta partida es... " + "¡" + this.listaJugadores.get(this.jugadorActual).getNombre() + "!" + "\n");
                    finPartida = true;
                    this.listaJugadores.get(this.jugadorActual).setPuntos(this.listaJugadores.get(this.jugadorActual).getPuntos() + puntosPartida);
                }
                
                this.jugadorActual++;
                if (this.jugadorActual % this.numJugadores == 0) {
                    this.jugadorActual = 0;
                }
                
        
        } while(!finPartida);
        
        
    }
    
    public void resultadosFinales() {
        List <String> ganadores = new ArrayList<>();
        int puntuacionAux = 0;
        
        //for-each para comprobar puntuación máxima
        for (Jugador jugador : this.listaJugadores) {
            if (jugador.getPuntos() >= puntuacionAux) {
                puntuacionAux = jugador.getPuntos();
            }
        }

        //for-each para lista de ganadores
        for (Jugador jugador : this.listaJugadores) {
            if (jugador.getPuntos() == puntuacionAux) {
                ganadores.add(jugador.getNombre());
            }
        }

        iu.mostrarMensaje(IU.GREEN + "¡¡¡JUEGO TERMINADO!!!" + "\n");
        iu.mostrarMensaje(IU.GREEN + "GANADOR/ES: " + "\n");
        for (String ganador : ganadores) {
            iu.mostrarMensaje(IU.YELLOW_BOLD + ganador + "\n");

        }

        iu.mostrarMensaje(IU.GREEN + "PUNTUACIONES FINALES:" + "\n");

        for (Jugador jugador : this.listaJugadores) {
            iu.mostrarMensaje(jugador.getNombre() + ": " + jugador.getPuntos() + " puntos.");
        }
    }

    public void colocarEnMesa(Jugador jugador, int cartanum) {
        Carta carta = jugador.retirar(cartanum);
        this.mesaJuego.insertarCarta(carta);
        if (carta.getNumPalo() == 0 && carta.getNumero() == 1) {
            jugador.setPuntos(jugador.getPuntos() + this.puntosAsDeOro);
        }
    }

    public void colocarEnMesaMenu(Jugador jugador) {
        iu.mostrarMensaje("\n--------------------------------------------------------------------------\n");
        iu.mostrarMensaje(IU.PURPLE + "¡Hola " + jugador.getNombre() + "!\n");

        iu.mostrarMensaje(IU.GREEN + "(i) Esta es la mesa actual:" + "\n");
        iu.mostrarMensaje(this.mesaJuego.toString(), this.mesaJuego.toString() + "\n");
        iu.mostrarMensaje(IU.GREEN + "(ii) Estas son tus cartas, " + jugador.getNombre() + " :\n");
        iu.mostrarMensaje(jugador.mostrarMano());

        iu.mostrarMensaje("\n--------------------------------------------------------------------------");
        colocarEnMesa(jugador, iu.pedirCarta(jugador, this.mesaJuego));
        
        iu.mostrarMensaje("\n");
        iu.mostrarMensaje(IU.GREEN + "(i) La mesa ha quedado asi:" + "\n\n");
        iu.mostrarMensaje(this.mesaJuego.toString());
        iu.mostrarMensaje("\n\n");
        iu.mostrarMensaje(IU.GREEN + "(ii) Estas son tus cartas, " + jugador.getNombre() + " :\n");
        iu.mostrarMensaje(jugador.mostrarMano());
        iu.mostrarMensaje("\n--------------------------------------------------------------------------");

    }
    
    public void titulo() {
        iu.mostrarMensaje(IU.YELLOW_BOLD + " .d8888b.  d8b                            d8b 888 888                    888                .d88888b.                  \n" +
IU.YELLOW_BOLD + "d88P  Y88b Y8P                            Y8P 888 888                    888               d88P\" \"Y88b                 \n" +
IU.YELLOW_BOLD + "888    888                                    888 888                    888               888     888                 \n" +
IU.YELLOW_BOLD + "888        888 88888b.   .d88888 888  888 888 888 888  .d88b.        .d88888  .d88b.       888     888 888d888 .d88b.  \n" +
IU.YELLOW_BOLD + "888        888 888 \"88b d88\" 888 888  888 888 888 888 d88\"\"88b      d88\" 888 d8P  Y8b      888     888 888P\"  d88\"\"88b \n" +
IU.YELLOW_BOLD + "888    888 888 888  888 888  888 888  888 888 888 888 888  888      888  888 88888888      888     888 888    888  888 \n" +
IU.YELLOW_BOLD + "Y88b  d88P 888 888  888 Y88b 888 Y88b 888 888 888 888 Y88..88P      Y88b 888 Y8b.          Y88b. .d88P 888    Y88..88P \n" +
IU.YELLOW_BOLD + " \"Y8888P\"  888 888  888  \"Y88888  \"Y88888 888 888 888  \"Y88P\"        \"Y88888  \"Y8888        \"Y88888P\"  888     \"Y88P\"  \n" +
IU.YELLOW_BOLD + "                             888                                                                                       \n" +
IU.YELLOW_BOLD + "                             888                                                                                       \n" +
IU.YELLOW_BOLD + "                             888");
        
        iu.mostrarMensaje("\n");
    }

}
