package com.Corhuila.ReservaCancha.IService;

import com.Corhuila.ReservaCancha.Entity.Usuarios;

import java.util.List;
import java.util.Optional;

public interface IUsuariosService {

    Usuarios save(Usuarios usuarios);
    void update (Usuarios usuarios, Integer id);
    List<Usuarios> all();
    Optional<Usuarios> findById(Integer id);
    void delete(Integer id);

}
