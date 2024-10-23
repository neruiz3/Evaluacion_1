package com.example.demo.services;

import com.example.demo.DTO.CostoDTO;
import com.example.demo.Estado;
import com.example.demo.TipoPrestamo;
import com.example.demo.DTO.TipoPrestamoDTO;
import com.example.demo.entities.ClienteEntity;
import com.example.demo.entities.CreditoEntity;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.CreditoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CreditoServiceTest {

    @MockBean
    CreditoRepository creditoRepository;

    @MockBean
    ClienteRepository clienteRepository;

    @MockBean
    DocumentacionService documentacionService;

    @MockBean
    ClienteService clienteService;

    @Autowired
    CreditoService creditoService;

    @Test
    public void whenGetCreditos_thenCorrect() {
        //Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-7");
        credito.setPlazo(20);
        credito.setMonto(1000.0);
        credito.setTasaInteres(3.5);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(2000.0);
        credito.setCuotaMensual(200.0);
        credito.setEstado(Estado.APROBADA);

        ArrayList<CreditoEntity> creditoList = new ArrayList<>();
        creditoList.add(credito);

        //When
        when(creditoRepository.findAll()).thenReturn(creditoList);

        //Then
        ArrayList<CreditoEntity> result = creditoService.getCreditos();
        assertNotNull(result);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getRut()).isEqualTo("1234566-7");
        assertThat(result.get(0).getPlazo()).isEqualTo(20);
        assertThat(result.get(0).getMonto()).isEqualTo(1000.0);
        assertThat(result.get(0).getTasaInteres()).isEqualTo(3.5);
        assertThat(result.get(0).getTipoPrestamo()).isEqualTo(TipoPrestamo.PRIMERAVIVIENDA);
        assertThat(result.get(0).getValorPropiedad()).isEqualTo(2000.0);
        assertThat(result.get(0).getCuotaMensual()).isEqualTo(200.0);
        assertThat(result.get(0).getEstado()).isEqualTo(Estado.APROBADA);
    }

    @Test
    public void whenGetById_thenCorrect() {
        //Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-7");
        credito.setPlazo(20);
        credito.setMonto(1000.0);
        credito.setTasaInteres(3.5);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(2000.0);
        credito.setCuotaMensual(200.0);
        credito.setEstado(Estado.APROBADA);

        //When
        when(creditoRepository.findById(1L)).thenReturn(Optional.of(credito));

        //Then
        CreditoEntity result = creditoService.getCredito(1L);
        assertNotNull(result);
        assertThat(result.getRut()).isEqualTo("1234566-7");
        assertThat(result.getPlazo()).isEqualTo(20);
        assertThat(result.getMonto()).isEqualTo(1000.0);
        assertThat(result.getTasaInteres()).isEqualTo(3.5);
        assertThat(result.getTipoPrestamo()).isEqualTo(TipoPrestamo.PRIMERAVIVIENDA);
        assertThat(result.getValorPropiedad()).isEqualTo(2000.0);
        assertThat(result.getCuotaMensual()).isEqualTo(200.0);
        assertThat(result.getEstado()).isEqualTo(Estado.APROBADA);
    }

    @Test
    public void whenCalculaSimulacion_thenCorrect() {
        //Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-7");
        credito.setPlazo(20);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setCuotaMensual(200.0);

        //When
        CreditoEntity simulacion = creditoService.calculaSimulacion(credito);

        //Then
        assertThat(simulacion.getRut()).isEqualTo("1234566-7");
        assertThat(simulacion.getPlazo()).isEqualTo(20);
        assertThat(simulacion.getMonto()).isEqualTo(100000000.0);
        assertThat(simulacion.getTasaInteres()).isEqualTo(4.5);
        assertThat(simulacion.getCuotaMensual()).isEqualTo(632649.3762199708);
    }

    @Test
    public void whenCreaExpediente_thenCorrect() {
        //Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-7");
        credito.setPlazo(20);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(200000000.0);

        //When
        when(creditoRepository.save(credito)).thenReturn(credito);

        //Then
        CreditoEntity result = creditoService.creaExpediente(credito);
        assertNotNull(result);
        assertThat(result.getRut()).isEqualTo("1234566-7");
        assertThat(result.getPlazo()).isEqualTo(20);
        assertThat(result.getMonto()).isEqualTo(100000000.0);
        assertThat(result.getTasaInteres()).isEqualTo(4.5);
        assertThat(result.getTipoPrestamo()).isEqualTo(TipoPrestamo.PRIMERAVIVIENDA);
        assertThat(result.getValorPropiedad()).isEqualTo(200000000.0);
        assertThat(result.getCuotaMensual()).isEqualTo(632649.3762199708);
        assertThat(result.getEstado()).isEqualTo(Estado.EN_REVISION_INICIAL);
    }

    @Test
    public void whenRevisionInicialBien_thenEnEvaluacion() {
        //Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-7");
        credito.setPlazo(20);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setCuotaMensual(632649.3762199708);
        credito.setValorPropiedad(200000000.0);
        credito.setEstado(Estado.EN_REVISION_INICIAL);

        //When
        when(documentacionService.compruebaDocumentos(TipoPrestamo.PRIMERAVIVIENDA, "1234566-7")).thenReturn(true);
        when(clienteService.compruebaCampos("1234566-7")).thenReturn(true);
        when(creditoRepository.save(any(CreditoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //Then
        CreditoEntity result = creditoService.revisionInicial(credito);
        assertThat(result.getEstado()).isEqualTo(Estado.EN_EVALUACION);
    }

    @Test
    public void whenRevisionInicialDocsMal_thenPendienteDocumentacion() {
        //Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-7");
        credito.setPlazo(20);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setCuotaMensual(632649.3762199708);
        credito.setValorPropiedad(200000000.0);
        credito.setEstado(Estado.EN_REVISION_INICIAL);

        //When
        when(documentacionService.compruebaDocumentos(TipoPrestamo.PRIMERAVIVIENDA, "1234566-7")).thenReturn(false);
        when(clienteService.compruebaCampos("1234566-7")).thenReturn(true);
        when(creditoRepository.save(any(CreditoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //Then
        CreditoEntity result = creditoService.revisionInicial(credito);
        assertThat(result.getEstado()).isEqualTo(Estado.PENDIENTE_DOCUMENTACION);
    }

    @Test
    public void whenEvaluacionCredito_ThenPreAprobada() {
        // Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-7");
        credito.setPlazo(20);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setCuotaMensual(632649.3762199708);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(200000000.0);

        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Maria");
        cliente.setApellidos("García");
        cliente.setEdad(30);
        cliente.setIngresos(20000000.0);
        cliente.setEsMoroso(false);
        cliente.setEsIndependiente(false);
        cliente.setEsEstable(true);
        cliente.setAntiguedadLaboral(2);
        cliente.setDeudaTotal(5000.0);
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500000000000.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(5000000.0);
        cliente.setSaldoPositivo(true);

        // Mocking
        when(clienteRepository.findByRut(credito.getRut())).thenReturn(cliente);
        when(creditoRepository.save(any(CreditoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        CreditoEntity result = creditoService.evaluacionCredito(credito);

        // Then
        assertThat(result.getEstado()).isEqualTo(Estado.PRE_APROBADA);
    }

    @Test
    public void whenEvaluacionCredito_ThenSetEstadoRechazadaPorCuotaIngreso() {
        // Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-7");
        credito.setPlazo(20);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setCuotaMensual(632649.3762199708);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(200000000.0); // Cuota mensual alta

        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Maria");
        cliente.setApellidos("García");
        cliente.setEdad(30);
        cliente.setIngresos(200000.0);
        cliente.setEsMoroso(false);
        cliente.setEsIndependiente(false);
        cliente.setEsEstable(true);
        cliente.setAntiguedadLaboral(2);
        cliente.setDeudaTotal(5000.0);
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500000000000.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(5000000.0);
        cliente.setSaldoPositivo(true);

        // Mocking
        when(clienteRepository.findByRut(credito.getRut())).thenReturn(cliente);
        when(creditoRepository.save(any(CreditoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        CreditoEntity result = creditoService.evaluacionCredito(credito);

        // Then
        assertThat(result.getEstado()).isEqualTo(Estado.RECHAZADA);
    }

    @Test
    public void whenEvaluacionCredito_ThenSetEstadoRechazadaPorMoroso() {
        // Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-8");
        credito.setPlazo(20);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setCuotaMensual(632649.3762199708);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(200000000.0);

        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-8");
        cliente.setNombre("Maria");
        cliente.setApellidos("García");
        cliente.setEdad(30);
        cliente.setIngresos(200000000.0);
        cliente.setEsMoroso(true);
        cliente.setEsIndependiente(false);
        cliente.setEsEstable(true);
        cliente.setAntiguedadLaboral(2);
        cliente.setDeudaTotal(5000.0);
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500000000000.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(5000000.0);
        cliente.setSaldoPositivo(true);

        // Mocking
        when(clienteRepository.findByRut(credito.getRut())).thenReturn(cliente);
        when(creditoRepository.save(any(CreditoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        CreditoEntity result = creditoService.evaluacionCredito(credito);

        // Then
        assertThat(result.getEstado()).isEqualTo(Estado.RECHAZADA);
    }

    @Test
    public void whenEvaluacionCredito_ThenSetEstadoRechazadaPorNoEstable() {
        // Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-7");
        credito.setPlazo(20);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setCuotaMensual(632649.3762199708);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(200000000.0);

        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Maria");
        cliente.setApellidos("García");
        cliente.setEdad(30);
        cliente.setIngresos(20000000.0);
        cliente.setEsMoroso(false);
        cliente.setEsIndependiente(true);
        cliente.setEsEstable(false);
        cliente.setAntiguedadLaboral(2);
        cliente.setDeudaTotal(5000.0);
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500000000000.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(5000000.0);
        cliente.setSaldoPositivo(true);

        // Mocking
        when(clienteRepository.findByRut(credito.getRut())).thenReturn(cliente);
        when(creditoRepository.save(any(CreditoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        CreditoEntity result = creditoService.evaluacionCredito(credito);

        // Then
        assertThat(result.getEstado()).isEqualTo(Estado.RECHAZADA);
    }

    @Test
    public void whenEvaluacionCredito_ThenSetEstadoRechazadaPorEdad() {
        // Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-7");
        credito.setPlazo(20);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setCuotaMensual(632649.3762199708);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(200000000.0);

        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Maria");
        cliente.setApellidos("García");
        cliente.setEdad(60);
        cliente.setIngresos(20000000.0);
        cliente.setEsMoroso(false);
        cliente.setEsIndependiente(true);
        cliente.setEsEstable(true);
        cliente.setAntiguedadLaboral(2);
        cliente.setDeudaTotal(5000.0);
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500000000000.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(5000000.0);
        cliente.setSaldoPositivo(true);

        // Mocking
        when(clienteRepository.findByRut(credito.getRut())).thenReturn(cliente);
        when(creditoRepository.save(any(CreditoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        CreditoEntity result = creditoService.evaluacionCredito(credito);

        // Then
        assertThat(result.getEstado()).isEqualTo(Estado.RECHAZADA);
    }

    @Test
    public void whenEvaluacionCredito_ThenSetEstadoEvaluacionPorRequisitos() {
        // Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-7");
        credito.setPlazo(20);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setCuotaMensual(632649.3762199708);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(200000000.0);

        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Maria");
        cliente.setApellidos("García");
        cliente.setEdad(20);
        cliente.setIngresos(20000000.0);
        cliente.setEsMoroso(false);
        cliente.setEsIndependiente(true);
        cliente.setEsEstable(true);
        cliente.setAntiguedadLaboral(2);
        cliente.setDeudaTotal(5000.0);
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500000000000.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(false);
        cliente.setTotalDepositos(5000000.0);
        cliente.setSaldoPositivo(true);

        // Mocking
        when(clienteRepository.findByRut(credito.getRut())).thenReturn(cliente);
        when(creditoRepository.save(any(CreditoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        CreditoEntity result = creditoService.evaluacionCredito(credito);

        // Then
        assertThat(result.getEstado()).isEqualTo(Estado.EN_EVALUACION);
    }

    @Test
    public void whenEvaluacionCredito_ThenSetEstadoRechazadaPorAntiguedad() {
        // Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-7");
        credito.setPlazo(20);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setCuotaMensual(632649.3762199708);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(200000000.0);

        ClienteEntity cliente = new ClienteEntity();
        cliente.setRut("1234566-7");
        cliente.setNombre("Maria");
        cliente.setApellidos("García");
        cliente.setEdad(30);
        cliente.setIngresos(20000000.0);
        cliente.setEsMoroso(false);
        cliente.setEsIndependiente(false);
        cliente.setEsEstable(false);
        cliente.setAntiguedadLaboral(0);
        cliente.setDeudaTotal(5000.0);
        cliente.setTiempoCuentaAhorros(2);
        cliente.setSaldo(500000000000.0);
        cliente.setMayorRetiro6(500.0);
        cliente.setMayorRetiro12(100.0);
        cliente.setDepositoRegular(true);
        cliente.setTotalDepositos(5000000.0);
        cliente.setSaldoPositivo(true);

        // Mocking
        when(clienteRepository.findByRut(credito.getRut())).thenReturn(cliente);
        when(creditoRepository.save(any(CreditoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        CreditoEntity result = creditoService.evaluacionCredito(credito);

        // Then
        assertThat(result.getEstado()).isEqualTo(Estado.RECHAZADA);
    }

    @Test
    public void whenPlazoMaximo_thenReturnFalse() {
        // Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-8");
        credito.setPlazo(33);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(200000000.0);

        // When
        boolean result = creditoService.validacion(credito);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void whenTasaFueraDelRango_thenReturnFalse() {
        // Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-8");
        credito.setPlazo(29);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(5.5);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(200000000.0);

        // When
        boolean result = creditoService.validacion(credito);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void whenMontoMaximo_thenReturnFalse() {
        // Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-8");
        credito.setPlazo(29);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(100000000.0);

        // When
        boolean result = creditoService.validacion(credito);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void whenValidacionBien_thenReturnTrue() {
        // Given
        CreditoEntity credito = new CreditoEntity();
        credito.setRut("1234566-8");
        credito.setPlazo(29);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(200000000.0);

        // When
        boolean result = creditoService.validacion(credito);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void whenCambioEstadoValido_thenEstadoCambiado() {
        // Given
        Long id = 1L;
        Estado nuevoEstado = Estado.EN_EVALUACION;
        CreditoEntity credito = new CreditoEntity();
        credito.setEstado(Estado.EN_REVISION_INICIAL);

        // Mockear el comportamiento de findById
        when(creditoRepository.findById(id)).thenReturn(Optional.of(credito));
        when(creditoRepository.save(credito)).thenReturn(credito);

        // When
        CreditoEntity result = creditoService.cambioEstado(id, nuevoEstado);

        // Then
        assertThat(result.getEstado()).isEqualTo(nuevoEstado);
    }

    @Test
    public void whenCambioEstadoNoPermitido_thenThrowIllegalStateException() {
        // Given
        Long id = 2L;
        Estado nuevoEstado = Estado.RECHAZADA;
        CreditoEntity credito = new CreditoEntity();
        credito.setEstado(Estado.PENDIENTE_DOCUMENTACION);

        // Mockear el comportamiento de findById
        when(creditoRepository.findById(id)).thenReturn(Optional.of(credito));
        when(creditoRepository.save(credito)).thenReturn(credito);

        // When & Then
        assertThatThrownBy(() -> creditoService.cambioEstado(id, nuevoEstado))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Cambio de estado no permitido desde PENDIENTE_DOCUMENTACION a RECHAZADA");
    }

    @Test
    public void whenEliminaCredito_thenReturnTrue() {
        // Given
        CreditoEntity credito = new CreditoEntity();
        credito.setId(1L);
        credito.setRut("1234566-8");
        credito.setPlazo(29);
        credito.setMonto(100000000.0);
        credito.setTasaInteres(4.5);
        credito.setTipoPrestamo(TipoPrestamo.PRIMERAVIVIENDA);
        credito.setValorPropiedad(200000000.0);

        // When
        doNothing().when(creditoRepository).deleteById(1L);

        boolean result = false;
        try {
            result = creditoService.eliminaCredito(1L);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void whenCalculaCostoTotal_thenReturnCostoDTO() {
        // Given
        CreditoEntity credito = new CreditoEntity();
        credito.setCuotaMensual(500000.0); // Suponiendo que ya se calculó antes
        credito.setMonto(10000000.0);

        // When
        CostoDTO result = creditoService.calculaCostoTotal(credito);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getCuotaMensual()).isEqualTo(500000.0);
        assertThat(result.getSeguroDesgravamen()).isEqualTo(10000000.0 * 0.03);
        assertThat(result.getComisionAdmin()).isEqualTo(10000000.0 * 0.01);
        Double expectedCostoMensual = result.getCuotaMensual() + result.getSeguroDesgravamen() + result.getSeguroIncendio();
        assertThat(result.getCostoMensual()).isEqualTo(expectedCostoMensual);
        Double expectedCostoTotal = expectedCostoMensual * 12 + result.getComisionAdmin();
        assertThat(result.getCostoTotal()).isEqualTo(expectedCostoTotal);
    }

    @Test
    public void whenObtenerTiposPrestamo_thenReturnTipoPrestamoDTOList() {
        // When
        List<TipoPrestamoDTO> result = creditoService.obtenerTiposPrestamo();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();

        // Validar que la lista contiene todos los tipos de préstamo
        for (TipoPrestamo tipo : TipoPrestamo.values()) {
            TipoPrestamoDTO dto = result.stream()
                    .filter(t -> t.getNombre().equals(tipo.name()))
                    .findFirst()
                    .orElse(null);
            assertThat(dto).isNotNull();
            assertThat(dto.getPlazoMaximo()).isEqualTo(tipo.getPlazoMaximo());
            assertThat(dto.getMontoMaximo()).isEqualTo(tipo.getMontoMaximo());
            assertThat(dto.getTasaInteresMinima()).isEqualTo(tipo.getTasaInteresMinima());
            assertThat(dto.getTasaInteresMaxima()).isEqualTo(tipo.getTasaInteresMaxima());
            assertThat(dto.isComprobanteIngreso()).isEqualTo(tipo.isComprobanteIngreso());
            assertThat(dto.isCertificadoAvaluo()).isEqualTo(tipo.isCertificadoAvaluo());
            assertThat(dto.isHistorialCrediticio()).isEqualTo(tipo.isHistorialCrediticio());
            assertThat(dto.isEscrituraVivienda()).isEqualTo(tipo.isEscrituraVivienda());
            assertThat(dto.isEstadoFinanciero()).isEqualTo(tipo.isEstadoFinanciero());
            assertThat(dto.isPlanNegocios()).isEqualTo(tipo.isPlanNegocios());
            assertThat(dto.isPresupuestoRemodelacion()).isEqualTo(tipo.isPresupuestoRemodelacion());
        }
    }
}