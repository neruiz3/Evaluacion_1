package com.example.demo.repositories;

import com.example.demo.entities.CreditoEntity;
import com.example.demo.entities.DocumentacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CreditoRepository extends JpaRepository<CreditoEntity, Long> {
    public ArrayList<CreditoEntity> findByRut(String rut);
}
