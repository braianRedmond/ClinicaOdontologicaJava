package com.dh.ProyectoFinal.controller;

import com.dh.ProyectoFinal.dto.TurnoDTO;
import com.dh.ProyectoFinal.exceptions.BadRequestException;
import com.dh.ProyectoFinal.exceptions.ResourceNotFoundException;
import com.dh.ProyectoFinal.respository.PacienteRepository;
import com.dh.ProyectoFinal.service.OdontologoService;
import com.dh.ProyectoFinal.service.PacienteService;
import com.dh.ProyectoFinal.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService  odontologoService;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService  odontologoService,
                           PacienteRepository pacienteRepository) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos() {
        return ResponseEntity.ok(turnoService.buscarTodosTurnos());
    }
    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turnoDTO) throws BadRequestException {

        ResponseEntity<TurnoDTO> respuesta;
        if (pacienteService.buscarPaciente(turnoDTO.getPacienteId()).isPresent()&&odontologoService.buscarOdontologo(turnoDTO.getOdontologoId()).isPresent()){
            respuesta=ResponseEntity.ok(turnoService.guardarTurno(turnoDTO));
        } else {

            throw new BadRequestException("No se puede cargar el turno porque no existe o el odontologo o el paciente en la Base de Datos");
        }
        return respuesta;
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno) {
        ResponseEntity<TurnoDTO> respuesta;


        if(turnoService.buscarTurno(turno.getId()).isPresent()){
            if (pacienteService.buscarPaciente(turno.getPacienteId()).isPresent()&&
                    odontologoService.buscarOdontologo(turno.getOdontologoId()).isPresent()
            ){ turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("Se actualizó el turno con id= "+turno.getId());
            } else{
                return ResponseEntity.badRequest().body("Error al actualizar el turno.");
            }
        }
        else{
            return ResponseEntity.badRequest().body("El turno no existe");
        }

    };

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> resultado = turnoService.buscarTurno(id);
        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        } else {
            throw new ResourceNotFoundException("Se realizo la busqueda del turno: "+id+", sin exito");
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity <String> eliminarTurno (@PathVariable Long id){
        Optional<TurnoDTO> resultado = turnoService.buscarTurno(id);
        if (resultado.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se eliminó el turno correctamente");
        } else {
            return ResponseEntity.badRequest().body("No se puede eliminar el turno con id= "+id+
                    " porque no existe en la base de datos.");
        }
    }

}

