package com.Corhuila.ReservaCancha.Controller;

import com.Corhuila.ReservaCancha.Entity.Canchas;
import com.Corhuila.ReservaCancha.IService.ICanchasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("api/canchas")
public class CanchasController {
    @Autowired
    private ICanchasService service;

    @PostMapping("")
    public Canchas save(@RequestBody Canchas canchas){
        return service.save(canchas);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Canchas canchas, @PathVariable Integer id){
        service.update(canchas, id);
    }

    @GetMapping("")
    public List<Canchas> all(){
        return service.all();
    }

    @GetMapping("/{id}")
    public Optional<Canchas> findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}
