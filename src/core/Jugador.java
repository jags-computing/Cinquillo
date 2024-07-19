/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */
package src.core;

import java.util.LinkedList;
import java.util.List;

public class Jugador {

    private String nombre;
    private List <Carta> mano;
    private int puntos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumCartas() {
        return mano.size();
    }

    public void insertar(Carta c) {
        mano.add(c);
    }

    public Carta retirar(int pos) {
        Carta c = mano.get(pos);
        mano.remove(pos);
        return c;
    }

    public List <Carta> getMano() {
        return mano;
    }

    public boolean comprobarMano(Mesa mesa) {
        boolean esValido = false;
        for (Carta carta : this.mano) {
            if (mesa.comprobarCarta(carta) == true) {
                return true;
            }
        }
        return esValido;

    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public String mostrarMano() {
        StringBuilder toret = new StringBuilder();
        for (int i = 0; i < mano.size(); i++) {
            toret.append("(").append(i).append(")").append(". ").append(mano.get(i)).append("\n");
        }
        return toret.toString();

    }
    
    public void vaciaMano() {
        mano.clear();
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append("--").append(nombre).append("--").append("\n");
        for(int i = 0; i < mano.size(); i++) {
            toret.append(mano.get(i));
        }
        return toret.toString();
    }
}
