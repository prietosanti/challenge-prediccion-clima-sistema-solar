package com.prietosanti.repository;

import com.prietosanti.entity.Clima;
import com.prietosanti.model.TipoClima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("prediccionClimaticaRepository")
public interface PrediccionClimaticaRepository extends JpaRepository<Clima, Serializable> {

    Clima findByDia(int dia);
    List<Clima> findAllByTipoClima(TipoClima tipoClima);
}
