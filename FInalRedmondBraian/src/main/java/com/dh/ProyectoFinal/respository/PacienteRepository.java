package com.dh.ProyectoFinal.respository;

import com.dh.ProyectoFinal.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository <Paciente, Long> {
//    Optional<Paciente> findByEmail(String email);

}
