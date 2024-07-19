/*
* Representa la Mesa de juego. 
* Estructura: se utilizará un TAD adecuado. Piensa que hay 4 palos y de cada palo se pueden colocar cartas 
* por cualquiera de los dos extremos (un array con 4 doblescolas parece lo más adecuado). La DobleCola se comentó en clase de teoría
* Funcionalidad: saber si es posible colocar una carta en la mesa, colocar una carta en la mesa, mostrar la mesa
 */
package src.core;

import java.util.ArrayDeque;
import java.util.Deque;

public class Mesa {

    private Deque <Carta> [] mesa;

    //Constructor
    @SuppressWarnings("unchecked")
    public Mesa() {
        mesa = new Deque[4];

        for (int i = 0; i < mesa.length; i++) {
            mesa[i] = new ArrayDeque<>();
        }

    }

    public boolean comprobarCarta(Carta carta) {

        if (mesa[carta.getNumPalo()].isEmpty() && carta.getNumero() == 5) {
            return true;
        } else if (!mesa[carta.getNumPalo()].isEmpty() && (mesa[carta.getNumPalo()].getLast().getNumero() + 1 == carta.getNumero() || mesa[carta.getNumPalo()].getFirst().getNumero() - 1 == carta.getNumero())) {
            return true;
        }
        return false;
    }

    public void insertarCarta(Carta carta) {
       
        if (mesa[carta.getNumPalo()].isEmpty()) {
            if (carta.getNumero() == 5) {
                mesa[carta.getNumPalo()].add(carta);
            }

        } else {
            if (mesa[carta.getNumPalo()].getLast().getNumero() + 1 == carta.getNumero()) {
                mesa[carta.getNumPalo()].addLast(carta);
            } else if (mesa[carta.getNumPalo()].getFirst().getNumero() - 1 == carta.getNumero()) {
                mesa[carta.getNumPalo()].addFirst(carta);
            }
        }
    }

    public boolean asOrosColocado() {
        if (!mesa[0].isEmpty()) {
            if (mesa[0].peekFirst().getNumero() == 1) {
                return true;
            }
        }

        return false;
    }

    public void vaciaMesa() {
        for (Deque <Carta> palo : mesa) {
            palo.clear();
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < mesa.length; i++) {
            if (mesa[i].isEmpty()) {
                sb.append("Fila ").append(i).append(": ").append("vacia").append("\n");
            } else {
                for (Carta car : mesa[i]) {
                    sb.append(car.toString());
                }
                sb.append("\n");
            }
        }

        return sb.toString();
    }
    
    
    
}


