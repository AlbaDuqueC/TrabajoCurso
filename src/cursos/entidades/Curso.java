package cursos.entidades;

import cursos.excepciones.IdErrorException;
import cursos.excepciones.NombreErrorException;

public class Curso {

	private int id;
	private String nombre;
	private String descrpcion;
	private int año;

	public Curso(int id, String nombre, String descripcion, int año) throws IdErrorException, NombreErrorException {

		if (id > 0) {

			this.id = id;

		} else {
			throw new IdErrorException();
		}

		if (nombre != null && !nombre.isBlank()) {

		} else {
			throw new NombreErrorException();
		}

	}

}
