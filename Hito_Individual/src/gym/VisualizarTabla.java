package gym;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

public class VisualizarTabla extends JFrame {
    private JTable tabla;
    private JScrollPane scrollPane;
    private Usuario usuario = new Usuario();
    private JButton botonCargarDatos;
    
    public VisualizarTabla() {
    	
        // Creacion de la ventana para visualizar la tabla
    	
        setTitle("Tabla");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        tabla = new JTable();
        scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        // Obtener los datos de la base de datos
        obtenerDatos();
        
        // Creacion del boton para agregar un usuario
        botonCargarDatos = new JButton("Añadir Usuario");
        botonCargarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ProgramaGimnasio programa = new ProgramaGimnasio();
                
                programa.setVisible(true);
            }
        });
        add(botonCargarDatos, BorderLayout.SOUTH);
        
        //Mostrar la ventana
        setVisible(true);
    }

    private void obtenerDatos() {
        try {
        	//Creacion del metodo para mostrar la tabla de la base de datos
            ResultSet resultSet = usuario.consultaBase();

            // Crear un modelo de tabla para almacenar los datos
            DefaultTableModel modeloTabla = new DefaultTableModel();
            

            // Obtener datos de las columnas
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numeroColumnas = metaData.getColumnCount();

            // Agregar nombres de columnas
            for (int columna = 1; columna <= numeroColumnas; columna++) {
                modeloTabla.addColumn(metaData.getColumnName(columna));
            }

            // Agregar filas al modelo con los datos de la consulta
            while (resultSet.next()) {
                Object[] fila = new Object[numeroColumnas];
                for (int columna = 1; columna <= numeroColumnas; columna++) {
                    fila[columna - 1] = resultSet.getObject(columna);
                }
                modeloTabla.addRow(fila);
            }

            // Establecer el modelo de tabla
            tabla.setModel(modeloTabla);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

