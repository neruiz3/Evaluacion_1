package com.example.demo.services;

import com.example.demo.TipoPrestamo;
import com.example.demo.entities.DocumentacionEntity;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.DocumentacionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class DocumentacionServiceTest {
    @MockBean
    DocumentacionRepository documentacionRepository;

    @Autowired
    DocumentacionService documentacionService;

    @Test
    public void whenGuardaDocumento_ThenDocumentacionEntity() {
        DocumentacionEntity documentacion = new DocumentacionEntity();
        documentacion.setId(1L);
        documentacion.setRut("12345678-9");
        documentacion.setComprobanteIngresos("test".getBytes());
        // Given
        when(documentacionRepository.save(any(DocumentacionEntity.class))).thenReturn(documentacion);

        // When
        DocumentacionEntity savedDocumento = documentacionService.guardaDocumento(documentacion);

        // Then
        assertNotNull(savedDocumento);
        assertEquals("12345678-9", savedDocumento.getRut());
        assertEquals(1L, savedDocumento.getId());
        assertTrue(Arrays.equals("test".getBytes(), savedDocumento.getComprobanteIngresos()));
    }

    @Test
    public void whenActualizaDocumento_ThenDocumentacionEntity() {
        // Given
        // Inicializa un documento existente en el repositorio
        DocumentacionEntity documentoExistente = new DocumentacionEntity();
        documentoExistente.setId(1L);
        documentoExistente.setRut("12345678-9");
        documentoExistente.setComprobanteIngresos("test1".getBytes());

        DocumentacionEntity documentoActualizar = new DocumentacionEntity();
        documentoActualizar.setRut("12345678-9");
        documentoActualizar.setHistorialCrediticio("test2".getBytes());

        when(documentacionRepository.findByRut("12345678-9")).thenReturn(Optional.of(documentoExistente));
        when(documentacionRepository.save(any(DocumentacionEntity.class))).thenReturn(documentoExistente);

        // When
        DocumentacionEntity updatedDocumento = documentacionService.actualizaDocumento(documentoActualizar);

        // Then
        assertNotNull(updatedDocumento);
        assertThat(updatedDocumento.getId()).isEqualTo(documentoExistente.getId());
        assertThat(updatedDocumento.getRut()).isEqualTo(documentoExistente.getRut());
        assertArrayEquals("test1".getBytes(), updatedDocumento.getComprobanteIngresos());
        assertArrayEquals("test2".getBytes(), updatedDocumento.getHistorialCrediticio()); // Verifica que el comprobante de ingresos se ha actualizado
    }

    @Test
    public void whenGetByRut_ThenReturnDocumentacionEntity() {
        // Dado
        DocumentacionEntity documentoExistente = new DocumentacionEntity();
        documentoExistente.setId(1L);
        documentoExistente.setRut("12345678-9");

        when(documentacionRepository.findByRut("12345678-9")).thenReturn(Optional.of(documentoExistente));

        // Cuando
        Optional<DocumentacionEntity> resultado = documentacionService.getByRut("12345678-9");

        // Entonces
        assertTrue(resultado.isPresent());
        assertEquals("12345678-9",resultado.get().getRut());
    }

    @Test
    public void whenComprobandoPrimeraVivienda_ThenReturnTrue() {
        // Dado
        DocumentacionEntity documentos = new DocumentacionEntity();
        documentos.setHistorialCrediticio("historial".getBytes());
        documentos.setComprobanteIngresos("ingresos".getBytes());
        documentos.setCertificadoAvaluo("avaluo".getBytes());
        documentos.setCuentaAhorros("ahorros".getBytes());
        documentos.setFotocopiaRut("fotocopia".getBytes());
        documentos.setInformeDeudas("deudas".getBytes());
        documentos.setCertificadoAntiguedadLaboral("antiguedad".getBytes());

        when(documentacionRepository.findByRut("12345678-9")).thenReturn(Optional.of(documentos));

        // Cuando
        boolean resultado = documentacionService.compruebaDocumentos(TipoPrestamo.PRIMERAVIVIENDA, "12345678-9");

        // Entonces
        assertTrue(resultado);
    }

    @Test
    public void whenSegundaVivienda_ThenReturnTrue() {
        // Dado
        DocumentacionEntity documentos = new DocumentacionEntity();
        documentos.setHistorialCrediticio("historial".getBytes());
        documentos.setEscrituraVivienda("escritura".getBytes());
        documentos.setComprobanteIngresos("ingresos".getBytes());
        documentos.setCertificadoAvaluo("avaluo".getBytes());
        documentos.setCuentaAhorros("ahorros".getBytes());
        documentos.setFotocopiaRut("fotocopia".getBytes());
        documentos.setInformeDeudas("deudas".getBytes());
        documentos.setCertificadoAntiguedadLaboral("antiguedad".getBytes());

        when(documentacionRepository.findByRut("12345678-9")).thenReturn(Optional.of(documentos));

        boolean resultado = documentacionService.compruebaDocumentos(TipoPrestamo.SEGUNDAVIVIENDA, "12345678-9");

        assertTrue(resultado);
    }

    @Test
    public void whenComercial_ThenReturnTrue() {
        // Dado
        DocumentacionEntity documentos = new DocumentacionEntity();
        documentos.setPlanNegocio("plan".getBytes());
        documentos.setEstadoNegocio("estado".getBytes());
        documentos.setComprobanteIngresos("ingresos".getBytes());
        documentos.setCertificadoAvaluo("avaluo".getBytes());
        documentos.setCuentaAhorros("ahorros".getBytes());
        documentos.setFotocopiaRut("fotocopia".getBytes());
        documentos.setInformeDeudas("deudas".getBytes());
        documentos.setCertificadoAntiguedadLaboral("antiguedad".getBytes());

        when(documentacionRepository.findByRut("12345678-9")).thenReturn(Optional.of(documentos));

        // Cuando
        boolean resultado = documentacionService.compruebaDocumentos(TipoPrestamo.COMERCIAL, "12345678-9");

        // Entonces
        assertTrue(resultado);
    }

    @Test
    public void whenRemodelacion_ThenReturnTrue() {
        // Dado
        DocumentacionEntity documentos = new DocumentacionEntity();
        documentos.setPresupuestoRemodelacion("presupuesto".getBytes());
        documentos.setComprobanteIngresos("ingresos".getBytes());
        documentos.setCertificadoAvaluo("avaluo".getBytes());
        documentos.setCuentaAhorros("ahorros".getBytes());
        documentos.setFotocopiaRut("fotocopia".getBytes());
        documentos.setInformeDeudas("deudas".getBytes());
        documentos.setCertificadoAntiguedadLaboral("antiguedad".getBytes());

        when(documentacionRepository.findByRut("12345678-9")).thenReturn(Optional.of(documentos));

        // Cuando
        boolean resultado = documentacionService.compruebaDocumentos(TipoPrestamo.REMODELACION, "12345678-9");

        // Entonces
        assertTrue(resultado);
    }

    @Test
    public void whenMissingDocuments_ThenReturnFalse() {
        // Dado
        DocumentacionEntity documentos = new DocumentacionEntity();

        when(documentacionRepository.findByRut("12345678-9")).thenReturn(Optional.of(documentos));

        // Cuando
        Boolean resultado = documentacionService.compruebaDocumentos(TipoPrestamo.PRIMERAVIVIENDA, "12345678-9");

        // Entonces
        assertFalse(resultado);
    }


}