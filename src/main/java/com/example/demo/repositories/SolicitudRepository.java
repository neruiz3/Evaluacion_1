package com.example.demo.repositories;

import com.example.demo.entities.SimulacionEntity;
import com.example.demo.entities.SolicitudEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<SolicitudEntity, Long> {
}
