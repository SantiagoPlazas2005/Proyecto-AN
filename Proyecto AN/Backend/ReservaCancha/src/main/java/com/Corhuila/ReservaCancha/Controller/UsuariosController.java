package com.Corhuila.ReservaCancha.Controller;

import com.Corhuila.ReservaCancha.IRepository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Corhuila.ReservaCancha.IService.IUsuariosService;
import com.Corhuila.ReservaCancha.Entity.Usuarios;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("api/usuarios")
public class UsuariosController {

    @Autowired
    private IUsuariosService service;

    @Autowired
    private IUsuariosRepository repository;

    @PostMapping("")
    public Usuarios save(@RequestBody Usuarios usuarios){
        return service.save(usuarios);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Usuarios usuarios, @PathVariable Integer id){
        service.update(usuarios, id);
    }

    @GetMapping("")
    public List<Usuarios> all(){
        return service.all();
    }

    @GetMapping("/{id}")
    public Optional<Usuarios> findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}
