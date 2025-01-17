package com.mx.alura.literalura.repository;

import com.mx.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    @Query("""
                SELECT a FROM Autor a
                WHERE a.fechaNacimiento < :fecha
                AND (a.fechaFallecimiento > :fecha OR a.fechaFallecimiento IS NULL)
            """)
    List<Autor> buscarAutoresVivosPorFecha(Integer fecha);

    Autor findByNombre(String nombre);
}
