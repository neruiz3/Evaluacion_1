package com.example.demo.services;
import com.example.demo.entities.ClienteEntity;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public ClienteEntity guardaCliente(ClienteEntity cliente){
        return clienteRepository.save(cliente);
    }

    public ClienteEntity actualizaCliente(ClienteEntity cliente){
        return clienteRepository.save(cliente);
    }

    public ClienteEntity getClienteByRut(String rut){
        return clienteRepository.findByRut(rut);
    }

    public ClienteEntity getClienteById(Long id){
        return clienteRepository.findById(id).get();
    }

    public ArrayList<ClienteEntity> getClientes(){
        return (ArrayList<ClienteEntity>) clienteRepository.findAll();
    }





}
