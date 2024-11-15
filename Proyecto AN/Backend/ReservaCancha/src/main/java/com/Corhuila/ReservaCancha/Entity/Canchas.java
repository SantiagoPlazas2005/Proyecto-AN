package com.Corhuila.ReservaCancha.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "canchas")
public class Canchas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "ubicacion", length = 55, nullable = false)
    private String ubicacion;

    @Column(name = "horarios_disponibles", columnDefinition = "TEXT", nullable = false)
    private String horariosDisponibles;  // Inicia con un valor vacío o un valor predeterminado JSON

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHorariosDisponibles() {
        return horariosDisponibles;
    }

    public void setHorariosDisponibles(String horariosDisponibles) {
        this.horariosDisponibles = horariosDisponibles;
    }
}