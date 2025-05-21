package cursos.entidades;

import java.util.Objects;

import cursos.excepciones.AñoErrorException;
import cursos.excepciones.DescripcionErrorException;
import cursos.excepciones.IdErrorException;
import cursos.excepciones.NombreErrorException;

/**
 * La clase Curso es la encargada de crear los objetos con unas determinada
 * información
 */
public class Curso {

	/**
	 * El id que es el identificador del curso
	 */
	private int id;

	/**
	 * Es el nombre del curso
	 */
	private String nombre;

	/**
	 * La descripcion del curso, que redacta de que trata la asignatura
	 */
	private String descripcion;

	/**
	 * El año en el que transcurrirá el curso
	 */
	private int año;

	/**
	 * Constructor Curso, es el encargado de crear el objeto Curso, el cual
	 * recibira los siguientes datos por parametro, aparte tambien controla la
	 * introducion de datos si son correcto y si son erroneas saltara las siguientes
	 * excepciones:
	 * 
	 * @param id          Id del curso
	 * @param nombre      Nombre del curso
	 * @param descripcion Descripción del curso
	 * @param año         Año en el que trascurrira el curso
	 * 
	 * @throws IdErrorException          Excepción que controla el id del curso
	 *                                   introducido por parametro
	 * @throws NombreErrorException      Excepción que controla el nombre del curso
	 *                                   introducido por parametro
	 * @throws DescripcionErrorException Excepción que controla la descripciñon del
	 *                                   curso introducido por parametro
	 * @throws AñoErrorException         Excepción que controla el año introducido
	 *                                   por parametro
	 */
	public Curso(int id, String nombre, String descripcion, int año)
			throws IdErrorException, NombreErrorException, DescripcionErrorException, AñoErrorException {

		if (id > 0) {

			this.id = id;

		} else {
			throw new IdErrorException();
		}

		if (nombre != null && !nombre.isBlank()) {

			this.nombre = nombre;

		} else {
			throw new NombreErrorException();
		}

		if (descripcion != null && !descripcion.isBlank()) {

			this.descripcion = descripcion;

		} else {
			throw new DescripcionErrorException();
		}

		if (año > 0) {

			this.año = año;

		} else {
			throw new AñoErrorException();
		}

	}

	/**
	 * Metodo para conseguir el id del curso
	 * 
	 * @return delvuelve el id del curso
	 */
	public int getId() {
		return id;
	}

	/**
	 * Metodo para conseguir el nombre del curso
	 * 
	 * @return delvuelve el nombre del curso
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para conseguir la descripción del curso
	 * 
	 * @return delvuelve la descripción del curso
	 */
	public String getDescrpcion() {
		return descripcion;
	}

	/**
	 * Metodo para conseguir el año del curso
	 * 
	 * @return delvuelve el año del curso
	 */
	public int getAño() {
		return año;
	}

	/**
	 * Metodo que modifica el año de realización del curso, he seleccionado solo el año del
	 * curso, ya que es el unico que veo que puede cambiar en cada año.
	 * 
	 * @param año introduce el valor del año del curso y lo sustituye.
	 */
	public void setAño(int año) {
		this.año = año;
	}

	/**
	 * Metodo que declara el id como primary key de curso.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Metodo para comparar si dos cursos son iguales. Son iguales cuando sus id son
	 * los mismos.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean igual = false;

		Curso curso = (Curso) obj;

		if (curso.id == this.id) {
			igual = true;
		}

		return igual;
	}

	/**
	 * Metodo que devuelve un String con los datos del curso.
	 */
	@Override
	public String toString() {
		return "Curso:\n\t-Id:" + this.id + "\n\t-Nombre: " + this.nombre + "\n\t-Descripción:" + this.descripcion
				+ "\n\t-Año: " + this.año;
	}

}
