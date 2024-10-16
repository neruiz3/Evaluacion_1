package com.example.demo.services;

import com.example.demo.TipoPrestamo;
import com.example.demo.entities.ClienteEntity;
import com.example.demo.entities.DocumentacionEntity;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.DocumentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentacionService {
    @Autowired
    DocumentacionRepository documentacionRepository;

    public boolean compruebaDocumentos (TipoPrestamo tipoPrestamo, String rutCliente) {

        DocumentacionEntity documentos = documentacionRepository.findByRut(rutCliente).get();
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

    public DocumentacionEntity guardaDocumento(DocumentacionEntity documento) {
        return documentacionRepository.save(documento);
    }

    public DocumentacionEntity actualizaDocumento(DocumentacionEntity documento) {
        DocumentacionEntity docAntes = documentacionRepository.findByRut(documento.getRut()).get();

        if (docAntes.getComprobanteIngresos() != null) documento.setComprobanteIngresos(docAntes.getComprobanteIngresos());
        if (docAntes.getEscrituraVivienda() != null) documento.setEscrituraVivienda(docAntes.getEscrituraVivienda());
        if (docAntes.getHistorialCrediticio() != null) documento.setHistorialCrediticio(docAntes.getHistorialCrediticio());
        if (docAntes.getCertificadoAvaluo() != null) documento.setCertificadoAvaluo(docAntes.getCertificadoAvaluo());
        if (docAntes.getEstadoNegocio() != null) documento.setEstadoNegocio(docAntes.getEstadoNegocio());
        if (docAntes.getPlanNegocio() != null) documento.setPlanNegocio(docAntes.getPlanNegocio());
        if (docAntes.getPresupuestoRemodelacion() != null) documento.setPresupuestoRemodelacion(docAntes.getPresupuestoRemodelacion());
        if (docAntes.getCertificadoAntiguedadLaboral() != null) documento.setCertificadoAntiguedadLaboral(docAntes.getCertificadoAntiguedadLaboral());
        if (docAntes.getInformeDeudas() != null) documento.setInformeDeudas(docAntes.getInformeDeudas());
        if (docAntes.getFotocopiaRut() != null) documento.setFotocopiaRut(docAntes.getFotocopiaRut());
        if (docAntes.getCuentaAhorros() != null) documento.setCuentaAhorros(docAntes.getCuentaAhorros());

        return documentacionRepository.save(documento);
    }

    public Optional<DocumentacionEntity> getByRut(String rut) {
        return documentacionRepository.findByRut(rut);
    }




}
