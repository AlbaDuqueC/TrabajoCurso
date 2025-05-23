package cursos.excepciones;

/**
 * Clase excepcion que controla el Id ingrsado
 */
public class IdErrorException extends Exception{
	
	/**
	 * Devuelve un mensaje de error
	 */
	public String toString() {
		return "El id introducido no es correcto.";
		
	}
	
	/**
	 * Devuelve un mensaje de error
	 */
	public String getMessage() {
		return "El id introducido no es correcto.";
		
	}

}
