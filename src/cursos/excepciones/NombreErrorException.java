package cursos.excepciones;

/**
 * Clase excepcion que controla el nombre ingrsado
 */
public class NombreErrorException extends Exception{
	
	/**
	 * Devuelve un mensaje de error
	 */
	public String toString() {
		return "El nombre introducido no es correcto.";
		
	}
	
	/**
	 * Devuelve un mensaje de error
	 */
	public String getMessage() {
		return "El nombre introducido no es correcto.";
		
	}

}
