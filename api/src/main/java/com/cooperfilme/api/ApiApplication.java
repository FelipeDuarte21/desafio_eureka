package com.cooperfilme.api;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cooperfilme.api.dto.UsuarioDadosDTO;
import com.cooperfilme.api.service.UsuarioService;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner{ 

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void run(String... args) throws Exception {
		
		for(UsuarioDadosDTO usuario: getUsuariosIniciais()) {
			try{
				this.usuarioService.cadastrar(usuario);
			}catch(IllegalArgumentException ex){
				continue;
			}
		}

	}

	private List<UsuarioDadosDTO> getUsuariosIniciais(){
		List<UsuarioDadosDTO> usuarios = new ArrayList<>();
		usuarios.add(new UsuarioDadosDTO("Roberto Júnior", "Analista", "analista@gmail.com", "1234"));
		usuarios.add(new UsuarioDadosDTO("Carlos Henrique", "Revisor", "revisor@gmail.com", "1234"));
		usuarios.add(new UsuarioDadosDTO("Maria Ribeiro", "Aprovador", "aprovador1@gmail.com", "1234"));
		usuarios.add(new UsuarioDadosDTO("Robson dos Santos", "Aprovador", "aprovador2@gmail.com", "1234"));
		usuarios.add(new UsuarioDadosDTO("Carolina Machado", "Aprovador", "aprovador3@gmail.com", "1234"));
		return usuarios;
	}

}
