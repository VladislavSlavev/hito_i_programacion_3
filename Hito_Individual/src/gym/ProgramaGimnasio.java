package gym;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProgramaGimnasio extends JFrame {
    private List<Usuario> usuarios;
    private JTextField txtNombre;
    private JTextField txtPlanTrabajo;
    private JTextField txtPeso;
    private JTextField txtEventosMes;
    private JCheckBox chkHorasExtra;

    public ProgramaGimnasio() {
        usuarios = new ArrayList<>();

        // Configuración de la ventana
        setTitle("Programa de Gimnasio");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de los componentes de la interfaz
        JLabel lblNombre = new JLabel("Nombre del usuario:");
        txtNombre = new JTextField("", 20);

        JLabel lblPlanTrabajo = new JLabel("Plan de trabajo (Principiante/Intermedio/Elite):");
        txtPlanTrabajo = new JTextField(20);

        JLabel lblPeso = new JLabel("Peso actual (kg):");
        txtPeso = new JTextField(10);

        /*JLabel lblCategoria = new JLabel("Categoría de peso:");
        txtCategoria = new JTextField(10);*/

        JLabel lblEventosMes = new JLabel("Eventos presentados este mes(Máximo 2):");
        txtEventosMes = new JTextField(10);

        JLabel lblHorasExtra = new JLabel("¿Añadir horas extra este mes?");
        chkHorasExtra = new JCheckBox();

        JButton btnCalcular = new JButton("Resultado");
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });

        // Configuración del panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Agregar componentes al panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(lblNombre, gbc);

        gbc.gridx = 1;
        panelPrincipal.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(lblPlanTrabajo, gbc);

        gbc.gridx = 1;
        panelPrincipal.add(txtPlanTrabajo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(lblPeso, gbc);

        gbc.gridx = 1;
        panelPrincipal.add(txtPeso, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        panelPrincipal.add(lblEventosMes, gbc);

        gbc.gridx = 1;
        panelPrincipal.add(txtEventosMes, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelPrincipal.add(lblHorasExtra, gbc);

        gbc.gridx = 1;
        panelPrincipal.add(chkHorasExtra, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(btnCalcular, gbc);

        // Agregar panel principal a la ventana
        getContentPane().add(panelPrincipal);

        // Mostrar la ventana
        setVisible(true);
    }

    private void agregarUsuario() {
        String nombre = txtNombre.getText();
        String planTrabajo = txtPlanTrabajo.getText();
        double peso = Double.parseDouble(txtPeso.getText());
        /*String categoria = txtCategoria.getText();*/
        int eventosMes = Integer.parseInt(txtEventosMes.getText());
        boolean horasExtra = chkHorasExtra.isSelected();

        Usuario usuario = new Usuario(nombre, planTrabajo, peso, eventosMes, horasExtra);
        usuarios.add(usuario);

        mostrarInformacion(usuario);
    }

    private void mostrarInformacion(Usuario usuario) {
        double costoTotal = usuario.getEventosMes() * 10; // Cada evento tiene un costo de 10

        String comparacionPeso = "";
        if (usuario.getPeso() < 50 /*&& usuario.getCategoria().equals("CategoriaA")*/) {
            comparacionPeso = "Peso inferior a la categoría Pesada";
        } else if (usuario.getPeso() >= 50 /*&& usuario.getPeso() < 60 && usuario.getCategoria().equals("CategoriaB")*/) {
            comparacionPeso = "Peso dentro de la categoría B";
        } else if (usuario.getPeso() >= 60 /*&& usuario.getPeso() < 70 && usuario.getCategoria().equals("CategoriaC")*/) {
            comparacionPeso = "Peso dentro de la categoría C";
        } else if (usuario.getPeso() >= 70 /*&& usuario.getCategoria().equals("CategoriaD")*/) {
            comparacionPeso = "Peso superior a la categoría D";
        } else {
            comparacionPeso = "Peso fuera de rango para la categoría";
        }

        String resultado = "Nombre: " + usuario.getNombre() + "\n\n" +
                "Plan de trabajo: " + usuario.getPlanTrabajo() + "\n\n" +
                "Lista de gastos del mes:\n" +
                "- Entrenamientos y competiciones: $" + costoTotal + "\n\n" +
                "Comparación de peso:\n" +
                "- Peso actual: " + usuario.getPeso() + " kg\n" +
               /* "- Categoría de peso: " + usuario.getCategoria() + "\n" +*/
                "- Resultado: " + comparacionPeso;

        JOptionPane.showMessageDialog(this, resultado, "Resultados", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProgramaGimnasio();
            }
        });
    }
}
