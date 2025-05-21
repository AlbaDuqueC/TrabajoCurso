package cursos.excepciones;

public class NombreErrorException extends Exception{
	
	public String toString() {
		return "El nombre introducido no es correcto.";
		
	}
	
	public String getMessage() {
		return "El nombre introducido no es correcto.";
		
	}

}
