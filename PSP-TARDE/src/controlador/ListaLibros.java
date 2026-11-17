package controlador;

import java.util.ArrayList;
import java.util.Comparator;
import modelo.Libro;


public class ListaLibros extends ArrayList<Libro> {

    public static final float IVA = 0.04f;

    private int actual;

    public ListaLibros() {
        super();
        this.actual = -1;
    }


    public boolean esVacia() {
        return this.isEmpty();
    }

    public boolean esPrimero() {
        return esVacia() || actual == 0;
    }

    public boolean esUltimo() {
        return esVacia() || actual == this.size() - 1;
    }


    public void avanzar() {
        if (!esUltimo()) {
            actual++;
        }
    }

    public void retroceder() {
        if (!esPrimero()) {
            actual--;
        }
    }

    public void irAlPrimero() {
        if (!esVacia()) {
            actual = 0;
        }
    }

    public void irAlUltimo() {
        if (!esVacia()) {
            actual = this.size() - 1;
        }
    }


    public void anadirLibro(Libro libro) {
        if (libro == null) {
            return;
        }
        this.add(libro);
        actual = this.size() - 1;
    }

   
    public void borrarLibro() {
        if (esVacia()) {
            return;
        }
        this.remove(actual);                 // remove(int) de ArrayList
        if (esVacia()) {
            actual = -1;
        } else if (actual >= this.size()) {
            actual = this.size() - 1;
        }
    }

    public Libro obtenerLibroActual() {
        if (esVacia()) {
            return null;
        }
        return this.get(actual);
    }


    public float calcularIVA() {
        Libro l = obtenerLibroActual();
        return l == null ? 0f : l.getPrecio() * IVA;
    }

    public float precioFinal() {
        Libro l = obtenerLibroActual();
        return l == null ? 0f : l.getPrecio() + calcularIVA();
    }


    public void ordenarPorTitulo() {
        this.sort(Comparator.comparing(Libro::getTitulo, String.CASE_INSENSITIVE_ORDER));
        irAlPrimero();
    }


    public boolean existeIsbn(int isbn) {
        for (Libro l : this) {
            if (l.getIsbn() == isbn) {
                return true;
            }
        }
        return false;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        if (actual >= 0 && actual < this.size()) {
            this.actual = actual;
        }
    }
}