package cursos.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cursos.dao.CursoDAO;
import cursos.entidades.Curso;
import cursos.excepciones.AñoErrorException;
import cursos.excepciones.DescripcionErrorException;
import cursos.excepciones.NombreErrorException;

public class PrincipalCurso {

	public static void main(String[] args) {

		// Creacion del scanner
		Scanner sc = new Scanner(System.in);

		// Atributo int del id del profesor
		int idProfesor;

		// Atributo int del id del curso
		int idCurso;

		// Atributo String del nombre del curso
		String nombreCurso;

		// Atributo String de la descripcion del curso
		String descripcionCurso;

		// Atributo int del año del curso
		int añoCurso;

		// Atributo int de la seleccion del menu
		int seleccion;

		// El bucle acaba al seleccionar el valor 0 en el atributo seleccion
		do {

			// Mostrara el menu
			menu();

			// Introduce el usuario la seleccion por consola
			seleccion = sc.nextInt();
			sc.nextLine();

			// Switch de seleccion
			switch (seleccion) {

			// Caso 1
			case 1 -> {

				// Pide por pantalla que inserte los siguiente datos
				System.out.println("Inserte los siguientes datos:");

				// Pide por pantalla el nombre
				System.out.println("Nombre");

				// Introduce el usuario el nombre del curso por consola
				nombreCurso = sc.next();

				sc.nextLine();

				// Pide por pantalla la descripcion
				System.out.println("Descripcion");

				// Introduce el usuario la descripcion del curso por consola
				descripcionCurso = sc.next();

				sc.nextLine();

				// Pide por pantalla el año
				System.out.println("Año");

				// Introduce el usuario el año del curso por consola
				añoCurso = sc.nextInt();

				try {

					// Creamos el objeto Curso
					Curso c = new Curso(nombreCurso, descripcionCurso, añoCurso);

					// Creamos el objeto CursoDAO
					CursoDAO dao = new CursoDAO();
					if (dao.create(c)) {
						// Muestra por pantalla que se ha podido crear el curso
						System.out.println("Fue bien creada el nuevo curso e introducida en la base de datos");
					} else {
						// Muestra por pantalla que no se ha podido crear el curso
						System.out.println("No se pudo introducir el nuevo curso en la base de datos");
					}

				} // Si es erroneo el nombre
				catch (NombreErrorException e) {

					// Imprime el error
					System.out.println(e.getMessage());

				} // Si es erroneo la descripcion
				catch (DescripcionErrorException e) {

					// Imprime el error
					System.out.println(e.getMessage());

				} // Si es erroneo el año
				catch (AñoErrorException e) {

					// Imprime el error
					System.out.println(e.getMessage());
				}

			}
			// Caaso 2
			case 2 -> {

				// Creamos el objeto CursoDAO
				CursoDAO dao = new CursoDAO();

				// Creamos una nueva lista
				List<Curso> lista = new ArrayList<Curso>();

				// Obtenemos el valor de la funcion listarCurso y lo introducimos en la lista
				lista = dao.listarCursos();

				// Vamos recorriendo e imprimiendo la lsita
				for (Curso cur : lista) {

					// Muestra por pantalla la lista de cursos
					System.out.println(cur);

				}

			}
			// Caso 3
			case 3 -> {

				// Pide por pantalla que inserte los ids de curso y profesor
				System.out.println("Inserta los ids de curso y profesor");

				// Pide por pantalla que inserte el id de curso
				System.out.println("Curso: ");

				// Introduce el usuario el id del curso por consola
				idCurso = sc.nextInt();

				// Pide por pantalla que insertes el is de profesor
				System.out.println("Profesor: ");

				// Introduce el usuario el id del profesor por consola
				idProfesor = sc.nextInt();

				// Creamos el objeto CursoDAO
				CursoDAO cur = new CursoDAO();

				// Si se asigna correctamente
				if (cur.asignarProfesorCurso(idProfesor, idCurso) > 0) {

					// Muestra por pantalla si se ha podido asignar
					System.out.println("Se asigno correctamente");

				} else {
					// Muestra por pantalla si no se ha podido asignar
					System.out.println("No se asigno correctamente");
				}

			}
			// Case 4
			case 4 -> {

				// Pide por pantalla que introduzcas el id del curso
				System.out.println("Introduce id curso");

				// Introduce el usuario el id del curso por consola
				idCurso = sc.nextInt();

				// Creamos el objeto CursoDAO
				CursoDAO cur = new CursoDAO();

				List<String> lista = cur.listaEstudiantesCurso(idCurso);

				// Muestra por pantalla la lista de todos los estudiante de un curso
				for (String i : lista) {

					System.out.println(i);

				}

			}
			case 5 -> {

				// Pide por pantalla que introduzcas el id del curso
				System.out.println("Introduce el id del curso que quieras eliminar: ");

				// Introduce el usuario el id del curso por consola
				idCurso = sc.nextInt();

				// Creamos el objeto CursoDAO
				CursoDAO cur = new CursoDAO();

				// Si se ha podido eliminar
				if (cur.eliminar(idCurso)) {

					// Muestra por pantalla si fue eliminado
					System.out.println("Fue elimando con exito");
				} else {

					// Muestra por pantalla si no pudo ser eliminado
					System.out.println("No se pudo eliminar");
				}

			}
			// Caso 0
			case 0 -> {

				// Muestra por pantalla que esta saliendo del programa
				System.out.println("--Saliendo--");

			}
			}

		} while (seleccion != 0);

		// Cierre del scanner
		sc.close();

	}

	// Funcion que imprime el menu
	public static void menu() {
		System.out.println("1. Crear curso.");
		System.out.println("2. Listar todos los cursos.");
		System.out.println("3. Asignar un profesor a un curso.");
		System.out.println("4. Listar estudiantes de un curso.");
		System.out.println("5. Eliminar curso.");
		System.out.println("0. Salir.");
	}

}
