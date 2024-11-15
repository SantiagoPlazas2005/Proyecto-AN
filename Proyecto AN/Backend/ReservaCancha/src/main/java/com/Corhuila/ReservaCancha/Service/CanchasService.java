package com.Corhuila.ReservaCancha.Service;

import com.Corhuila.ReservaCancha.Entity.Canchas;
import com.Corhuila.ReservaCancha.IRepository.ICanchasRepository;
import com.Corhuila.ReservaCancha.IService.ICanchasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CanchasService implements ICanchasService {

    @Autowired
    private ICanchasRepository repository;

    @Override
    public Canchas save(Canchas canchas) {
        return repository.save(canchas);
    }

    @Override
    public void update(Canchas canchas, Integer id) {
        //Validar si existe.
        Optional<Canchas> up = repository.findById(id);

        if (!up.isEmpty()) {
            up.get().setUbicacion(canchas.getUbicacion());
            up.get().setNombre(canchas.getNombre());
            up.get().setHorariosDisponibles(canchas.getHorariosDisponibles());
            repository.save(canchas);  // Guarda el usuario existente modificado
        } else {
            System.out.println("No existe registro");
        }



    }

    @Override
    public List<Canchas> all() {
        return repository.findAll();
    }

    @Override
    public Optional<Canchas> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }
}


