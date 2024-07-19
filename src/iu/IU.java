/**
 * Representa la interfaz del juego del Cinquillo-Oro, en este proyecto va a ser una entrada/salida en modo texto
 * Se recomienda una implementación modular.
 */
package src.iu;

import src.core.Jugador;
import src.core.Mesa;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class IU {

    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String GREEN = "\033[0;32m";
    public static final String RED = "\033[0;31m";
    public static final String PURPLE = "\033[0;35m";
    
    private final Scanner teclado;

    public IU() {
        teclado = new Scanner(System.in).useDelimiter("\r?\n");
    }

    /**
     * Lee un num. de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El num., como entero
     */
    public int leeNum(String msg) {
        do {
            System.out.print(msg);

            try {
                return teclado.nextInt();
            } catch (InputMismatchException exc) {
                teclado.next();
                System.out.println("Entrada no válida. Debe ser un entero.");
            }
        } while (true);
    }

    public String leeString(String msg) {
        System.out.print(msg);
        return teclado.next();
    }

    public String leeString(String msg, Object... args) {
        System.out.printf(msg, args);
        return teclado.next();
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void mostrarMensaje(String msg, Object... args) {
        System.out.printf(msg, args);
    }

    public Collection <String> pedirDatosJugadores() {

        String nombre;
        Collection <String> jugadores = new LinkedList<>();
        int numJugadores = -1;
        do {
            numJugadores = leeNum("Introduce el número de jugadores (3 / 4): ");
            mostrarMensaje("\n");
        } while (numJugadores < 3 || numJugadores > 4);

        for (int i = 1; i <= numJugadores; i++) {

            do {
                nombre = leeString("Introduce el nombre del jugador " + i + ": ");
                mostrarMensaje("\n");
                if (!nombre.isEmpty()) {
                    jugadores.add(nombre);

                }
            } while (nombre.isEmpty());
        }

        return jugadores;
    }
    
    public int pedirCarta(Jugador jugador, Mesa mesa) {
        int cartanum;
        do {
            mostrarMensaje("\n");
            cartanum = leeNum(IU.GREEN + ">¿Que carta de tu mano quieres colocar en la mesa? (0-" + (jugador.getNumCartas() - 1) + "): "); //Desde 0 a numCartas-1
            if (cartanum < 0 || cartanum > jugador.getNumCartas() - 1 || mesa.comprobarCarta(jugador.getMano().get(cartanum)) == false) {
                System.err.println(IU.RED + "ERROR. Carta no valida. Ingrese de nuevo");
            }

        } while (cartanum < 0 || cartanum > jugador.getNumCartas() - 1 || mesa.comprobarCarta(jugador.getMano().get(cartanum)) == false);
        
        return cartanum;
    }

    public void mostrarJugador(Jugador jugador) {
        System.out.println(jugador.toString());
    }

    public void mostrarJugadores(Collection <Jugador> jugadores) {
        for (Jugador jugadore : jugadores) {
            mostrarJugador(jugadore);

        }
    }

}
