package com.example.demo.repositories;

import com.example.demo.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    public ClienteEntity findByRut(String rut);
}
