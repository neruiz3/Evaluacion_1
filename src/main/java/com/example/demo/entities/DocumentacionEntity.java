package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "documentacion")
@Data //genera automaticamente los getters y setters
@NoArgsConstructor //genera un constructor sin argumentos para la clase
@AllArgsConstructor //constructor con todos los argumentos de la clase, por si se los pasas
public class DocumentacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String rut;
    //comprobante de ingresos
    @Lob
    private byte[] comprobanteIngresos;
    //escritura primera vivienda
    @Lob
    private byte[] escrituraVivienda;
    //Historial crediticio
    @Lob
    private byte[] historialCrediticio;
    //Certificado avaluo
    @Lob
    private byte[] certificadoAvaluo;
    //estado financiero del negocio
    @Lob
    private byte[] estadoNegocio;
    //plan de negocios
    @Lob
    private byte[] planNegocio;
    //presupuesto de la remodelacion
    @Lob
    private byte[] presupuestoRemodelacion;
    @Lob
    private byte[] certificadoAntiguedadLaboral;
    @Lob
    private byte[] informeDeudas;
    @Lob
    private byte[] fotocopiaRut;
    @Lob
    private byte[] cuentaAhorros;


    //lo siguiente, lo marca el cliente pero lo puede modificar el ejecutivo en funcion de lo que vea en los documentos
    //pero de momento lo voy a dejar en cliente
    /*private double ingresos;
    private boolean esMoroso;
    private boolean esIndependiente;
    private boolean esEstable;
    private int antiguedadLaboral;
    private double deudaTotal;
    private int edad;
    private String capacidadAhorro;
    private double saldo; //Saldo en la cuenta de ahorros
    private double mayorRetiro12; //mayor retiro en los ultimos 12 meses
    private boolean saldoPositivo; //saldo positivo en los ultimos 12 meses
    private int tiempoCuentaAhorros;
    private double mayorRetiro6;// mayor retiro del cliente en los ultimos 6 meses
    private boolean depositoRegular; // si ingresa cada mes o cada trimestre, en los ultimos 12 meses
    private double totalDepositos; //suma total de los depositos en los últimos 12 meses*/
}
