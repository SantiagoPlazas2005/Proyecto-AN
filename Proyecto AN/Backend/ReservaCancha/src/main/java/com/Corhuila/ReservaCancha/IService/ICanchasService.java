package com.Corhuila.ReservaCancha.IService;

import com.Corhuila.ReservaCancha.Entity.Canchas;

import java.util.List;
import java.util.Optional;

public interface ICanchasService {

    Canchas save(Canchas canchas);
    void update (Canchas canchas, Integer id);
    List<Canchas> all();
    Optional<Canchas> findById(Integer id);
    void delete(Integer id);
    
}
