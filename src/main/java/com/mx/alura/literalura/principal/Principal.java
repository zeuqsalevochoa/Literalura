package com.mx.alura.literalura.principal;

import com.mx.alura.literalura.model.Autor;
import com.mx.alura.literalura.model.DatosLibro;
import com.mx.alura.literalura.model.Libro;
import com.mx.alura.literalura.model.Resultado;
import com.mx.alura.literalura.repository.IAutorRepository;
import com.mx.alura.literalura.repository.ILibroRepository;
import com.mx.alura.literalura.service.ConsumoApi;
import com.mx.alura.literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/"; //?search=
    private ILibroRepository libroRepository;
    private IAutorRepository autorRepository;

    public Principal(IAutorRepository autorRepository, ILibroRepository libroRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraMenu(){
        var opcion = -1;
        while(opcion != 0){
            var menu = """
                    1.- Buscar libro por titulo
                    2.- Listar libros registrados
                    3.- Listar autores registrados
                    4.- Listar autores vivos en un determinado año
                    5.- Listar libros por idioma
                    0.- Salir
                    """;
            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion){
                    case 1:
                        buscarLibroWeb();
                        break;
                    case 2:
                        listarLibros();
                        break;
                    case 3:
                        listarAutores();
                        break;
                    case 4:
                        listarAutoresVivos();
                        break;
                    case 5:
                        listarPorIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción invalida");

                }
            } catch (InputMismatchException e){
                System.out.println("Entrada inválida. Por favor ingrese un número.");
                teclado.nextLine();
            }

        }
    }

    private void listarPorIdioma() {
        var msg = """
                            Escribe el idioma que deseas elegir de acuerdo a los siguientes códigos:
                            es - Español
                            en - Inglés
                            fr - Francés
                            pt - Portugués
                            """;
        System.out.println(msg);
        var idioma = teclado.nextLine();
        List<Libro> librosEncontrados = libroRepository.buscarLibrosPorIdioma(idioma);
        if (librosEncontrados.isEmpty()){
            System.out.println("No se encontaron libros en el idioma: " + idioma);
        } else{
            System.out.println(librosEncontrados);
        }

    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año para ver los autores vivos");
        try {
            var fecha = teclado.nextInt();
            List<Autor> autoresVivos = autorRepository.buscarAutoresVivosPorFecha(fecha);
            if (autoresVivos.isEmpty()){
                System.out.println("No se encontraron autores vivos en la fecha: "+fecha);
            } else{
                System.out.println(autoresVivos);
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor ingrese un año en formato numérico.");
            teclado.nextLine();
        }

    }

    private void listarAutores() {
        List<Autor> autoresRegistrados = autorRepository.findAll();
        System.out.println(autoresRegistrados);
    }

    private void listarLibros() {
        List<Libro> librosRegistrados = libroRepository.findAll();
        System.out.println(librosRegistrados);
    }

    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();
        if (datos == null){
            System.out.println("No se ha registrado el libro");
            return;
        }
        var busqueda = libroRepository.findByTitulo(datos.titulo());
        if (busqueda != null ) {
            System.out.println("Libro ya registrado en la base de datos");
            System.out.println(busqueda);
            return;
        }

        // Buscar si el autor ya existe en la base de datos
        Autor autor = autorRepository.findByNombre(datos.autores().get(0).nombre());
        if (autor == null) {
            // Si el autor no existe, crear un nuevo autor
            autor = new Autor(datos.autores().get(0));
        }

        Libro libro = new Libro(datos);

        if (autor.getLibros() == null) {
            autor.setLibros(new ArrayList<>());
        }
        autor.getLibros().add(libro);
        libro.setAutor(autor);         // Asociar el autor al libro

        // Guardar solo el libro si el autor ya existe
        if (autor.getId() != null) {
            libroRepository.save(libro);
        } else {
            // Guardar el nuevo autor y el libro si el autor es nuevo
            autorRepository.save(autor);
            libroRepository.save(libro);
        }
//        Autor autor = new Autor(datos.autores().get(0));
//        Libro libro = new Libro(datos);
//        autor.setLibros(List.of(libro));
//        libro.setAutor(autor);
//        autorRepository.save(autor);
//        libroRepository.save(libro);

        System.out.println("Nuevo libro registrado: ");
        System.out.println(libro);
    }

    private DatosLibro getDatosLibro(){
        System.out.println("Ingrese el nombre del libro: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+"?search="+nombreLibro.replace(" ", "%20"));
        var datos = convierteDatos.obtenerDatos(json, Resultado.class);
        if(datos.resultados().isEmpty()){
            System.out.println("No se encontraron resultados");
            return null;
        } else {
            return datos.resultados().get(0);
        }

    }


}
