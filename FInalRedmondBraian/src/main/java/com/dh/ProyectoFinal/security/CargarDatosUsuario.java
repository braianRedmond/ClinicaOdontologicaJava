package com.dh.ProyectoFinal.security;

import com.dh.ProyectoFinal.entity.Usuario;
import com.dh.ProyectoFinal.entity.UsuarioRole;
import com.dh.ProyectoFinal.respository.UsuarioRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosUsuario implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;
    public CargarDatosUsuario (UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args){

        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passCifrada = cifrador.encode("digital");
        Usuario usuario =  new Usuario("Braian", "reno33", "reno@gmail.com", passCifrada, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuario);

        passCifrada = cifrador.encode("baspi");
        usuario =  new Usuario("Baspi", "baspi", "baspi@gmail.com", passCifrada, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuario);

    }
}
