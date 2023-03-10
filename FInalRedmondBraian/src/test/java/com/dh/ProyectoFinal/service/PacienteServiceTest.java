package com.dh.ProyectoFinal.service;

import com.dh.ProyectoFinal.entity.Domicilio;
import com.dh.ProyectoFinal.entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Paciente pacienteAGuardar = new Paciente("alfonso","mansilla","20123456",
                LocalDate.of(2022,11,28),
                new Domicilio("calle a",1,"localidad","provincia"));
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);

        assertEquals(1L,pacienteGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarPacientePorIdTest(){
        Long idABuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);

        assertNotNull(pacienteBuscado);
    }
    @Test
    @Order(3)
    public void buscarPaicnetesTest(){
        List<Paciente> pacientes = pacienteService.buscarTodosPacientes();
        Integer cantidadEsperada=1;

        assertEquals(cantidadEsperada, pacientes.size());
    }
    @Test
    @Order(4)
    public void actualizarPacientesTest(){
        Paciente pacienteAActualizar = new Paciente("juan","perez","21515456",
                LocalDate.of(2022,11,28),
                new Domicilio("rico",1,"dolores","cordoba"));
        pacienteService.actualizarPaciente(pacienteAActualizar);
        Optional<Paciente> pacienteActualizado = pacienteService.buscarPaciente(pacienteAActualizar.getId());

        assertEquals("Pedro",pacienteActualizado.get().getNombre());
    }
    @Test
    @Order(5)
    public void eliminarPacienteTest(){
        Long idAEliminar=1L;
        pacienteService.eliminarPaciente(idAEliminar);
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPaciente(idAEliminar);

        assertFalse(pacienteEliminado.isPresent());
    }


}