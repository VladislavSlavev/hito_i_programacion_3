package gym;

import java.sql.ResultSet;

public class Usuario {
    private String nombre;
    private String planTrabajo;
    private double peso;
    private int eventosMes;
    private int horasExtra;
    private Conexion conexion= new Conexion();
    
    public Usuario(String nombre, String planTrabajo, double peso, int eventosMes, int horasExtra2) {
        this.nombre = nombre;
        this.planTrabajo = planTrabajo;
        this.peso = peso;
        this.eventosMes = eventosMes;
        this.horasExtra = horasExtra2;
    }
    
    public Usuario() {
    	
    }

    //Almacenamiento de los datos del usuario
    public String getNombre() {
        return nombre;
    }

    public String getPlanTrabajo() {
        return planTrabajo;
    }

    public double getPeso() {
    	
    	return peso;
    	
    }
    
    public String getCat() {
    	
    	//Metodo para devolver la categoria del usuario
        String comparacionPeso = "";
        if (this.peso < 66) {
            comparacionPeso = "Peso inferior a la categoría minima";
        } else if (this.peso >= 66 && this.peso <73) {
            comparacionPeso = "Peso dentro de la categoría Pluma";
        } else if (this.peso >= 73 && this.peso <81) {
            comparacionPeso = "Peso dentro de la categoría Ligero";
        } else if (this.peso >= 81 && this.peso <90) {
            comparacionPeso = "Peso dentro de la categoria Medio";
        } else if (this.peso >= 90 && this.peso <100) {
            comparacionPeso = "Peso dentro de la categoria Medio-Pesado";
        } else {
            comparacionPeso = "Peso dentro de la categoria Pesado";
        }
    	
        return comparacionPeso;
    }

    public int getEventosMes() {
        return eventosMes;
    }

    public int isHorasExtra() {
        return horasExtra;
    }
    
    public void insertarUsuario() {
    	
    	//Metodo para insertar el usuario en la base de datos
    	String sql = "INSERT INTO usuarios (ID, Nombre, Plan, Peso, Eventos, HorasExtra) VALUES (NULL, '"+nombre+"', '"+planTrabajo+"', '"+peso+"', '"+eventosMes+"', '"+horasExtra+"');";
    	conexion.executeUpdate(sql);
    	
    }
    
    //Metodo para mostrar los datos de todos los usuarios
    public ResultSet consultaBase() {
    	
    	String sql = "SELECT * FROM usuarios;";
    	return conexion.executeQuery(sql);
    	
    	
    	
    	
    }
}

