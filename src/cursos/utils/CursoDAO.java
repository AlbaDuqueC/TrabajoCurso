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

/**
 * Clase CursoDAO, es la encargada de insertar, modificar, eliminar y extraer
 * los datos de la base de datos
 */
public class CursoDAO {

	/**
	 * Variable que se encarga de la conexion
	 */
	private Connection conexion;

	/**
	 * Constructor que conecata a la base de datos
	 */
	public CursoDAO() {

		try {
			conexion = DriverManager.getConnection(CONEXION, USUARIO, CONTRASEÑA);
		} catch (SQLException e) {
			System.out.println("Error al crear la conexión con la base de datos: " + e.getMessage());
		}

	}

	/**
	 * funcion que devuelve la conexion
	 * 
	 * @return devuelve la conexion
	 */
	public Connection getConexion() {
		return conexion;
	}

	/**
	 * inserta un nuevo curso a la base de datos
	 * 
	 * @param cur Recibe un ojeto Curso con todos los datos ingresados anteriormente
	 * @return devuelve si se ha podido insertar o no a la base de datos
	 */
	public boolean create(Curso cur) {
		boolean creada = true;
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
			creada = false;
		}

		return creada;

	}

	/**
	 * Funcion que crea una lista con todos los cursos de la base de datos
	 * 
	 * @return devuleve una lista con todos los datos ingresados en ella
	 */
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

	/**
	 * Metodo que asigna un profesor a un curso
	 * 
	 * @param idProfesor el id del profesor al que quieran asignar el curso
	 * @param idCurso    el id del curso al que quieren asignar al profesor
	 * @return devulve un boolean si se ha podido asignar o no
	 */
	public boolean asignarProfesorCurso(int idProfesor, int idCurso) {

		boolean seAsigno = false;

		boolean existeP = false;
		boolean existeC = false;

		Statement st;
		try {
			st = conexion.createStatement();

			ResultSet rsP = st.executeQuery("SELECT id_profesor FROM Profesores");

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

				seAsigno = true;

			}

		} catch (SQLException e) {

			System.out.println(e);
		}

		return seAsigno;

	}

	/**
	 * Metodo que crea un String como si fuera una lista de todos los Estudiantes de
	 * un curso
	 * 
	 * @param idCurso recibe el id del curso al que queremos saber los estudiantes
	 *                que tiene
	 * @return devuelve un String con todos los datos de los estudiantes
	 */
	public String listaEstudiantesCurso(int idCurso) {
		String lista = "";

		Statement st;
		try {
			st = conexion.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT nombre, apellido, fecha_nacimiento, email, telefono FROM Estudiantes AS E "
							+ "INNER JOIN Matriculas AS M ON E.id_estudiante=M.id_estudiante WHERE M.id_curso="
							+ idCurso);

			while (rs.next()) {

				lista += "-" + rs.getString("nombre") + " " + rs.getString("apellido") + " \n\tFecha nacimiento: "
						+ rs.getString("fecha_nacimiento") + " \n\tEmail: " + rs.getString("email") + " \n\tTelefono: "
						+ rs.getString("telefono") + "\n\n";

			}

		} catch (SQLException e) {

			System.out.println(e);
		}

		return lista;
	}

	/**
	 * metodo para eliminar el curso
	 * 
	 * @param idCurso id del curso que quiera eliminar
	 * @return devuelve un boolean si se ha podido eliminar o no el curso
	 */
	public boolean eliminar(int idCurso) {

		boolean eliminado = false;

		String sql1 = " DELETE FROM Matriculas WHERE id_curso= " + idCurso;

		String sql2 = " DELETE FROM CursoProfesor WHERE id_curso= " + idCurso;

		String sql3 = " DELETE FROM Cursos WHERE id_curso= " + idCurso;

		Statement ps;

		try {

			ps = conexion.createStatement();

			ps.executeUpdate(sql1);
			ps.executeUpdate(sql2);
			ps.executeUpdate(sql3);

			eliminado = true;
		} catch (SQLException e) {
			System.err.println(e);
		}

		return eliminado;
	}

}
