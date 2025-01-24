package com.rgbrain.brianbot.domain.saci.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Consorciado;

public interface ConsorciadoRepository extends JpaRepository<Consorciado, Long> {
    
}
