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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class PanelNuevo extends JPanel {

    private final JTextField txtIsbn = new JTextField();
    private final JTextField txtTitulo = new JTextField();
    private final JTextField txtFecha = new JTextField();
    private final JTextField txtPrecio = new JTextField();
    private final JButton btnGuardar = new JButton("Guardar");

    private final JLabel lblError = new JLabel(" ", SwingConstants.CENTER);

    public PanelNuevo() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        JLabel cabecera = new JLabel("Nuevo libro", SwingConstants.CENTER);
        cabecera.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(cabecera, BorderLayout.NORTH);

        JPanel datos = new JPanel(new GridLayout(4, 2, 10, 10));
        datos.add(new JLabel("ISBN (4 cifras):"));
        datos.add(txtIsbn);
        datos.add(new JLabel("Título:"));
        datos.add(txtTitulo);
        datos.add(new JLabel("Fecha publicación (dd/MM/aaaa):"));
        datos.add(txtFecha);
        datos.add(new JLabel("Precio (10 - 75):"));
        datos.add(txtPrecio);
        JPanel centro = new JPanel(new BorderLayout());
        centro.add(datos, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);

        lblError.setForeground(Color.RED);                 // <<< JLabel ROJO
        lblError.setFont(lblError.getFont().deriveFont(Font.BOLD));

        JPanel botones = new JPanel(new FlowLayout());
        btnGuardar.setFont(btnGuardar.getFont().deriveFont(Font.BOLD));
        botones.add(btnGuardar);

        JPanel sur = new JPanel(new GridLayout(2, 1, 0, 6));
        sur.add(lblError);
        sur.add(botones);
        add(sur, BorderLayout.SOUTH);
    }

    public String getIsbn()   { return txtIsbn.getText(); }
    public String getTitulo() { return txtTitulo.getText(); }
    public String getFecha()  { return txtFecha.getText(); }
    public String getPrecio() { return txtPrecio.getText(); }

    public void mostrarError(String mensaje) {
        lblError.setForeground(Color.RED);
        lblError.setText(mensaje);
    }

    public void mostrarOk(String mensaje) {
        lblError.setForeground(new Color(0, 130, 0));
        lblError.setText(mensaje);
    }

    public void limpiarCampos() {
        txtIsbn.setText("");
        txtTitulo.setText("");
        txtFecha.setText("");
        txtPrecio.setText("");
        txtIsbn.requestFocusInWindow();
    }

    public void limpiar() {
        limpiarCampos();
        lblError.setText(" ");         
    }

    public void addGuardarListener(ActionListener l) { btnGuardar.addActionListener(l); }
}