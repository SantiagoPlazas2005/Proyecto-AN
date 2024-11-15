package com.Corhuila.ReservaCancha.IService;

import com.Corhuila.ReservaCancha.Entity.Reservas;

import java.util.List;
import java.util.Optional;

public interface IReservasService {

    Reservas save(Reservas reservas);
    void update (Reservas reservas, Integer id);
    List<Reservas> all();
    Optional<Reservas> findById(Integer id);
    void delete(Integer id);
    
}
