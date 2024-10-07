package com.example.demo.services;

import com.example.demo.entities.ClienteEntity;
import com.example.demo.entities.SolicitudEntity;
import com.example.demo.repositories.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitudService {

    @Autowired
    SolicitudRepository solicitudRepository;

    public SolicitudEntity guardaSolicitud(SolicitudEntity solicitud){
        return solicitudRepository.save(solicitud);
    }
}
