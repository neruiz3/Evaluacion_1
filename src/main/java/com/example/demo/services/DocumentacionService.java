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
        if (documentos.getComprobanteIngresos() == null || documentos.getCertificadoAvaluo() == null ||
                documentos.getCuentaAhorros() == null || documentos.getFotocopiaRut() == null ||
                documentos.getInformeDeudas() == null || documentos.getCertificadoAntiguedadLaboral() == null)  {
            return false;
        }
        switch(tipoPrestamo){
            case PRIMERAVIVIENDA -> {
                if(documentos.getHistorialCrediticio() != null){
                    return true;
                }
            }
            case SEGUNDAVIVIENDA -> {
                if (documentos.getEscrituraVivienda() != null && documentos.getHistorialCrediticio() != null) {
                    return true;
                }
            }
            case COMERCIAL -> {
                if (documentos.getPlanNegocio() != null && documentos.getEstadoNegocio() != null) {
                    return true;
                }
            }
            case REMODELACION -> {
                if (documentos.getPresupuestoRemodelacion() != null) {
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

        if (documento.getComprobanteIngresos() != null) docAntes.setComprobanteIngresos(documento.getComprobanteIngresos());
        if (documento.getEscrituraVivienda() != null) docAntes.setEscrituraVivienda(documento.getEscrituraVivienda());
        if (documento.getHistorialCrediticio() != null) docAntes.setHistorialCrediticio(documento.getHistorialCrediticio());
        if (documento.getCertificadoAvaluo() != null) docAntes.setCertificadoAvaluo(documento.getCertificadoAvaluo());
        if (documento.getEstadoNegocio() != null) docAntes.setEstadoNegocio(documento.getEstadoNegocio());
        if (documento.getPlanNegocio() != null) docAntes.setPlanNegocio(documento.getPlanNegocio());
        if (documento.getPresupuestoRemodelacion() != null) docAntes.setPresupuestoRemodelacion(documento.getPresupuestoRemodelacion());
        if (documento.getCertificadoAntiguedadLaboral() != null) docAntes.setCertificadoAntiguedadLaboral(documento.getCertificadoAntiguedadLaboral());
        if (documento.getInformeDeudas() != null) docAntes.setInformeDeudas(documento.getInformeDeudas());
        if (documento.getFotocopiaRut() != null) docAntes.setFotocopiaRut(documento.getFotocopiaRut());
        if (documento.getCuentaAhorros() != null) docAntes.setCuentaAhorros(documento.getCuentaAhorros());

        return documentacionRepository.save(docAntes);
    }

    public Optional<DocumentacionEntity> getByRut(String rut) {
        return documentacionRepository.findByRut(rut);
    }




}
