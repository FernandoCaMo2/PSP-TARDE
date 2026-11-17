package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Libro implements Comparable<Libro> {

    private int isbn;
    private String titulo;
    private LocalDate fechaPublicacion;
    private float precio;

    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public Libro() {
        this.isbn = 0;
        this.titulo = "";
        this.fechaPublicacion = LocalDate.now();
        this.precio = 0f;
    }

    public Libro(int isbn, String titulo, LocalDate fechaPublicacion, float precio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.precio = precio;
    }


    public int getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public float getPrecio() {
        return precio;
    }

    public String getFechaPublicacionTexto() {
        return fechaPublicacion == null ? "" : fechaPublicacion.format(FORMATO_FECHA);
    }


    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn
                + ", titulo=" + titulo
                + ", fechaPublicacion=" + getFechaPublicacionTexto()
                + ", precio=" + precio + '}';
    }

    
    @Override
    public int compareTo(Libro otro) {
        return this.titulo.compareToIgnoreCase(otro.titulo);
    }
}