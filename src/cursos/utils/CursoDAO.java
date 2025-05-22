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

	public void create(Curso cur) {
		String sql = "INSERT INTO Cursos (nombre, descripcion, año_escolar)" + "VALUES (?, ?, ?)";

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

	public List<Curso> listarCursos() {

		List<Curso> lista = new ArrayList<Curso>();

		Statement st;
		try {
			st = conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Cursos");

			while (rs.next()) {

				Curso c = new Curso(rs.getInt("id_curso"), rs.getString("nombre"), rs.getString("descripcion"),
						rs.getInt("año_escolar"));

				lista.add(c);

			}

		} catch (SQLException e) {

			System.out.println(e);
			
		} catch (IdErrorException | NombreErrorException | DescripcionErrorException | AñoErrorException e) {
			System.out.println(e.getMessage());
		}

		return lista;

	}

	public void asignarProfesorCurso(int idProfesor, int idCurso) {

		boolean existeP = false;
		boolean existeC = false;

		Statement st;
		try {
			st = conexion.createStatement();

			ResultSet rsP = st.executeQuery("SELECT id_profesor FROM Profesor");

			while (rsP.next()) {
				if (rsP.getInt("id_profesor") == idProfesor) {
					existeP = true;
				}
			}

			ResultSet rsC = st.executeQuery("SELECT id_curso FROM Cursos");

			while (rsC.next()) {
				if (rsC.getInt("id_curso") == idCurso) {
					existeC = true;
				}
			}

			if (existeC && existeP) {

				String sql = "INSERT INTO CursoProfesor (id_profesor, id_curso)" + "VALUES (?, ?)";

				PreparedStatement ps;

				ps = conexion.prepareStatement(sql);
				ps.setInt(1, idProfesor);
				ps.setInt(2, idCurso);

				ps.executeUpdate();

			}

		} catch (SQLException e) {

			System.out.println(e);
		}

	}

	public String listaEstudiantesCurso(int idCurso) {
		String lista = "";

		Statement st;
		try {
			st = conexion.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT nombre, apellido, fecha_nacimiento, email, telefono FROM Estudiante AS E "
							+ "INNER JOIN Matriculas AS M ON E.id_estudiante=M.id_estudiante WHERE M.id_curso="
							+ idCurso);

			while (rs.next()) {

				lista += "-"+ rs.getString("nombre") + " " + rs.getString("apellido") + " \n\tFecha nacimiento: " + rs.getString("fecha_nacimiento") + " \n\tEmail: "
						+ rs.getString("email") + " \n\tTelefono: " + rs.getString("telefono") + "\n\n";

			}

		} catch (SQLException e) {

			System.out.println(e);
		}

		return lista;
	}
	
	public void eliminar(int idCurso) {
		Statement st;
		try {
			st = conexion.createStatement();
			ResultSet rs = st
					.executeQuery("DELETE FROM Cursos WHERE id_curso="+  idCurso);
		}catch (SQLException e) {

			System.out.println(e);
		}
	}

}
