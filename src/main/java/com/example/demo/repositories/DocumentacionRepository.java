package com.example.demo.repositories;

import com.example.demo.entities.DocumentacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentacionRepository extends JpaRepository<DocumentacionEntity, Long> {
    public DocumentacionEntity findByRut(String rut);
}
