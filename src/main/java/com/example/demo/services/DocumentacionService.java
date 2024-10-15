package com.example.demo.services;

import com.example.demo.TipoPrestamo;
import com.example.demo.entities.ClienteEntity;
import com.example.demo.entities.DocumentacionEntity;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.DocumentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentacionService {
    @Autowired
    DocumentacionRepository documentacionRepository;

    public boolean compruebaDocumentos (TipoPrestamo tipoPrestamo, String rutCliente) {
        DocumentacionEntity documentos = documentacionRepository.findByRut(rutCliente);
        if (!(documentos.getComprobanteIngresos().length > 0 && documentos.getCertificadoAvaluo().length > 0)) {
            return false;
        }
        switch(tipoPrestamo){
            case PRIMERAVIVIENDA -> {
                if(documentos.getHistorialCrediticio().length>0){
                    return true;
                }
            }
            case SEGUNDAVIVIENDA -> {
                if (documentos.getEscrituraVivienda().length > 0 && documentos.getHistorialCrediticio().length > 0) {
                    return true;
                }
            }
            case COMERCIAL -> {
                if (documentos.getPlanNegocio().length > 0 && documentos.getEstadoNegocio().length > 0) {
                    return true;
                }
            }
            case REMODELACION -> {
                if (documentos.getPresupuestoRemodelacion().length > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public DocumentacionEntity guardaDocumento(DocumentacionEntity documento) {return documentacionRepository.save(documento);}




}
