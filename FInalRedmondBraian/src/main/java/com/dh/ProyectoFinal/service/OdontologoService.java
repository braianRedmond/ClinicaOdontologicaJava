package com.dh.ProyectoFinal.service;

import com.dh.ProyectoFinal.entity.Odontologo;
import com.dh.ProyectoFinal.respository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private static final Logger logger = Logger.getLogger(OdontologoService.class);
    OdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService (OdontologoRepository odontologoRepository){
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardarOdontologo (Odontologo odontologo){
        logger.info("Se guardo el odontologo correctamente");
        return odontologoRepository.save(odontologo);
    }
    public void actualizarOdontologo (Odontologo odontologo){
        odontologoRepository.save(odontologo);
        logger.info("Se actualizo el odontologo: "+odontologo.getId());
    }
    public void eliminarOdontologo (Long id){
        odontologoRepository.deleteById(id);
        logger.info("Se elimino el odontologo con id: "+id);
    }
    public Optional<Odontologo> buscarOdontologo (Long id){
        return odontologoRepository.findById(id);
    }
    public List<Odontologo> listarOdontologos(){
        logger.info("Se realizo correctamente la busqueda de todos los odontologos existentes");
        return odontologoRepository.findAll();
    }

    }
