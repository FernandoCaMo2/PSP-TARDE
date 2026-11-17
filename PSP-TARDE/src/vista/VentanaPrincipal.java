package vista;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class VentanaPrincipal extends JFrame {

    private static final String CARTA_VISUALIZAR = "VISUALIZAR";
    private static final String CARTA_NUEVO = "NUEVO";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contenedor = new JPanel(cardLayout);

    private final PanelVisualizar panelVisualizar = new PanelVisualizar();
    private final PanelNuevo panelNuevo = new PanelNuevo();

    private final JMenuItem itemVisualizar = new JMenuItem("Visualizar");
    private final JMenuItem itemNuevo = new JMenuItem("Nuevo");
    private final JMenuItem itemSalir = new JMenuItem("Salir");

    public VentanaPrincipal() {
        super("Gestión de Libros - MVC");

        contenedor.add(panelVisualizar, CARTA_VISUALIZAR);
        contenedor.add(panelNuevo, CARTA_NUEVO);
        setContentPane(contenedor);

        JMenu menu = new JMenu("Opciones");
        menu.add(itemVisualizar);
        menu.add(itemNuevo);
        menu.addSeparator();
        menu.add(itemSalir);
        JMenuBar barra = new JMenuBar();
        barra.add(menu);
        setJMenuBar(barra);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 380);
        setResizable(false);
        setLocationRelativeTo(null);       // centrada
    }

    public PanelVisualizar getPanelVisualizar() { return panelVisualizar; }
    public PanelNuevo getPanelNuevo() { return panelNuevo; }

    public void mostrarVisualizar() { cardLayout.show(contenedor, CARTA_VISUALIZAR); }
    public void mostrarNuevo() { cardLayout.show(contenedor, CARTA_NUEVO); }

    public void addVisualizarListener(ActionListener l) { itemVisualizar.addActionListener(l); }
    public void addNuevoListener(ActionListener l) { itemNuevo.addActionListener(l); }
    public void addSalirListener(ActionListener l) { itemSalir.addActionListener(l); }
}