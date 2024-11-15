package com.Corhuila.ReservaCancha.IRepository;

import com.Corhuila.ReservaCancha.Entity.Canchas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICanchasRepository extends JpaRepository<Canchas, Integer> {
}

