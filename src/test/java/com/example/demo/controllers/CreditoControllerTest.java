package com.example.demo.controllers;

import com.example.demo.DTO.CostoDTO;
import com.example.demo.Estado;
import com.example.demo.TipoPrestamo;
import com.example.demo.DTO.TipoPrestamoDTO;
import com.example.demo.entities.CreditoEntity;
import com.example.demo.services.CreditoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreditoController.class)
public class CreditoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditoService creditoService;

    @Test
    public void nuevaSolicitud_ShouldReturnCredito() throws Exception {
        CreditoEntity solicitud = new CreditoEntity(1L,
                "12345678-9",
                2,
                3.5,
                10000.0,
                TipoPrestamo.PRIMERAVIVIENDA,
                120000.0,
                25.0,
                Estado.EN_REVISION_INICIAL

        );

        given(creditoService.creaExpediente(any(CreditoEntity.class))).willReturn(solicitud);

        String solicitudJson = """
            {
               "rut": "12345678-9",
               "plazo": 2,
               "tasaInteres": 3.5,
               "monto": 10000.0,
               "tipoPrestamo": "PRIMERAVIVIENDA",
               "valorPropiedad": 120000.0,
               "cuotaMensual": 25.0,
               "estado": "EN_REVISION_INICIAL"
            }
            """;

        mockMvc.perform(post("/api/v1/credito/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(solicitudJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.monto", is(10000.0)));
    }

    @Test
    public void calculaSimulacion_ShouldReturnCredito() throws Exception {
        CreditoEntity solicitud = new CreditoEntity(1L,
                "12345678-9",
                20,
                4.5,
                100000000.0,
                TipoPrestamo.PRIMERAVIVIENDA,
                120000.0,
                632649.0,
                Estado.EN_REVISION_INICIAL

        );

        given(creditoService.calculaSimulacion(any(CreditoEntity.class))).willReturn(solicitud);

        String solicitudJson = """
            {
               "rut": "12345678-9",
               "plazo": 20,
               "tasaInteres": 4.5,
               "monto": 100000000.0,
               "tipoPrestamo": "PRIMERAVIVIENDA",
               "valorPropiedad": 120000.0,
               "estado": "EN_REVISION_INICIAL"
            }
            """;

        mockMvc.perform(post("/api/v1/credito/calculaSimulacion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(solicitudJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cuotaMensual", is(632649.0)));
    }

    @Test
    public void revisaInicial_ShouldReturnCredito() throws Exception {
        CreditoEntity solicitud = new CreditoEntity(1L,
                "12345678-9",
                20,
                4.5,
                100000000.0,
                TipoPrestamo.PRIMERAVIVIENDA,
                120000.0,
                632649.0,
                Estado.EN_EVALUACION

        );

        given(creditoService.revisionInicial(any(CreditoEntity.class))).willReturn(solicitud);

        String solicitudJson = """
            {
               "rut": "12345678-9",
               "plazo": 20,
               "tasaInteres": 4.5,
               "monto": 100000000.0,
               "tipoPrestamo": "PRIMERAVIVIENDA",
               "valorPropiedad": 120000.0,
               "estado": "EN_REVISION_INICIAL"
            }
            """;

        mockMvc.perform(put("/api/v1/credito/revisaInicial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(solicitudJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado", is("EN_EVALUACION")));
    }

    @Test
    public void evaluaCredito_ShouldReturnCredito() throws Exception {
        CreditoEntity credito = new CreditoEntity(1L,
                "12345678-9",
                20,
                4.5,
                100000000.0,
                TipoPrestamo.PRIMERAVIVIENDA,
                120000.0,
                632649.0,
                Estado.APROBADA

        );

        given(creditoService.evaluacionCredito(any(CreditoEntity.class))).willReturn(credito);

        String solicitudJson = """
            {
               "rut": "12345678-9",
               "plazo": 20,
               "tasaInteres": 4.5,
               "monto": 100000000.0,
               "tipoPrestamo": "PRIMERAVIVIENDA",
               "valorPropiedad": 120000.0,
               "estado": "EN_EVALUACION"
            }
            """;

        mockMvc.perform(put("/api/v1/credito/evaluar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(solicitudJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado", is("APROBADA")));
    }

    @Test
    public void cambiaEstado_ShouldReturnCredito() throws Exception {
        CreditoEntity credito = new CreditoEntity(1L,
                "12345678-9",
                20,
                4.5,
                100000000.0,
                TipoPrestamo.PRIMERAVIVIENDA,
                120000.0,
                632649.0,
                Estado.APROBADA

        );

        given(creditoService.cambioEstado(Mockito.anyLong(), any(Estado.class))).willReturn(credito);

        String solicitudJson = """
               "APROBADA"
            """;

        mockMvc.perform(put("/api/v1/credito/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(solicitudJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado", is("APROBADA")));
    }

    @Test
    public void getCreditos_ShouldReturnCreditos() throws Exception {
        CreditoEntity credito1 = new CreditoEntity(1L,
                "12345678-9",
                20,
                4.5,
                100000000.0,
                TipoPrestamo.PRIMERAVIVIENDA,
                120000.0,
                632649.0,
                Estado.APROBADA
        );

        CreditoEntity credito2 = new CreditoEntity(2L,
                "1112222-9",
                200,
                4.0,
                100000000.0,
                TipoPrestamo.PRIMERAVIVIENDA,
                120000.0,
                632649.0,
                Estado.RECHAZADA
        );

        List<CreditoEntity> creditos = new ArrayList<>(Arrays.asList(credito1, credito2));

        given(creditoService.getCreditos()).willReturn((ArrayList<CreditoEntity>) creditos);

        mockMvc.perform(get("/api/v1/credito/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].rut", is("12345678-9")))
                .andExpect(jsonPath("$[1].rut", is("1112222-9")));
    }

    @Test
    public void deleteCredito_ShouldReturn200() throws Exception {
        when(creditoService.eliminaCredito(1L)).thenReturn(true); // Assuming the method returns a boolean
        mockMvc.perform(delete("/api/v1/credito/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getCreditoById_ShouldReturnCredito() throws Exception {
        CreditoEntity credito = new CreditoEntity(1L,
                "12345678-9",
                20,
                4.5,
                100000000.0,
                TipoPrestamo.PRIMERAVIVIENDA,
                120000.0,
                632649.0,
                Estado.APROBADA
        );

        given(creditoService.getCredito(1L)).willReturn(credito);

        mockMvc.perform(get("/api/v1/credito/id/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.rut", is("12345678-9")));
    }

    @Test
    public void getCreditosByRut_ShouldReturnCreditos() throws Exception {
        CreditoEntity credito1 = new CreditoEntity(1L,
                "12345678-9",
                20,
                4.5,
                100000000.0,
                TipoPrestamo.PRIMERAVIVIENDA,
                120000.0,
                632649.0,
                Estado.APROBADA
        );

        CreditoEntity credito2 = new CreditoEntity(2L,
                "12345678-9",
                15,
                4,
                100000000.0,
                TipoPrestamo.COMERCIAL,
                125000.0,
                0.0,
                Estado.APROBADA
        );

        List<CreditoEntity> creditos = new ArrayList<>(Arrays.asList(credito1, credito2));

        given(creditoService.getCreditosCliente("12345678-9")).willReturn((ArrayList<CreditoEntity>) creditos);

        mockMvc.perform(get("/api/v1/credito/cliente/{rut}", "12345678-9"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].plazo", is(20)))
                .andExpect(jsonPath("$[1].plazo", is(15)));
    }

    @Test
    public void obtenerTiposPrestamo_ShouldReturnListOfTiposPrestamo() throws Exception {
        // Crear datos simulados
        TipoPrestamoDTO tipo1 = new TipoPrestamoDTO(
                "PRIMERAVIVIENDA",
                30,
                80,
                3.5,
                5.0,
                true,
                true,
                true,
                false,
                false,
                false,
                false
        );
        TipoPrestamoDTO tipo2 = new TipoPrestamoDTO(
                "COMERCIAL",
                25,
                60,
                5.0,
                7.0,
                true,
                true,
                false,
                false,
                true,
                true,
                false
        );
        List<TipoPrestamoDTO> tiposPrestamo = new ArrayList<>(Arrays.asList(tipo1, tipo2));

        // Mockear la respuesta del servicio
        given(creditoService.obtenerTiposPrestamo()).willReturn(tiposPrestamo);

        // Realizar la solicitud HTTP GET y verificar la respuesta
        mockMvc.perform(get("/api/v1/credito/tipo-prestamo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("PRIMERAVIVIENDA")))
                .andExpect(jsonPath("$[1].nombre", is("COMERCIAL")));
    }

    @Test
    public void costoTotal_ShouldReturnCostoDTO() throws Exception {
        // Crear un objeto CreditoEntity de prueba
        CreditoEntity credito = new CreditoEntity(
                1L,
                "12345678-9",
                20,
                4.5,
                100000000.0,
                TipoPrestamo.PRIMERAVIVIENDA,
                120000.0,
                632649.0,
                Estado.APROBADA
        );

        // Crear un CostoDTO esperado como respuesta
        CostoDTO costoEsperado = new CostoDTO(
                632649.0,
                20000000.0,
                100.0,
                20.0,
                1000.0,
                1000.0
        );

        // Mockear el comportamiento del servicio
        given(creditoService.calculaCostoTotal(any(CreditoEntity.class)))
                .willReturn(costoEsperado);

        String solicitudJson = """
        {
           "rut": "12345678-9",
           "plazo": 20,
           "tasaInteres": 4.5,
           "monto": 100000000.0,
           "tipoPrestamo": "PRIMERAVIVIENDA",
           "valorPropiedad": 120000.0,
           "cuotaMensual": 632649.0,
           "estado": "APROBADA"
        }
        """;

        // Realizar la solicitud POST y verificar la respuesta
        mockMvc.perform(post("/api/v1/credito/costoTotal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(solicitudJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.costoTotal").value(1000.0))
                .andExpect(jsonPath("$.comisionAdmin").value(20000000.0))
                .andExpect(jsonPath("$.cuotaMensual").value(632649.0))
                .andExpect(jsonPath("$.seguroIncendio").value(20.0))
                .andExpect(jsonPath("$.seguroDesgravamen").value(100.0))
                .andExpect(jsonPath("$.costoMensual").value(1000.0));
    }
}
