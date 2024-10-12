package com.example.demo.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data //genera automaticamente los getters y setters
@NoArgsConstructor //genera un constructor sin argumentos para la clase
@AllArgsConstructor //constructor con todos los argumentos de la clase, por si se los pasas
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String rut;
    private String nombre;
    private String apellidos;
    private int edad;
    private double ingresos;
    private boolean esMoroso; // se obtiene a traves del historial crediticio
    private boolean esIndependiente;
    private boolean esEstable;
    private int antiguedadLaboral;
    private double deudaTotal;
    private String capacidadAhorro;
    private double saldo; //Saldo en la cuenta de ahorros
    private double mayorRetiro12; //mayor retiro en los ultimos 12 meses
    private boolean saldoPositivo; //saldo positivo en los ultimos 12 meses
    private int tiempoCuentaAhorros;
    private double mayorRetiro6;// mayor retiro del cliente en los ultimos 6 meses
    private boolean depositoRegular; // si ingresa cada mes o cada trimestre, en los ultimos 12 meses
    private double totalDepositos; //suma total de los depositos en los últimos 12 meses

}
