package cursos.excepciones;

/**
 * Clase excepcion que controla la descripcion ingrsado
 */
public class DescripcionErrorException extends Exception{
	
	/**
	 * Devuelve un mensaje de error
	 */
	public String toString() {
		return "La descripción introducida no es correcta.";
		
	}
	
	/**
	 * Devuelve un mensaje de error
	 */
	public String getMessage() {
		return "La descripciñon introducida no es correcta.";
		
	}

}
