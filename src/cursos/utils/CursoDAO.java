package cursos.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cursos.entidades.Curso;
import cursos.excepciones.AñoErrorException;
import cursos.excepciones.DescripcionErrorException;
import cursos.excepciones.IdErrorException;
import cursos.excepciones.NombreErrorException;

import static cursos.utils.Constantes.*;

public class CursoDAO {

private Connection conexion;
	
	public CursoDAO() {
	
		try {
			conexion = DriverManager.getConnection(CONEXION, USUARIO, CONTRASEÑA);
		} catch (SQLException e) {
			System.out.println("Error al crear la conexión con la base de datos: " + e.getMessage());
		}
		
	}
	
	public Connection getConexion() {
		return conexion;
	}
	
	public void create (Curso cur) {
		String sql = "INSERT INTO Cursos (nombre, descripcion, año_escolar)"
				+ "VALUES (?, ?, ?)";
		
		PreparedStatement ps;
		try {
			ps = conexion.prepareStatement(sql);
			ps.setString(1, cur.getNombre());
			ps.setString(2, cur.getDescrpcion());
			ps.setInt(3, cur.getAño());
			
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error al insertar en cursos: " + e.getMessage());
		}
		
	}
	
	public List<Curso> listarCursos () {
		
		List<Curso> lista = new ArrayList<Curso>();
		
		Statement st;
		try {
			st = conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Cursos");
		
			while (rs.next()) {
				
				
				Curso c = new Curso(rs.getInt("id_curso"),rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("año_escolar"));
				
				lista.add(c);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (IdErrorException | NombreErrorException | DescripcionErrorException | AñoErrorException e) {
					System.out.println(e.getMessage());
				}
				
		
		
		
		return lista;
		
	}
	
}
