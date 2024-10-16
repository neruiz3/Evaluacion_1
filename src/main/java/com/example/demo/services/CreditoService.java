package com.example.demo.services;

import com.example.demo.Estado;
import com.example.demo.TipoPrestamo;
import com.example.demo.TipoPrestamoDTO;
import com.example.demo.entities.ClienteEntity;
import com.example.demo.entities.CreditoEntity;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.CreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditoService {
    @Autowired
    CreditoRepository creditoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    DocumentacionService documentacionService;

    public ArrayList<CreditoEntity> getCreditos(){
        return (ArrayList<CreditoEntity>) creditoRepository.findAll();
    }

    public ArrayList<CreditoEntity> getCreditosCliente(String rut){
        return (ArrayList<CreditoEntity>) creditoRepository.findByRut(rut);
    }

    //Hay que poner condiciones en cuanto al monto y el resto de parametros? o es se pone en otro lado??
    public CreditoEntity calculaSimulacion (CreditoEntity simulacion){
        simulacion.setCuotaMensual(calcularCuotaMensual(simulacion.getPlazo(), simulacion.getTasaInteres(), simulacion.getMonto()));
        return simulacion; //ya se han establecido los valores de la simulacion
    }

    public CreditoEntity creaExpediente(CreditoEntity solicitud){
        solicitud.setEstado(Estado.EN_REVISION_INICIAL);
        return creditoRepository.save(solicitud);
    }

    public CreditoEntity revisionInicial (CreditoEntity credito){
        //verificar que se han completado los campos y adjuntado los documentos necesarios
        //plazo maximo
        //tasa de interes anual
        //documentacion
        //piden un monto
        //yo creo que eso se podria hacer haciendo que cuandos se meta un credito a la base de datos no puedan estar vacios esos campos
        credito.setMonto(calcularCuotaMensual(credito.getPlazo(), credito.getTasaInteres(), credito.getMonto()));
        if(documentacionService.compruebaDocumentos(credito.getTipoPrestamo(), credito.getRut())){
            credito.setEstado(Estado.EN_EVALUACION);
        }
        else {
            credito.setEstado(Estado.PENDIENTE_DOCUMENTACION);
        }
        return creditoRepository.save(credito);
    }

    public CreditoEntity evaluacionCredito (CreditoEntity credito){
        credito.setCuotaMensual(calcularCuotaMensual(credito.getPlazo(), credito.getTasaInteres(), credito.getMonto()));
        ClienteEntity cliente = clienteRepository.findByRut(credito.getRut());

        double cuotaIngreso = credito.getCuotaMensual()/cliente.getIngresos()*100.0;
        if(cuotaIngreso > 35.0){
            credito.setEstado(Estado.RECHAZADA);
            return credito;
        }
        if(cliente.getEsMoroso()){
            credito.setEstado(Estado.RECHAZADA);
            return credito;
        }
        if(cliente.getEsIndependiente()){
            if(!cliente.getEsEstable()){
                credito.setEstado(Estado.RECHAZADA);
                return credito;
            }
        }
        else{
            if(cliente.getAntiguedadLaboral()<1){
                credito.setEstado(Estado.RECHAZADA);
                return credito;
            }
        }
        double deuda = cliente.getDeudaTotal()+credito.getCuotaMensual();
        if(deuda > 0.5*cliente.getIngresos()){
            credito.setEstado(Estado.RECHAZADA);
            return credito;
        }
        if(!(validacion(credito))){
            credito.setEstado(Estado.RECHAZADA);
            return credito;
        }
        int edadFutura = cliente.getEdad()+credito.getPlazo();
        if(edadFutura>70){
            credito.setEstado(Estado.RECHAZADA);
            return credito;
        }
        //capacidad de ahorro
        int requisitos = calculaCapacidadAhorro(cliente,credito);
        //evaluamos los requisitos
        if(requisitos == 5){
            cliente.setCapacidadAhorro("solida"); //continuar con la evaluacion
            credito.setEstado(Estado.PRE_APROBADA);
        } else if (requisitos < 3) {
            cliente.setCapacidadAhorro("insuficiente"); //revision adicional
            credito.setEstado(Estado.RECHAZADA);
        } else {
            cliente.setCapacidadAhorro("moderada"); //rechazar
            credito.setEstado(Estado.EN_EVALUACION);
        }
        return creditoRepository.save(credito);
    }

    private int calculaCapacidadAhorro(ClienteEntity cliente, CreditoEntity credito){
        int requisitos = 0;

        //saldo minimo
        if (cliente.getSaldo()>=0.1*credito.getMonto()){
            requisitos++;
        }
        //historial de ahorro  --> saldo positivo en su cuenta de ahorros durante los últimos 12 meses
        //sin retiros > 50% del saldo.
        if(cliente.getSaldoPositivo()){
            if(cliente.getMayorRetiro12()>cliente.getSaldo()*0.5){
                requisitos++;
            }
        }
        //depositos periodicos
        if(cliente.getDepositoRegular()){
            if(cliente.getTotalDepositos()>=0.05*cliente.getIngresos()){
                requisitos++;
            }
        }
        //relacion saldo/años de antiguedad
        double porcentaje = 0.1;
        if(cliente.getTiempoCuentaAhorros()<2){
            porcentaje = 0.2;
        }
        if(cliente.getSaldo()>credito.getMonto()*porcentaje){
            requisitos++;
        }
        //retiros recientes, no se si habria que hacerlo asi o obteniendo de una base de datos los retiros
        if((cliente.getMayorRetiro6()<= cliente.getSaldo()*0.3)) {
            requisitos++;
        }
    return requisitos;
    }


    private double calcularCuotaMensual(int plazo, double tasaInteres, double monto) {
        int n = plazo * 12; // numero total de pagos
        double r = tasaInteres / 12.0 / 100.0; //tasa de interes mensual
        double p = monto;
        // M = P[r(1+r)^n]/[(1+r)^n-1]
        return ((p*r*Math.pow((1+r),n))/(Math.pow((1+r),n)-1));
    }

    public boolean validacion(CreditoEntity simulacion) {
        // Validar el plazo
        if (simulacion.getPlazo() > simulacion.getTipoPrestamo().getPlazoMaximo()) {
            throw new IllegalArgumentException("El plazo excede el máximo permitido para este tipo de préstamo.");
        }
        // Validar la tasa de interés
        if (simulacion.getTasaInteres() < simulacion.getTipoPrestamo().getTasaInteresMinima()
                || simulacion.getTasaInteres() > simulacion.getTipoPrestamo().getTasaInteresMaxima()) {
            throw new IllegalArgumentException("La tasa de interés está fuera del rango permitido.");
        }
        // Validar el monto
        double montoMax = simulacion.getValorPropiedad()*(simulacion.getTipoPrestamo().getMontoMaximo() / 100.0);
        if (simulacion.getMonto() > montoMax) {
            throw new IllegalArgumentException("El monto solicitado excede el máximo permitido para este tipo de préstamo.");
        }

        return true;
    }

    public CreditoEntity cambioEstado(CreditoEntity credito, Estado nuevoEstado){
        if ((credito.getEstado() == Estado.PRE_APROBADA && nuevoEstado == Estado.EN_APROBACION_FINAL) ||
                (credito.getEstado() == Estado.EN_APROBACION_FINAL && nuevoEstado == Estado.APROBADA) ||
                (credito.getEstado() == Estado.APROBADA && nuevoEstado == Estado.EN_DESEMBOLSO)) {
            credito.setEstado(nuevoEstado);
            creditoRepository.save(credito);
        } else {
            throw new IllegalStateException("Cambio de estado no permitido desde " + credito.getEstado() + " a " + nuevoEstado);
        }
        creditoRepository.save(credito);
        return credito;
    }

    public boolean eliminaCredito(Long id) throws Exception {
        try{
            creditoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public double calculaCostoTotal (CreditoEntity credito){
        //calculo cuota mensual, que ya deberia estar calculada porque nada mas crearlo se hace
        double seguro = credito.getMonto()*0.03 + 20.0; //se suma tambien el de incendio que son 20 dolares
        double admin = credito.getMonto()*0.01;
        double costoMensual = credito.getCuotaMensual()+seguro;
        double costoTotal = costoMensual*12+admin;
        return costoTotal;
    }


    public List<TipoPrestamoDTO> obtenerTiposPrestamo() {
        return List.of(TipoPrestamo.values()).stream()
                .map(this::mapearEnumADTO)
                .collect(Collectors.toList());
    }

    private TipoPrestamoDTO mapearEnumADTO(TipoPrestamo tipo) {
        return new TipoPrestamoDTO(
                tipo.name(),
                tipo.getPlazoMaximo(),
                tipo.getMontoMaximo(),
                tipo.getTasaInteresMinima(),
                tipo.getTasaInteresMaxima(),
                tipo.isComprobanteIngreso(),
                tipo.isCertificadoAvaluo(),
                tipo.isHistorialCrediticio(),
                tipo.isEscrituraVivienda(),
                tipo.isEstadoFinanciero(),
                tipo.isPlanNegocios(),
                tipo.isPresupuestoRemodelacion()
        );
    }
}
