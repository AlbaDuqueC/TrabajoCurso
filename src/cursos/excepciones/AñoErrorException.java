package cursos.excepciones;

/**
 * Clase excepcion que controla el año ingrsado
 */
public class AñoErrorException extends Exception{
	
	/**
	 * Devuelve un mensaje de error
	 */
	public String toString() {
		return "El año introducido no es correcto.";
		
	}
	
	/**
	 * Devuelve un mensaje de error
	 */
	public String getMessage() {
		return "El año introducido no es correcto.";
		
	}

}
