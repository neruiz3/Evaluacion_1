package com.example.demo.services;
import com.example.demo.entities.ClienteEntity;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public ClienteEntity guardaCliente(ClienteEntity cliente){
        return clienteRepository.save(cliente);
    }



}
