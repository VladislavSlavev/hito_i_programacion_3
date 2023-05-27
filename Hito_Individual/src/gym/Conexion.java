package gym;

import java.sql.*;

public class Conexion {

	private Connection conexion;
	private Statement statement;
	private ResultSet resultset;
	
	public Conexion() {
		try {
			//Establecer la conexion
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/hito3", "root", "" );

		} catch(Exception e) {
			//Mensaje de error
			System.out.println("No se puede conectar");
			e.printStackTrace();
			
		}
	}
	
	public ResultSet executeQuery(String sql) {
		//Ejecutar consulta
		try {
			
			statement = conexion.createStatement();
			resultset = statement.executeQuery(sql);
			
		} catch(SQLException e) {
			//Mensaje de error
			System.out.println("No se puede realizar la accion");
			e.printStackTrace();
			
		}
		
		return resultset;
	}
	
	public void executeUpdate(String sql) {
		//Ejecutar insercion de datos
		try {
			statement = conexion.createStatement();
			statement.executeUpdate(sql);
		} catch(SQLException e) {
			//Mensaje de error
			System.out.println("No se puede realizar la accion");
			e.printStackTrace();
			
		}
		
	}

}
