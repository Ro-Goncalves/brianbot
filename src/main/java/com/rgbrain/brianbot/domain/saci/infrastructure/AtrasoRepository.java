package com.rgbrain.brianbot.domain.saci.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rgbrain.brianbot.domain.saci.core.model.AtrasoComissionado;
import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Atraso;

@Repository
public interface AtrasoRepository extends JpaRepository<Atraso, Long> {
    List<Atraso> findByIdComissionado(Long idComissionado);   
    
    @Query(value = """
        SELECT DISTINCT a.idComissionado
        FROM Atraso a
    """)
    List<AtrasoComissionado> localizarComissionados(); 
}
