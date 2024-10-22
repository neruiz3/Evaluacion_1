package com.example.demo.services;
import com.example.demo.entities.ClienteEntity;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

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

    public Boolean compruebaCampos(String rut){
        ClienteEntity cliente = getClienteByRut(rut);
        Boolean camposCompletos = false;

        camposCompletos =  cliente.getIngresos() != null &&
                cliente.getEsMoroso() != null &&
                cliente.getEsIndependiente() != null &&
                cliente.getDeudaTotal() != null &&
                cliente.getSaldo() != null &&
                cliente.getMayorRetiro12() != null &&
                cliente.getSaldoPositivo() != null &&
                cliente.getTiempoCuentaAhorros() != null &&
                cliente.getMayorRetiro6() != null &&
                cliente.getDepositoRegular() != null;

        if(cliente.getEsIndependiente()!=null){
            if (cliente.getEsIndependiente() && cliente.getEsEstable() == null) {
                return false;
            }
            if((!cliente.getEsIndependiente()) && cliente.getAntiguedadLaboral()==null){
                return false;
            }
        }
        if(cliente.getSaldoPositivo()!=null){
            if(cliente.getSaldoPositivo() && cliente.getTotalDepositos()==null){
                return false;
            }
        }
        return camposCompletos;
    }

}
