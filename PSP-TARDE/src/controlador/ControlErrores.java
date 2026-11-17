package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import modelo.Libro;


public final class ControlErrores {

    // ---------------- LÍMITES ----------------
    public static final int ISBN_MIN = 1000;          // 4 cifras y positivo
    public static final int ISBN_MAX = 9999;
    public static final LocalDate FECHA_MIN = LocalDate.of(2001, 1, 1);
    public static final float PRECIO_MIN = 10f;
    public static final float PRECIO_MAX = 75f;

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private ControlErrores() {
    }

    public static LocalDate fechaMax() {
        return LocalDate.now();
    }

    public static String validarIsbn(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "El ISBN es obligatorio.";
        }
        String s = texto.trim();
        if (!s.matches("\\d{4}")) {                   
            return "El ISBN debe ser un número entero positivo de 4 cifras (ej. 1234).";
        }
        int isbn = Integer.parseInt(s);
        if (isbn < ISBN_MIN || isbn > ISBN_MAX) {     
            return "El ISBN debe estar entre " + ISBN_MIN + " y " + ISBN_MAX + ".";
        }
        return null;
    }

    public static String validarTitulo(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "El título es obligatorio.";
        }
        return null;
    }

    public static String validarFecha(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "La fecha es obligatoria (dd/MM/aaaa).";
        }
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(texto.trim(), FORMATO);
        } catch (DateTimeParseException e) {
            return "Fecha incorrecta: usa dd/MM/aaaa con una fecha que exista (ej. 15/03/2018).";
        }
        if (fecha.isBefore(FECHA_MIN)) {
            return "La fecha debe ser de este siglo (desde el "
                    + FECHA_MIN.format(Libro.FORMATO_FECHA) + ").";
        }
        if (fecha.isAfter(fechaMax())) {
            return "La fecha no puede ser futura (posterior al "
                    + fechaMax().format(Libro.FORMATO_FECHA) + ").";
        }
        return null;
    }

    
    public static String validarPrecio(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "El precio es obligatorio.";
        }
        float precio;
        try {
            precio = Float.parseFloat(normalizarNumero(texto));
        } catch (NumberFormatException e) {
            return "El precio debe ser un número (ej. 19,95).";
        }
        if (Float.isNaN(precio) || Float.isInfinite(precio)
                || precio < PRECIO_MIN || precio > PRECIO_MAX) {
            return "El precio debe estar entre " + (int) PRECIO_MIN + " y " + (int) PRECIO_MAX + ".";
        }
        return null;
    }

    private static String normalizarNumero(String texto) {
        return texto.trim().replace(" ", "").replace("€", "").replace(',', '.');
    }

    
    public static String validarLibro(String isbn, String titulo, String fecha, String precio) {
        String error = validarIsbn(isbn);
        if (error == null) error = validarTitulo(titulo);
        if (error == null) error = validarFecha(fecha);
        if (error == null) error = validarPrecio(precio);
        return error;
    }

   
    public static Libro crearLibro(String isbn, String titulo, String fecha, String precio) {
        String error = validarLibro(isbn, titulo, fecha, precio);
        if (error != null) {
            throw new IllegalArgumentException(error);
        }
        return new Libro(
                Integer.parseInt(isbn.trim()),
                titulo.trim(),
                LocalDate.parse(fecha.trim(), FORMATO),
                Float.parseFloat(normalizarNumero(precio)));
    }
}