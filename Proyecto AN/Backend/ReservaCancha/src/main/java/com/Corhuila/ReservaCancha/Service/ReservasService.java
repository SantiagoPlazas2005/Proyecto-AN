package com.Corhuila.ReservaCancha.Service;

import com.Corhuila.ReservaCancha.Entity.Reservas;
import com.Corhuila.ReservaCancha.IRepository.ICanchasRepository;
import com.Corhuila.ReservaCancha.IRepository.IReservasRepository;
import com.Corhuila.ReservaCancha.IRepository.IUsuariosRepository;
import com.Corhuila.ReservaCancha.IService.IReservasService;
import com.Corhuila.ReservaCancha.Entity.Usuarios;
import com.Corhuila.ReservaCancha.Entity.Canchas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservasService implements IReservasService {

    @Autowired
    private IReservasRepository repository;

    @Autowired
    private IUsuariosRepository usuariosRepository;  // Asegúrate de que tienes este repositorio

    @Autowired
    private ICanchasRepository canchasRepository;  // Asegúrate de que tienes este repositorio

    @Override
    public Reservas save(Reservas reservas) {
        // Verificar si el usuario y la cancha existen en la base de datos
        Optional<Usuarios> usuarioOpt = usuariosRepository.findById(reservas.getUsuario().getId());
        Optional<Canchas> canchaOpt = canchasRepository.findById(reservas.getCancha().getId());

        if (usuarioOpt.isPresent() && canchaOpt.isPresent()) {
            // Asignar las entidades de usuario y cancha a la reserva antes de guardarla
            reservas.setUsuario(usuarioOpt.get());
            reservas.setCancha(canchaOpt.get());

            // Ahora guardamos la reserva con las relaciones adecuadas
            return repository.save(reservas);
        } else {
            // Lanza una excepción si el usuario o la cancha no existen
            throw new IllegalArgumentException("El usuario o la cancha no existen.");
        }
    }

    @Override
    public void update(Reservas reservas, Integer id) {
        // Buscar la reserva existente
        Optional<Reservas> existingReservaOpt = repository.findById(id);

        if (existingReservaOpt.isPresent()) {
            Reservas existingReserva = existingReservaOpt.get();

            // Actualizar los valores básicos de la reserva
            existingReserva.setFecha(reservas.getFecha());
            existingReserva.setHora(reservas.getHora());

            // Si el usuario ha cambiado, actualizar también
            if (reservas.getUsuario() != null) {
                Optional<Usuarios> usuarioOpt = usuariosRepository.findById(reservas.getUsuario().getId());
                usuarioOpt.ifPresent(existingReserva::setUsuario);
            }

            // Si la cancha ha cambiado, actualizar también
            if (reservas.getCancha() != null) {
                Optional<Canchas> canchaOpt = canchasRepository.findById(reservas.getCancha().getId());
                canchaOpt.ifPresent(existingReserva::setCancha);
            }

            // Guardar la reserva actualizada
            repository.save(existingReserva);
        } else {
            // Si la reserva no existe, lanzar una excepción
            throw new IllegalArgumentException("La reserva con ID " + id + " no existe.");
        }
    }

    @Override
    public List<Reservas> all() {
        return repository.findAll();
    }

    @Override
    public Optional<Reservas> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}


