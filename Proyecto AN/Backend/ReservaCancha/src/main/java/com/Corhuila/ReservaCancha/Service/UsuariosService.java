package com.Corhuila.ReservaCancha.Service;

import com.Corhuila.ReservaCancha.Entity.Usuarios;
import com.Corhuila.ReservaCancha.IRepository.IUsuariosRepository;
import com.Corhuila.ReservaCancha.IService.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService implements IUsuariosService {

    @Autowired
    private IUsuariosRepository repository;

    @Override
    public Usuarios save(Usuarios usuarios) {
        return repository.save(usuarios);
    }

    @Override
    public void update(Usuarios usuarios, Integer id) {
        //Validar si existe.
        Optional<Usuarios> up = repository.findById(id);

        if (!up.isEmpty()) {
            up.get().setDocumento(usuarios.getDocumento());
            up.get().setNombre(usuarios.getNombre());
            up.get().setCorreo(usuarios.getCorreo());
            repository.save(usuarios);  // Guarda el usuario existente modificado
        } else {
            System.out.println("No existe registro");
        }



    }

    @Override
    public List<Usuarios> all() {
        return repository.findAll();
    }

    @Override
    public Optional<Usuarios> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }
}


