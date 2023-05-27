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
    private JComboBox txtPlanTrabajo;
    private JTextField txtPeso;
    private JTextField txtEventosMes;
    private JTextField chkHorasExtra;
    
    private String[] planesTrabajo = {"","Principiante", "Intermedio", "Elite"};


    public ProgramaGimnasio() {
        usuarios = new ArrayList<>();
        
        // Creacion y configuración de la ventana
        setTitle("Programa de Gimnasio");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de los componentes de la interfaz
        JLabel lblNombre = new JLabel("Nombre del usuario:");
        txtNombre = new JTextField("", 20);

        JLabel lblPlanTrabajo = new JLabel("Plan de trabajo:");
        txtPlanTrabajo = new JComboBox<>(planesTrabajo);

        JLabel lblPeso = new JLabel("Peso actual (kg):");
        txtPeso = new JTextField(10);

        JLabel lblEventosMes = new JLabel("Eventos presentados este mes(Máximo 2):");
        txtEventosMes = new JTextField(10);

        JLabel lblHorasExtra = new JLabel("¿Añadir horas extra este mes?(Máximo 5)");
        chkHorasExtra = new JTextField(10);

        JButton btnCalcular = new JButton("Resultado");
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(btnCerrar, gbc);
        
        // Agregar panel principal a la ventana
        getContentPane().add(panelPrincipal);

        // Mostrar la ventana
        setVisible(true);
    }

    private void agregarUsuario() {
    	
    	//Metodo para añadir un usuario
        String nombre = txtNombre.getText();
        String planTrabajo = txtPlanTrabajo.getSelectedItem().toString();
        double peso = Double.parseDouble(txtPeso.getText());
        int eventosMes = Integer.parseInt(txtEventosMes.getText());
        int horasExtra = Integer.parseInt(chkHorasExtra.getText());
        
        //Controles de error
        if (nombre.isEmpty()) {
        	mostrarError("Debes introducir un nombre");
        	return;
        }
        
        if (planTrabajo=="") {
        	mostrarError("Debes elegir un plan");
        	return;
        }
        if (peso==0.00) {
        	mostrarError("Debes introducir un peso válido");
        	return;
        }
        if (horasExtra>5) {
        	mostrarError("Sólo puedes tener un máximo de 5 horas extra");
        	return;
        }
        
        if (eventosMes>2) {
        	mostrarError("Sólo puedes estar en un máximo de dos eventos");
        	return;
        }
        if (planTrabajo=="Principiante" && eventosMes>0) {
        	mostrarError("Los principiantes no pueden participar en eventos");
        	return;
        }
        
        //Creacion de nueva instancia
        Usuario usuario = new Usuario(nombre, planTrabajo, peso, eventosMes, horasExtra);
        usuarios.add(usuario);

        mostrarInformacion(usuario);
    }

    private void mostrarInformacion(Usuario usuario) {
    	
    	//Metodo para mostrar la informacion del usuario añadido
    	double costoTotal=0;
    	
        switch(usuario.getPlanTrabajo()) {
        case "Principiante":
        	costoTotal=25.00;
          break;
        case "Intermedio":
        	costoTotal=30.00;
          break;
        case "Elite":
        	costoTotal=35.00;
        break;
        }
        
        //Mostrar los gastos
        costoTotal = costoTotal+(usuario.getEventosMes() * 22) + (usuario.isHorasExtra()*9.50); // Cada evento tiene un costo de 22


        String resultado = "Nombre: " + usuario.getNombre() + "\n\n" +
                "Plan de trabajo: " + usuario.getPlanTrabajo() + "\n\n" +
                "Lista de gastos del mes:\n" +
                "- Entrenamientos y competiciones: " + costoTotal + " € \n\n" +
                "Comparación de peso:\n" +
                "- Peso actual: " + usuario.getPeso() + " kg\n" +
                "- Categoria: " + usuario.getCat();

        JOptionPane.showMessageDialog(this, resultado, "Resultados", JOptionPane.INFORMATION_MESSAGE);
        
        //Llamada del metodo para insertar el usuario en la base de datos
        usuario.insertarUsuario();
    }
    
    //Mensaje de error
    private void mostrarError(String mensaje) {
    	JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

