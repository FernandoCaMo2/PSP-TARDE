package controlador;

import java.util.Locale;
import modelo.Libro;
import vista.PanelNuevo;
import vista.PanelVisualizar;
import vista.VentanaPrincipal;


public class ControladorLibros {

    private static final Locale ES = Locale.forLanguageTag("es-ES");

    private final ListaLibros lista;
    private final VentanaPrincipal ventana;
    private final PanelVisualizar panelVisualizar;
    private final PanelNuevo panelNuevo;

    public ControladorLibros(ListaLibros lista, VentanaPrincipal ventana) {
        this.lista = lista;
        this.ventana = ventana;
        this.panelVisualizar = ventana.getPanelVisualizar();
        this.panelNuevo = ventana.getPanelNuevo();

        ventana.addVisualizarListener(e -> irAVisualizar());
        ventana.addNuevoListener(e -> irANuevo());
        ventana.addSalirListener(e -> System.exit(0));

        panelVisualizar.addPrimeroListener(e -> { lista.irAlPrimero(); refrescar(); });
        panelVisualizar.addAnteriorListener(e -> { lista.retroceder(); refrescar(); });
        panelVisualizar.addSiguienteListener(e -> { lista.avanzar(); refrescar(); });
        panelVisualizar.addUltimoListener(e -> { lista.irAlUltimo(); refrescar(); });
        panelVisualizar.addBorrarListener(e -> borrar());
        panelVisualizar.addPrecioFinalListener(e -> mostrarPrecioFinal());
        panelVisualizar.addOrdenarListener(e -> { lista.ordenarPorTitulo(); refrescar(); });

        panelNuevo.addGuardarListener(e -> guardar());

        irAVisualizar();      
    }

    private static String euros(float valor) {
        return String.format(ES, "%,.2f €", valor);
    }


    private void irAVisualizar() {
        refrescar();
        ventana.mostrarVisualizar();
    }

    private void irANuevo() {
        panelNuevo.limpiar();
        ventana.mostrarNuevo();
    }


    private void refrescar() {
        Libro l = lista.obtenerLibroActual();
        if (l == null) {
            panelVisualizar.mostrarVacia();
        } else {
            panelVisualizar.mostrarLibro(
                    String.valueOf(l.getIsbn()),
                    l.getTitulo(),
                    l.getFechaPublicacionTexto(),
                    euros(l.getPrecio()),
                    lista.getActual() + 1,
                    lista.size());
        }
        panelVisualizar.setNavegacion(!lista.esPrimero(), !lista.esUltimo(), !lista.esVacia());
    }

    private void borrar() {
        Libro l = lista.obtenerLibroActual();
        if (l == null) {
            return;
        }
        if (panelVisualizar.confirmar("¿Borrar el libro \"" + l.getTitulo() + "\"?")) {
            lista.borrarLibro();
            refrescar();
        }
    }

    private void mostrarPrecioFinal() {
        Libro l = lista.obtenerLibroActual();
        if (l == null) {
            return;
        }
        panelVisualizar.mostrarMensaje(
                "Libro: " + l.getTitulo() + "\n"
                + "Precio: " + euros(l.getPrecio()) + "\n"
                + "IVA (4 %): " + euros(lista.calcularIVA()) + "\n"
                + "Precio final: " + euros(lista.precioFinal()),
                "Precio final");
    }


   
    private void guardar() {
        String isbn = panelNuevo.getIsbn();
        String titulo = panelNuevo.getTitulo();
        String fecha = panelNuevo.getFecha();
        String precio = panelNuevo.getPrecio();

        String error = ControlErrores.validarLibro(isbn, titulo, fecha, precio);
        if (error != null) {
            panelNuevo.mostrarError(error);                
            return;
        }
        Libro l = ControlErrores.crearLibro(isbn, titulo, fecha, precio);
        if (lista.existeIsbn(l.getIsbn())) {
            panelNuevo.mostrarError("Ya existe un libro con el ISBN " + l.getIsbn() + ".");
            return;
        }
        lista.anadirLibro(l);
        panelNuevo.limpiarCampos();
        panelNuevo.mostrarOk("Libro \"" + l.getTitulo() + "\" añadido. Total: " + lista.size());
    }
}