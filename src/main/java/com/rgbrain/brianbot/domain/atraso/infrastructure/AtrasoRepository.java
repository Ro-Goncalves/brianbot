package com.rgbrain.brianbot.domain.atraso.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgbrain.brianbot.domain.atraso.infrastructure.entity.Atraso;

@Repository
public interface AtrasoRepository extends JpaRepository<Atraso, Long> {
    List<Atraso> findByIdComissionado(Long idComissionado);    
}
