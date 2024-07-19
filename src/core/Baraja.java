/*
* Representa la baraja española pero con 8 y 9, en total 48 cartas, 4 palos, valores de las cartas de 1 a 12. 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */
package src.core;

import java.util.Stack;


public class Baraja {

    private Stack<Carta> cartas;

    public Baraja() {
        cartas = new Stack<>();
        for (int i = 1; i <= 12; i++) {
            cartas.push(new Carta(i, "Copas"));
        }
        for (int i = 1; i <= 12; i++) {
            cartas.push(new Carta(i, "Oros"));
        }
        for (int i = 1; i <= 12; i++) {
            cartas.push(new Carta(i, "Bastos"));
        }
        for (int i = 1; i <= 12; i++) {
            cartas.push(new Carta(i, "Espadas"));
        }
    }

    public void barajar() {
        Carta[] arrayCartas = new Carta[cartas.size()];
        //Se genera un array con el mismo tamaño que la Baraja

        for (int i = 0; i < arrayCartas.length; i++) {
            arrayCartas[i] = sacarCarta();
        }
        //Se insertan las cartas de Baraja en el array, dejando este vacío

        for (int j = 0; j < arrayCartas.length; j++) {
            int numeroAleatorio = (int) (Math.random() * arrayCartas.length);
            Carta cartaTemporal = arrayCartas[j];
            arrayCartas[j] = arrayCartas[numeroAleatorio];

            arrayCartas[numeroAleatorio] = cartaTemporal;
        }
        /**
         * Dentro del array, se cambian de posición todas las cartas comenzando
         * por las situadas en el 0 hasta el final del array
         */

        for (int k = 0; k < arrayCartas.length; k++) {
            cartas.push(arrayCartas[k]);
        }
    }

    public Carta sacarCarta() {
        if (cartas.empty()== true) {
            return null;
        }
        Carta carta = cartas.pop();    
     
        return carta;
    }

}
