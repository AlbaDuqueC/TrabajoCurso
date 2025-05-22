package cursos.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cursos.entidades.Curso;
import cursos.excepciones.AñoErrorException;
import cursos.excepciones.DescripcionErrorException;
import cursos.excepciones.NombreErrorException;
import cursos.utils.CursoDAO;

public class PrincipalCurso {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int idProfesor;

		int idCurso;
		String nombreCurso;
		String descripcionCurso;
		int añoCurso;

		int seleccion;

		do {

			menu();

			seleccion = sc.nextInt();

			switch (seleccion) {
			case 1 -> {

				System.out.println("Inserte los siguientes datos:");
				System.out.println("Nombre");
				nombreCurso = sc.nextLine();
				System.out.println("Descripcion");
				descripcionCurso = sc.nextLine();
				System.out.println("Año");
				añoCurso = sc.nextInt();

				try {
					Curso c = new Curso(nombreCurso, descripcionCurso, añoCurso);
					CursoDAO dao = new CursoDAO();
					dao.create(c);

				} catch (NombreErrorException e) {
					System.out.println(e.getMessage());

				} catch (DescripcionErrorException e) {
					System.out.println(e.getMessage());
				} catch (AñoErrorException e) {
					System.out.println(e.getMessage());
				}

			}
			case 2 -> {
				CursoDAO dao = new CursoDAO();
				List<Curso> lista = new ArrayList<Curso>();

				lista = dao.listarCursos();

				for (Curso cur : lista) {

					System.out.println(cur);

				}

			}
			case 3 -> {
				System.out.println("Inserta los ids de curso y profesor");
				System.out.println("Curso: ");

				idCurso = sc.nextInt();

				System.out.println("Profesor: ");

				idProfesor = sc.nextInt();

				CursoDAO cur = new CursoDAO();

				cur.asignarProfesorCurso(idProfesor, idCurso);

			}
			case 4 -> {

				System.out.println("Introduce id curso");
				idCurso = sc.nextInt();
				CursoDAO cur = new CursoDAO();

				System.out.println(cur.listaEstudiantesCurso(idCurso));

			}
			case 5 -> {
				
				System.out.println("Introduce el id del curso que quieras eliminar: ");
				idCurso=sc.nextInt();
				
				CursoDAO cur = new CursoDAO();
				
				cur.eliminar(idCurso);

			}
			case 0 -> {

				System.out.println("--Saliendo--");
				
			}
			}

		} while (seleccion != 0);
		
		sc.close();

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
