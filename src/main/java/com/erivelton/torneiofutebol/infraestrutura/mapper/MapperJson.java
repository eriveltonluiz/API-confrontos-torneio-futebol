package com.erivelton.torneiofutebol.infraestrutura.mapper;

import com.erivelton.torneiofutebol.aplicacao.dto.DadosEquipe;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapperJson {

    public List<DadosEquipe> transformarEmObjeto() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> list = mapper.readValue(new File("src/main/resources/teste_torneio.json"), List.class);
        List<DadosEquipe> dadosEquipes = list.stream().
                map(item -> mapper.convertValue(item, DadosEquipe.class))
                .collect(Collectors.toList());
        return dadosEquipes;
    }

}
