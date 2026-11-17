package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class PanelVisualizar extends JPanel {

    private final JLabel lblPosicion = new JLabel(" ", SwingConstants.CENTER);
    private final JLabel lblVacia = new JLabel("La lista está vacía. Añade libros desde el menú Nuevo.",
            SwingConstants.CENTER);

    private final JTextField txtIsbn = new JTextField();
    private final JTextField txtTitulo = new JTextField();
    private final JTextField txtFecha = new JTextField();
    private final JTextField txtPrecio = new JTextField();

    private final JButton btnPrimero = new JButton("|<");
    private final JButton btnAnterior = new JButton("<");
    private final JButton btnSiguiente = new JButton(">");
    private final JButton btnUltimo = new JButton(">|");
    private final JButton btnBorrar = new JButton("Borrar");
    private final JButton btnPrecioFinal = new JButton("Precio final");
    private final JButton btnOrdenar = new JButton("Ordenar por título");

    public PanelVisualizar() {
        setLayout(new BorderLayout(10, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        lblPosicion.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblVacia.setForeground(new Color(180, 0, 0));
        lblVacia.setFont(lblVacia.getFont().deriveFont(Font.BOLD));
        lblVacia.setVisible(false);
        JPanel norte = new JPanel(new GridLayout(2, 1));
        norte.add(lblPosicion);
        norte.add(lblVacia);
        add(norte, BorderLayout.NORTH);

        JPanel datos = new JPanel(new GridLayout(4, 2, 10, 8));
        datos.add(new JLabel("ISBN:"));
        datos.add(txtIsbn);
        datos.add(new JLabel("Título:"));
        datos.add(txtTitulo);
        datos.add(new JLabel("Fecha de publicación:"));
        datos.add(txtFecha);
        datos.add(new JLabel("Precio:"));
        datos.add(txtPrecio);
        txtIsbn.setEditable(false);
        txtTitulo.setEditable(false);
        txtFecha.setEditable(false);
        txtPrecio.setEditable(false);
        JPanel centro = new JPanel(new BorderLayout());
        centro.add(datos, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);

        JPanel nav = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        nav.add(btnPrimero);
        nav.add(btnAnterior);
        nav.add(btnSiguiente);
        nav.add(btnUltimo);
        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        btnBorrar.setForeground(Color.RED);
        acciones.add(btnBorrar);
        acciones.add(btnPrecioFinal);
        acciones.add(btnOrdenar);
        JPanel sur = new JPanel(new GridLayout(2, 1, 0, 8));
        sur.add(nav);
        sur.add(acciones);
        add(sur, BorderLayout.SOUTH);
    }


    public void mostrarLibro(String isbn, String titulo, String fecha, String precio, int posicion, int total) {
        lblVacia.setVisible(false);
        lblPosicion.setText("Libro " + posicion + " de " + total);
        txtIsbn.setText(isbn);
        txtTitulo.setText(titulo);
        txtFecha.setText(fecha);
        txtPrecio.setText(precio);
    }

    public void mostrarVacia() {
        lblPosicion.setText("No hay libros");
        lblVacia.setVisible(true);
        txtIsbn.setText("");
        txtTitulo.setText("");
        txtFecha.setText("");
        txtPrecio.setText("");
    }

    public void setNavegacion(boolean puedeRetroceder, boolean puedeAvanzar, boolean hayLibros) {
        btnPrimero.setEnabled(puedeRetroceder);
        btnAnterior.setEnabled(puedeRetroceder);
        btnSiguiente.setEnabled(puedeAvanzar);
        btnUltimo.setEnabled(puedeAvanzar);
        btnBorrar.setEnabled(hayLibros);
        btnPrecioFinal.setEnabled(hayLibros);
        btnOrdenar.setEnabled(hayLibros);
    }

    public boolean confirmar(String pregunta) {
        return JOptionPane.showConfirmDialog(this, pregunta, "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION;
    }

    public void mostrarMensaje(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public void addPrimeroListener(ActionListener l)     { btnPrimero.addActionListener(l); }
    public void addAnteriorListener(ActionListener l)    { btnAnterior.addActionListener(l); }
    public void addSiguienteListener(ActionListener l)   { btnSiguiente.addActionListener(l); }
    public void addUltimoListener(ActionListener l)      { btnUltimo.addActionListener(l); }
    public void addBorrarListener(ActionListener l)      { btnBorrar.addActionListener(l); }
    public void addPrecioFinalListener(ActionListener l) { btnPrecioFinal.addActionListener(l); }
    public void addOrdenarListener(ActionListener l)     { btnOrdenar.addActionListener(l); }
}