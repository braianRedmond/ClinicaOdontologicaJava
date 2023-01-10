package com.dh.ProyectoFinal.controller;


import com.dh.ProyectoFinal.entity.Paciente;
import com.dh.ProyectoFinal.exceptions.ResourceNotFoundException;
import com.dh.ProyectoFinal.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    private static final Logger logger = Logger.getLogger(PacienteController.class);

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.buscarTodosPacientes());
    };
    @PostMapping
    public ResponseEntity<Paciente> registrarNuevoPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    };

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if(pacienteBuscado.isPresent()){
            logger.info("Se realizo la busqueda del paciente: "+id);
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else {
            throw new ResourceNotFoundException("Se realizo la busqueda del paciente: "+id+", sin exito");
        }
    };


    @DeleteMapping
    public ResponseEntity<String> eliminarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(paciente.getId());
            return ResponseEntity.ok("Se elimino el paciente con id: "+paciente.getId());
        }
        else {
            throw new ResourceNotFoundException("No se elimino el odontologo con id: "+paciente.getId()+" revise que  exista en la base de datos");
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizo el paciente con ID: "+paciente.getId());
        }
        else {
            throw new ResourceNotFoundException("No se puede actualizar los datos del paciente con id: "+paciente.getId()+" revise que  exista en la base de datos");
        }
    }




}
