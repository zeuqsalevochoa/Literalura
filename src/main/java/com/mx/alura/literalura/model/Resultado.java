package com.mx.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record Resultado(
        Integer count,
        String next,
        String previous,//,
        @JsonAlias("results") List<DatosLibro> resultados
) {
}
