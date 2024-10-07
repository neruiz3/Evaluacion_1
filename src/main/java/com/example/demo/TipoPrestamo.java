package com.example.demo;



public enum TipoPrestamo {
    // no se como meter lo de los documentos
    PRIMERAVIVIENDA(30, 80, 3.5, 5.0),
    SEGUNDAVIVIENDA(20, 70, 4.0, 6.0),
    COMERCIAL(25, 60, 5.0, 7.0),
    REMODELACION(15, 50, 4.5, 6.0);

    private final int plazoMaximo;
    private final int montoMaximo;
    private final double tasaInteresMinima;
    private final double tasaInteresMaxima;


    TipoPrestamo(int plazoMaximo, int montoMaximo, double tasaInteresMinima, double tasaInteresMaxima) {
        this.plazoMaximo = plazoMaximo;
        this.montoMaximo = montoMaximo;
        this.tasaInteresMinima = tasaInteresMinima;
        this.tasaInteresMaxima = tasaInteresMaxima;
    }

    public int getPlazoMaximo() {
        return plazoMaximo;
    }

    public int getMontoMaximo() {
        return montoMaximo;
    }

    public double getTasaInteresMaxima() {
        return tasaInteresMaxima;
    }

    public double getTasaInteresMinima() {
        return tasaInteresMinima;
    }
}
