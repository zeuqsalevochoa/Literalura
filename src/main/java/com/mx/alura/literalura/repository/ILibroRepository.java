package com.mx.alura.literalura.repository;

import com.mx.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ILibroRepository extends JpaRepository<Libro, Long> {

    @Query("""
            SELECT l from Libro l
            WHERE l.idioma ILIKE :idioma
            """)
    List<Libro> buscarLibrosPorIdioma(String idioma);

    Libro findByTitulo(String titulo);
}
