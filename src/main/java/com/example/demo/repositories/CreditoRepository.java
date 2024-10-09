package com.example.demo.repositories;

import com.example.demo.entities.CreditoEntity;
import com.example.demo.entities.DocumentacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditoRepository extends JpaRepository<CreditoEntity, Long> {
}
