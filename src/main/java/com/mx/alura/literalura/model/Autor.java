package com.mx.alura.literalura.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "AUTORES")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros  = new ArrayList<>();

    public Autor() {

    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaFallecimiento = datosAutor.fechaFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        if (libros == null) {
            this.libros = new ArrayList<>();
        } else {
            this.libros = new ArrayList<>(libros);
        }
        this.libros.forEach(l -> l.setAutor(this));
        this.libros = libros;
    }

    @Override
    public String toString() {
        String librosTitulo = libros.stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(", "));
        return "***********AUTOR***********\n" +
                "Autor: " + nombre + "\n" +
                "Fecha de Nacimiento: " + fechaNacimiento + "\n" +
                "Fecha de Fallecimiento: " + fechaFallecimiento + "\n" +
                "Libros: " + librosTitulo + "\n" +
                "**************************\n";
    }
}
