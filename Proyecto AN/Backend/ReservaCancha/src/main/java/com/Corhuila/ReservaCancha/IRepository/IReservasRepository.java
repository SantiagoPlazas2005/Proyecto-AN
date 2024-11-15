package com.Corhuila.ReservaCancha.IRepository;

import com.Corhuila.ReservaCancha.Entity.Reservas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservasRepository extends JpaRepository<Reservas, Integer> {
}

