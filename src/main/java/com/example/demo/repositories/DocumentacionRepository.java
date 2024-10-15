package com.example.demo.repositories;

import com.example.demo.entities.DocumentacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentacionRepository extends JpaRepository<DocumentacionEntity, Long> {
    public Optional<DocumentacionEntity> findByRut(String rut);
}
