package com.Corhuila.ReservaCancha.Controller;

import com.Corhuila.ReservaCancha.Entity.Reservas;
import com.Corhuila.ReservaCancha.IService.IReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@Controller
@RestController
@RequestMapping("api/reservas")
public class ReservasController {
    @Autowired
    private IReservasService service;

    @PostMapping("")
    public Reservas save(@RequestBody Reservas reservas){
        // Si solo envías los IDs, deberías asegurarte de que los valores de usuario_id y cancha_id estén presentes
        if (reservas.getUsuario() == null || reservas.getCancha() == null) {
            throw new IllegalArgumentException("El usuario y la cancha son obligatorios");
        }

        return service.save(reservas);  // Guardar la reserva con los objetos de usuario y cancha asociados
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Reservas reservas, @PathVariable Integer id){
        service.update(reservas, id);
    }

    @GetMapping("")
    public List<Reservas> all(){
        return service.all();
    }

    @GetMapping("/{id}")
    public Optional<Reservas> findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }


}
