package cursos.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PrincipalCurso {

	public static void main(String[] args) {
		
		String conexion = "jdbc:mysql://localhost/InstitutoDB";
		String usuario = "root";
		String contraseña = "alba123";
		
		int idCurso;
		String nombreCurso;
		String descripcionCurso;
		int añoCurso;
		
		try (Connection con = DriverManager.getConnection(conexion, usuario, contraseña)) {
			
			
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}

	}
	
	public static void menu() {
		System.out.println("1. Crear curso.");
		System.out.println("2. Listar todos los cursos.");
		System.out.println("3. Asignar un profesor a un curso.");
		System.out.println("4. Listar estudiantes de un curso.");
		System.out.println("5. Eliminar curso.");
		System.out.println("0. Salir.");
	}

}
