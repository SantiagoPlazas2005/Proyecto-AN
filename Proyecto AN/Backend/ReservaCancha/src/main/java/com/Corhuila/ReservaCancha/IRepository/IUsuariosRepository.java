package com.Corhuila.ReservaCancha.IRepository;

import com.Corhuila.ReservaCancha.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuariosRepository extends JpaRepository<Usuarios, Integer> {
}

