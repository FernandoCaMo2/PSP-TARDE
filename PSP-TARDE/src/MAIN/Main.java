import controlador.ControladorLibros;
import controlador.ListaLibros;
import java.time.LocalDate;
import javax.swing.SwingUtilities;
import modelo.Libro;
import vista.VentanaPrincipal;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ListaLibros lista = new ListaLibros();
            lista.anadirLibro(new Libro(4521, "La sombra del viento", LocalDate.of(2001, 4, 10), 21.90f));
            lista.anadirLibro(new Libro(1234, "Cien años de soledad", LocalDate.of(2007, 3, 6), 18.50f));
            lista.anadirLibro(new Libro(9876, "El infinito en un junco", LocalDate.of(2019, 9, 4), 24.95f));
            lista.irAlPrimero();

            VentanaPrincipal ventana = new VentanaPrincipal();
            new ControladorLibros(lista, ventana);
            ventana.setVisible(true);
        });
    }
}