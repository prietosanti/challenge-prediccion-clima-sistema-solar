package com.prietosanti.component;

import com.prietosanti.entity.Clima;
import com.prietosanti.model.ClimaModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component("climaConverter")
public class ClimaConverter {

    // Entity -> Model
    public ClimaModel toModel(Clima climaEntity) {
        return ClimaModel.of(
                climaEntity.getDia(),
                climaEntity.getTipoClima()
        );
    }

    // Model -> Entity
    public Clima toEntity(ClimaModel climaModel) {
        return new Clima(
          climaModel.getDia(),
          climaModel.getTipoClima()
        );
    }

    // List<Entity> -> List<Model>
    public List<ClimaModel> toModelList(List<Clima> climas) {
        return climas
                .stream()
                .map(clima -> toModel(clima))
                .collect(Collectors.toList());
    }

    // List<Model> -> List<Entity>
    public List<Clima> toEntityList(List<ClimaModel> climasModel) {
        return climasModel
                .stream()
                .map(climaModel -> toEntity(climaModel))
                .collect(Collectors.toList());
    }


}
