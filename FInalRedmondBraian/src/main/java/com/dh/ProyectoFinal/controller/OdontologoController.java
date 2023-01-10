package com.dh.ProyectoFinal.controller;


import com.dh.ProyectoFinal.entity.Odontologo;
import com.dh.ProyectoFinal.exceptions.ResourceNotFoundException;
import com.dh.ProyectoFinal.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {


    private OdontologoService odontologoService;

    private static final Logger logger = Logger.getLogger(OdontologoService.class);

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos (){
        return ResponseEntity.ok(odontologoService.listarOdontologos()) ;
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException{

        Optional<Odontologo> resultado = odontologoService.buscarOdontologo(id);
        if (resultado.isPresent()) {
            logger.info("Se realizo la busqueda del odontologo: "+id);

            return ResponseEntity.ok(resultado.get());
        } else {

            throw new ResourceNotFoundException("El odontologo con id: "+id+", no ha sido encontrado");
        }
    };
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> buscado = odontologoService.buscarOdontologo(odontologo.getId());
        if (buscado.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Se actualizo el odontologo con id: " + odontologo.getId());
        } else {
            return ResponseEntity.badRequest().body("No se puede actualizar los datos del odontologo con id: "+odontologo.getId()+" revise si existe en la base de datos");
        }
    }
    @DeleteMapping
    public ResponseEntity<String> eliminarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> resultado = odontologoService.buscarOdontologo(odontologo.getId());
        if (resultado.isPresent()) {
            odontologoService.eliminarOdontologo(odontologo.getId());
            return ResponseEntity.ok("Se elimino el odontologo con id: "+odontologo.getId());
        } else {
            throw new ResourceNotFoundException("No se elimino el odontologo con id: "+odontologo.getId()+" revise que exista en la base de datos");
        }
    }

}
