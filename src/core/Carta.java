/*
 * Representa una carta, formada por un número y su palo correspondiente
 */
package src.core;

public class Carta {

    
    private final int numero;
    private final String palo;
    private int numPalo;
 
    public Carta(int numero, String palo) {
        this.numero = numero;
        this.palo = palo;
        if(this.palo.equalsIgnoreCase("Oros")) {
            numPalo = 0;
        }
        if(this.palo.equalsIgnoreCase("Copas")) {
            numPalo = 1;
        }
        if(this.palo.equalsIgnoreCase("Bastos")) {
            numPalo = 2;
        }
        if(this.palo.equalsIgnoreCase("Espadas")) {
            numPalo = 3;
        }
    }

    public int getNumero() {
        return numero;
    }

    public String getPalo() {
        return palo;
    }

    public int getNumPalo() {
        return numPalo;
    }
 
    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(numero).append(" de ");
        toret.append(palo).append("\n");
        return toret.toString();
    }

}
