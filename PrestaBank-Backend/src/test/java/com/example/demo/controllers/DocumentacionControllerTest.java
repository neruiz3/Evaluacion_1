package com.example.demo.controllers;

import com.example.demo.entities.DocumentacionEntity;
import com.example.demo.services.DocumentacionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DocumentacionController.class)
public class DocumentacionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentacionService documentacionService;

    @Test
    public void nuevoDocumento_ShouldReturnDocumentacionEntity() throws Exception {
        // Given
        DocumentacionEntity documento = new DocumentacionEntity();
        documento.setRut("12345678-9");
        documento.setComprobanteIngresos("test".getBytes());

        when(documentacionService.guardaDocumento(any(DocumentacionEntity.class))).thenReturn(documento);

        // When & Then
        mockMvc.perform(multipart("/api/v1/documentacion/")
                        .file("comprobanteIngresos", "test".getBytes())
                        .param("rut", "12345678-9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value("12345678-9"))
                .andExpect(jsonPath("$.comprobanteIngresos").value(Base64.getEncoder().encodeToString("test".getBytes())));
    }

    @Test
    public void ActualizaDocumento_ShouldReturnDocumentacionEntity() throws Exception {
        // Given
        DocumentacionEntity documento = new DocumentacionEntity();
        documento.setId(1L);
        documento.setRut("12345678-9");
        documento.setComprobanteIngresos("test".getBytes());

        when(documentacionService.actualizaDocumento(any(DocumentacionEntity.class))).thenReturn(documento);

        // When & Then
        mockMvc.perform(multipart("/api/v1/documentacion/actualiza")
                        .file("comprobanteIngresos", "test".getBytes())
                        .param("rut", "12345678-9")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value("12345678-9"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.comprobanteIngresos").value(Base64.getEncoder().encodeToString("test".getBytes())));
    }

    @Test
    public void obtenerDocumentacionPorRut_ShouldReturnDocumentacionDTO() throws Exception {
        // Given
        DocumentacionEntity documento = new DocumentacionEntity();
        documento.setId(1L);
        documento.setRut("12345678-9");
        documento.setComprobanteIngresos("test".getBytes());
        documento.setEscrituraVivienda("escritura".getBytes());
        //se inicializan los campos que se quieran

        // Simulamos que el servicio devuelve el documento
        when(documentacionService.getByRut("12345678-9")).thenReturn(Optional.of(documento));

        // When & Then
        mockMvc.perform(get("/api/v1/documentacion/12345678-9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value("12345678-9"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.comprobanteIngresos").value(Base64.getEncoder().encodeToString("test".getBytes())))
                .andExpect(jsonPath("$.escrituraVivienda").value(Base64.getEncoder().encodeToString("escritura".getBytes())));
    }
}
