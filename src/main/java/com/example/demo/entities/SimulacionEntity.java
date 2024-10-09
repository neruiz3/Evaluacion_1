package com.example.demo.entities;

import com.example.demo.TipoPrestamo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "simulaciones") //no se si hay que crear una base de datos con las simulaciones en realidad
@Data //genera automaticamente los getters y setters
@NoArgsConstructor //genera un constructor sin argumentos para la clase
@AllArgsConstructor //constructor con todos los argumentos de la clase, por si se los pasas
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
