package com.example.demo.services;

import com.example.demo.entities.ClienteEntity;
import com.example.demo.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ClienteServiceTest {

    @MockBean
    ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;

    @Test
    public void whenGuardaCliente_thenCorrect() {
        //Given
        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Maria");
        cliente.setApellidos("García");
        cliente.setEdad(25);
        cliente.setIngresos(500.0);
        cliente.setEsMoroso(true);
        cliente.setEsIndependiente(true);
        cliente.setEsEstable(true);
        cliente.setAntiguedadLaboral(1);
        cliente.setDeudaTotal(500.0);
        cliente.setCapacidadAhorro("estable");
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(500000.0);
        cliente.setSaldoPositivo(true);

        //When
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        //Then
        ClienteEntity result = clienteService.guardaCliente(cliente);
        assertNotNull(result);
        assertThat(result.getRut()).isEqualTo("1234566-7");
        assertThat(result.getNombre()).isEqualTo("Maria");
        assertThat(result.getApellidos()).isEqualTo("García");
        assertThat(result.getEdad()).isEqualTo(25);
        assertThat(result.getIngresos()).isEqualTo(500.0);
        assertThat(result.getEsMoroso()).isEqualTo(true);
        assertThat(result.getEsIndependiente()).isEqualTo(true);
        assertThat(result.getEsEstable()).isEqualTo(true);
        assertThat(result.getAntiguedadLaboral()).isEqualTo(1);
        assertThat(result.getDeudaTotal()).isEqualTo(500.0);
        assertThat(result.getCapacidadAhorro()).isEqualTo("estable");
        assertThat(result.getTiempoCuentaAhorros()).isEqualTo(2);
        assertThat(result.getSaldo()).isEqualTo(500.0);
        assertThat(result.getMayorRetiro6()).isEqualTo(500.0);
        assertThat(result.getMayorRetiro12()).isEqualTo(100.0);
        assertThat(result.getDepositoRegular()).isEqualTo(true);
        assertThat(result.getTotalDepositos()).isEqualTo(500000.0);
        assertThat(result.getSaldoPositivo()).isEqualTo(true);
    }

    @Test
    public void whenActualizaCliente_thenCorrect() {
        //Given
        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Maria");
        cliente.setApellidos("García");
        cliente.setEdad(25);
        cliente.setIngresos(500.0);
        cliente.setEsMoroso(true);
        cliente.setEsIndependiente(true);
        cliente.setEsEstable(true);
        cliente.setAntiguedadLaboral(1);
        cliente.setDeudaTotal(500.0);
        cliente.setCapacidadAhorro("estable");
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(500000.0);
        cliente.setSaldoPositivo(true);


        //When
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        //Then
        ClienteEntity result = clienteService.actualizaCliente(cliente);
        assertNotNull(result);
        assertThat(result.getRut()).isEqualTo("1234566-7");
        assertThat(result.getNombre()).isEqualTo("Maria");
        assertThat(result.getApellidos()).isEqualTo("García");
        assertThat(result.getEdad()).isEqualTo(25);
        assertThat(result.getIngresos()).isEqualTo(500.0);
        assertThat(result.getEsMoroso()).isEqualTo(true);
        assertThat(result.getEsIndependiente()).isEqualTo(true);
        assertThat(result.getEsEstable()).isEqualTo(true);
        assertThat(result.getAntiguedadLaboral()).isEqualTo(1);
        assertThat(result.getDeudaTotal()).isEqualTo(500.0);
        assertThat(result.getCapacidadAhorro()).isEqualTo("estable");
        assertThat(result.getTiempoCuentaAhorros()).isEqualTo(2);
        assertThat(result.getSaldo()).isEqualTo(500.0);
        assertThat(result.getMayorRetiro6()).isEqualTo(500.0);
        assertThat(result.getMayorRetiro12()).isEqualTo(100.0);
        assertThat(result.getDepositoRegular()).isEqualTo(true);
        assertThat(result.getTotalDepositos()).isEqualTo(500000.0);
        assertThat(result.getSaldoPositivo()).isEqualTo(true);
    }

    @Test
    public void whenGetByRut_thenCorrect() {
        //Given
        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Maria");
        cliente.setApellidos("García");
        cliente.setEdad(25);
        cliente.setIngresos(500.0);
        cliente.setEsMoroso(true);
        cliente.setEsIndependiente(true);
        cliente.setEsEstable(true);
        cliente.setAntiguedadLaboral(1);
        cliente.setDeudaTotal(500.0);
        cliente.setCapacidadAhorro("estable");
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(500000.0);
        cliente.setSaldoPositivo(true);


        //When
        when(clienteRepository.findByRut("1234566-7")).thenReturn(cliente);

        //Then
        ClienteEntity result = clienteService.getClienteByRut("1234566-7");
        assertNotNull(result);
        assertThat(result.getNombre()).isEqualTo("Maria");
        assertThat(result.getApellidos()).isEqualTo("García");
        assertThat(result.getEdad()).isEqualTo(25);
        assertThat(result.getIngresos()).isEqualTo(500.0);
        assertThat(result.getEsMoroso()).isEqualTo(true);
        assertThat(result.getEsIndependiente()).isEqualTo(true);
        assertThat(result.getEsEstable()).isEqualTo(true);
        assertThat(result.getAntiguedadLaboral()).isEqualTo(1);
        assertThat(result.getDeudaTotal()).isEqualTo(500.0);
        assertThat(result.getCapacidadAhorro()).isEqualTo("estable");
        assertThat(result.getTiempoCuentaAhorros()).isEqualTo(2);
        assertThat(result.getSaldo()).isEqualTo(500.0);
        assertThat(result.getMayorRetiro6()).isEqualTo(500.0);
        assertThat(result.getMayorRetiro12()).isEqualTo(100.0);
        assertThat(result.getDepositoRegular()).isEqualTo(true);
        assertThat(result.getTotalDepositos()).isEqualTo(500000.0);
        assertThat(result.getSaldoPositivo()).isEqualTo(true);
    }

    @Test
    public void whenGetById_thenCorrect() {
        //Given
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(1L);
        cliente.setRut("1234566-7");
        cliente.setNombre("Maria");
        cliente.setApellidos("García");
        cliente.setEdad(25);
        cliente.setIngresos(500.0);
        cliente.setEsMoroso(true);
        cliente.setEsIndependiente(true);
        cliente.setEsEstable(true);
        cliente.setAntiguedadLaboral(1);
        cliente.setDeudaTotal(500.0);
        cliente.setCapacidadAhorro("estable");
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(500000.0);
        cliente.setSaldoPositivo(true);


        //When
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        //Then
        ClienteEntity result = clienteService.getClienteById(1L);
        assertNotNull(result);
        assertThat(result.getRut()).isEqualTo("1234566-7");
        assertThat(result.getNombre()).isEqualTo("Maria");
        assertThat(result.getApellidos()).isEqualTo("García");
        assertThat(result.getEdad()).isEqualTo(25);
        assertThat(result.getIngresos()).isEqualTo(500.0);
        assertThat(result.getEsMoroso()).isEqualTo(true);
        assertThat(result.getEsIndependiente()).isEqualTo(true);
        assertThat(result.getEsEstable()).isEqualTo(true);
        assertThat(result.getAntiguedadLaboral()).isEqualTo(1);
        assertThat(result.getDeudaTotal()).isEqualTo(500.0);
        assertThat(result.getCapacidadAhorro()).isEqualTo("estable");
        assertThat(result.getTiempoCuentaAhorros()).isEqualTo(2);
        assertThat(result.getSaldo()).isEqualTo(500.0);
        assertThat(result.getMayorRetiro6()).isEqualTo(500.0);
        assertThat(result.getMayorRetiro12()).isEqualTo(100.0);
        assertThat(result.getDepositoRegular()).isEqualTo(true);
        assertThat(result.getTotalDepositos()).isEqualTo(500000.0);
        assertThat(result.getSaldoPositivo()).isEqualTo(true);
    }

    @Test
    public void whenGetClientes_thenCorrect() {
        //Given
        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Maria");
        cliente.setApellidos("García");
        cliente.setEdad(25);
        cliente.setIngresos(500.0);
        cliente.setEsMoroso(true);
        cliente.setEsIndependiente(true);
        cliente.setEsEstable(true);
        cliente.setAntiguedadLaboral(1);
        cliente.setDeudaTotal(500.0);
        cliente.setCapacidadAhorro("estable");
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(500000.0);
        cliente.setSaldoPositivo(true);

        ArrayList<ClienteEntity> clienteList = new ArrayList<>();
        clienteList.add(cliente);


        //When
        when(clienteRepository.findAll()).thenReturn(clienteList);

        //Then
        ArrayList<ClienteEntity> result = clienteService.getClientes();
        assertNotNull(result);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getRut()).isEqualTo("1234566-7");
        assertThat(result.get(0).getNombre()).isEqualTo("Maria");
        assertThat(result.get(0).getApellidos()).isEqualTo("García");
        assertThat(result.get(0).getEdad()).isEqualTo(25);
        assertThat(result.get(0).getIngresos()).isEqualTo(500.0);
        assertThat(result.get(0).getEsMoroso()).isEqualTo(true);
        assertThat(result.get(0).getEsIndependiente()).isEqualTo(true);
        assertThat(result.get(0).getEsEstable()).isEqualTo(true);
        assertThat(result.get(0).getAntiguedadLaboral()).isEqualTo(1);
        assertThat(result.get(0).getDeudaTotal()).isEqualTo(500.0);
        assertThat(result.get(0).getCapacidadAhorro()).isEqualTo("estable");
        assertThat(result.get(0).getTiempoCuentaAhorros()).isEqualTo(2);
        assertThat(result.get(0).getSaldo()).isEqualTo(500.0);
        assertThat(result.get(0).getMayorRetiro6()).isEqualTo(500.0);
        assertThat(result.get(0).getMayorRetiro12()).isEqualTo(100.0);
        assertThat(result.get(0).getDepositoRegular()).isEqualTo(true);
        assertThat(result.get(0).getTotalDepositos()).isEqualTo(500000.0);
        assertThat(result.get(0).getSaldoPositivo()).isEqualTo(true);
    }

    @Test
    public void whenCompruebaCampos_thenCorrect() {
        //Given
        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Nerea");
        cliente.setApellidos("Ruiz");
        cliente.setEdad(23);
        cliente.setIngresos(500.0);
        cliente.setEsMoroso(true);
        cliente.setEsIndependiente(true);
        cliente.setEsEstable(true);
        cliente.setAntiguedadLaboral(1);
        cliente.setDeudaTotal(500.0);
        cliente.setCapacidadAhorro("estable");
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(500000.0);
        cliente.setSaldoPositivo(true);

        given(clienteRepository.findByRut("1234566-7")).willReturn(cliente);
        //When
        boolean campoCorrecto = clienteService.compruebaCampos(cliente.getRut());

        //Then
        assertThat(campoCorrecto).isTrue();
    }

    @Test
    public void whenCompruebaCamposIndep_thenCorrect() {
        //Given
        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Nerea");
        cliente.setApellidos("Ruiz");
        cliente.setEdad(23);
        cliente.setIngresos(500.0);
        cliente.setEsMoroso(true);
        cliente.setEsIndependiente(false);
        cliente.setAntiguedadLaboral(1);
        cliente.setDeudaTotal(500.0);
        cliente.setCapacidadAhorro("estable");
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(500000.0);
        cliente.setSaldoPositivo(true);

        given(clienteRepository.findByRut("1234566-7")).willReturn(cliente);
        //When
        boolean campoCorrecto = clienteService.compruebaCampos(cliente.getRut());

        //Then
        assertThat(campoCorrecto).isTrue();
    }

    @Test
    public void whenCompruebaCamposIndep_thenIncorrect() {
        //Given
        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Nerea");
        cliente.setApellidos("Ruiz");
        cliente.setEdad(23);
        cliente.setIngresos(500.0);
        cliente.setEsMoroso(true);
        cliente.setEsIndependiente(true);
        cliente.setAntiguedadLaboral(1);
        cliente.setDeudaTotal(500.0);
        cliente.setCapacidadAhorro("estable");
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(500000.0);
        cliente.setSaldoPositivo(true);

        given(clienteRepository.findByRut("1234566-7")).willReturn(cliente);
        //When
        boolean campoCorrecto = clienteService.compruebaCampos(cliente.getRut());

        //Then
        assertThat(campoCorrecto).isFalse();
    }

    @Test
    public void whenCompruebaCamposNoAntiguedad_thenIncorrect() {
        //Given
        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Nerea");
        cliente.setApellidos("Ruiz");
        cliente.setEdad(23);
        cliente.setIngresos(500.0);
        cliente.setEsMoroso(true);
        cliente.setEsIndependiente(false);
        cliente.setDeudaTotal(500.0);
        cliente.setCapacidadAhorro("estable");
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(500000.0);
        cliente.setSaldoPositivo(true);

        given(clienteRepository.findByRut("1234566-7")).willReturn(cliente);
        //When
        boolean campoCorrecto = clienteService.compruebaCampos(cliente.getRut());

        //Then
        assertThat(campoCorrecto).isFalse();
    }

    @Test
    public void whenCompruebaCampos_thenIncorrect() {
        //Given
        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Nerea");
        cliente.setApellidos("Ruiz");
        cliente.setEdad(23);
        cliente.setIngresos(500.0);
        cliente.setEsIndependiente(true);
        cliente.setEsMoroso(true);
        cliente.setEsEstable(true);
        cliente.setAntiguedadLaboral(1);
        cliente.setDeudaTotal(500.0);
        cliente.setCapacidadAhorro("estable");
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500.0);
        cliente.setTotalDepositos(500000.0);
        cliente.setSaldoPositivo(true);

        given(clienteRepository.findByRut("1234566-7")).willReturn(cliente);
        //When
        Boolean campoCorrecto = clienteService.compruebaCampos(cliente.getRut());

        //Then
        assertThat(campoCorrecto).isFalse();
    }

    @Test
    public void whenCompruebaCamposNoIndep_thenIncorrect() {
        //Given
        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Nerea");
        cliente.setApellidos("Ruiz");
        cliente.setEdad(23);
        cliente.setIngresos(500.0);
        cliente.setEsMoroso(true);
        cliente.setEsEstable(true);
        cliente.setAntiguedadLaboral(1);
        cliente.setDeudaTotal(500.0);
        cliente.setCapacidadAhorro("estable");
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(500000.0);
        cliente.setSaldoPositivo(true);

        given(clienteRepository.findByRut("1234566-7")).willReturn(cliente);
        //When
        boolean campoCorrecto = clienteService.compruebaCampos(cliente.getRut());

        //Then
        assertThat(campoCorrecto).isFalse();
    }
}