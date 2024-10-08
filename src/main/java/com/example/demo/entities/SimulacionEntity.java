package com.example.demo.entities;

import com.example.demo.TipoPrestamo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "simulaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)

    private Long id;
    private String rut; //para saber el cliente que ha solicitado ese credito, no se si hace fala realmente
    private int plazo;
    private double tasaInteres;
    private double monto;
    private TipoPrestamo tipoPrestamo;
    private double valorPropiedad;
    private double cuotaMensual; // no estoy segura de que haya que incluir aqui la cuota mensual
}
