package com.example.demo.services;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    //aquí tengo que tener una funcion que me devuelva la cuota mensual me imagino, osea que llame a simulation service

}
