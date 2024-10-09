package com.example.demo.repositories;

import com.example.demo.entities.CreditoEntity;
import com.example.demo.entities.SimulacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulacionRepository extends JpaRepository<SimulacionEntity, Long> {

}
