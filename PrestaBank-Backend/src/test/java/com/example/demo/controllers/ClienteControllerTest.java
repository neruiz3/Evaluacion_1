package com.example.demo.controllers;

import com.example.demo.entities.ClienteEntity;
import com.example.demo.services.ClienteService;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void nuevoCliente_ShouldReturnCliente() throws Exception {
        ClienteEntity cliente = new ClienteEntity(1L,
                "12345678-9",
                "Nerea",
                "Ruiz",
                23,
                1000.0,
                true,
                true,
                true,
                2,
                1000.0,
                "estable",
                1000.0,
                1000.0,
                false,
                1,
                1000.0,
                false,
                1000.0
        );

        given(clienteService.guardaCliente(Mockito.any(ClienteEntity.class))).willReturn(cliente);

        String clienteJson = """
            {
               "rut": "12345678-9",
               "nombre": "Nerea",
               "apellidos": "Ruiz",
               "edad": 23,
               "ingresos": 1000.0,
               "esMoroso": true,
               "esIndependiente": true,
               "esEstable": true,
               "antiguedadLaboral":  2,
               "deudaTotal": 1000.0,
               "capacidadAhorro": "estable",
               "saldo": 1000.0,
               "mayorRetiro12": 1000.0,
               "saldoPositivo": false,
               "tiempoCuentaAhorros": 1,
               "mayorRetiro6": 1000.0,
               "depositoRegular": false,
               "totalDepositos": 1000.0 
            }
            """;

        mockMvc.perform(post("/api/v1/cliente/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Nerea")));
    }

    @Test
    public void actualizaCliente_ShouldReturnCliente() throws Exception {
        ClienteEntity cliente = new ClienteEntity(1L,
                "28.519.211-0",
                "Nerea",
                "Ruiz",
                23,
                1000.0,
                true,
                true,
                true,
                2,
                1000.0,
                "estable",
                1000.0,
                1000.0,
                false,
                1,
                1000.0,
                false,
                1000.0
        );

        given(clienteService.actualizaCliente(Mockito.any(ClienteEntity.class))).willReturn(cliente);

        String clienteJson = """
            {
               "rut": "28.519.211-0",
               "nombre": "Nerea",
               "apellidos": "Ruiz",
               "edad": 23,
               "ingresos": 1000.0,
               "esMoroso": true,
               "esIndependiente": true,
               "esEstable": true,
               "antiguedadLaboral":  2,
               "deudaTotal": 1000.0,
               "capacidadAhorro": "estable",
               "saldo": 1000.0,
               "mayorRetiro12": 1000.0,
               "saldoPositivo": false,
               "tiempoCuentaAhorros": 1,
               "mayorRetiro6": 1000.0,
               "depositoRegular": false,
               "totalDepositos": 1000.0
            }
            """;

        mockMvc.perform(put("/api/v1/cliente/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Nerea")));
    }

    @Test
    public void listaClientes_ShouldReturnClientes() throws Exception {
        ClienteEntity cliente1 = new ClienteEntity(1L,
                "1234567-8",
                "Nerea",
                "Ruiz",
                23,
                1000.0,
                true,
                true,
                true,
                2,
                1000.0,
                "estable",
                1000.0,
                1000.0,
                false,
                1,
                1000.0,
                false,
                1000.0
        );

        ClienteEntity cliente2 = new ClienteEntity(1L,
                "28.519.211-0",
                "Marina",
                "Ruiz",
                21,
                2000.0,
                false,
                true,
                true,
                2,
                1000.0,
                "estable",
                1000.0,
                1000.0,
                false,
                1,
                1000.0,
                false,
                1000.0
        );

        List<ClienteEntity> clientes = new ArrayList<>(Arrays.asList(cliente1, cliente2));

        given(clienteService.getClientes()).willReturn((ArrayList<ClienteEntity>) clientes);

        mockMvc.perform(get("/api/v1/cliente/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Nerea")))
                .andExpect(jsonPath("$[1].nombre", is("Marina")));
    }

    @Test
    public void getClienteById_ShouldReturnCliente() throws Exception {
        ClienteEntity cliente = new ClienteEntity(1L,
                "1234567-8",
                "Nerea",
                "Ruiz",
                23,
                1000.0,
                true,
                true,
                true,
                2,
                1000.0,
                "estable",
                1000.0,
                1000.0,
                false,
                1,
                1000.0,
                false,
                1000.0
        );

        given(clienteService.getClienteById(1L)).willReturn(cliente);

        mockMvc.perform(get("/api/v1/cliente/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre", is("Nerea")));
    }

    @Test
    public void getClienteByRut_ShouldReturnCliente() throws Exception {
        ClienteEntity cliente = new ClienteEntity(1L,
                "1234567-8",
                "Nerea",
                "Ruiz",
                23,
                1000.0,
                true,
                true,
                true,
                2,
                1000.0,
                "estable",
                1000.0,
                1000.0,
                false,
                1,
                1000.0,
                false,
                1000.0
        );

        given(clienteService.getClienteByRut("1234567-8")).willReturn(cliente);

        mockMvc.perform(get("/api/v1/cliente/rut/{rut}", "1234567-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre", is("Nerea")));
    }


}



