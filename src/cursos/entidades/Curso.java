package cursos.entidades;

import java.util.Objects;

import cursos.excepciones.AñoErrorException;
import cursos.excepciones.DescripcionErrorException;
import cursos.excepciones.IdErrorException;
import cursos.excepciones.NombreErrorException;

public class Curso {

	private int id;
	private String nombre;
	private String descrpcion;
	private int año;

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

			this.descrpcion = descripcion;

		} else {
			throw new DescripcionErrorException();
		}

		if (año > 0) {

			this.año = año;

		} else {
			throw new AñoErrorException();
		}

	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescrpcion() {
		return descrpcion;
	}

	public int getAño() {
		return año;
	}

	/**
	 * Modifica el año de realización del curso, he seleccionado solo el año del
	 * curso, ya que es el unico que veo que puede cambiar en cada año
	 * 
	 * @param año introduce el valor del año del curso y lo sustituye
	 */
	public void setAño(int año) {
		this.año = año;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		boolean igual=false;
		
		Curso curso=(Curso) obj;
		
		if(curso.id==this.id) {
			igual=true;
		}
		
		return igual;
	}

	@Override
	public String toString() {
		return "";
	}
	
	
	
	

}
