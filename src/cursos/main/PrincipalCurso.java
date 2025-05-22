package cursos.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import cursos.entidades.Curso;
import cursos.excepciones.AñoErrorException;
import cursos.excepciones.DescripcionErrorException;
import cursos.excepciones.NombreErrorException;
import cursos.utils.CursoDAO;


public class PrincipalCurso {

	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		
		
		
		int idCurso;
		String nombreCurso;
		String descripcionCurso;
		int añoCurso;
		
		int seleccion;
		
		
		
			
			do {
				
				menu();
				
				seleccion=sc.nextInt();
				
				switch (seleccion) {
				case 1 ->{
					
					
					System.out.println("Inserte los siguientes datos:");
					System.out.println("Nombre");
					nombreCurso=sc.nextLine();
					System.out.println("Descripcion");
					descripcionCurso= sc.nextLine();
					System.out.println("Año");
					añoCurso=sc.nextInt();
					
					try {
						Curso c= new Curso(nombreCurso, descripcionCurso, añoCurso);
						CursoDAO dao= new CursoDAO();
						dao.create(c);
						
					} catch (NombreErrorException e) {
						System.out.println(e.getMessage());
						
					} catch (DescripcionErrorException e) {
						System.out.println(e.getMessage());
					} catch (AñoErrorException e) {
						System.out.println(e.getMessage());
					}
					
					
					
				}
				case 2 ->{
					CursoDAO dao= new CursoDAO();
					List<Curso> lista = new ArrayList<Curso>();
					
					lista=dao.listarCursos();
					
					for(Curso cur: lista) {
						
						System.out.println(cur);
						
					}
					
				}
				case 3 ->{
					
				}
				case 4 ->{
					
				}
				case 5 ->{
					
				}
				case 0 ->{
					
				}
				}
				
			}while(seleccion!=0);
			
			
		

	}
	
	public static void menu() {
		System.out.println("1. Crear curso.");
		System.out.println("2. Listar todos los cursos.");
		System.out.println("3. Asignar un profesor a un curso.");
		System.out.println("4. Listar estudiantes de un curso.");
		System.out.println("5. Eliminar curso.");
		System.out.println("0. Salir.");
	}

}
