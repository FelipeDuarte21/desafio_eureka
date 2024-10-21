package com.cooperfilme.api.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cooperfilme.api.entity.Usuario;


public class UsuarioDetalhe implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	private String cargo;
	private String nome;
	
	private Collection<? extends GrantedAuthority> autorizacoes;
	
	public UsuarioDetalhe(Long id, String email, String senha, String cargo, String nome) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		List<SimpleGrantedAuthority> simpleGrantedAuthoritys = new ArrayList<>();
		simpleGrantedAuthoritys.add(new SimpleGrantedAuthority("ROLE_USER"));
		this.autorizacoes = simpleGrantedAuthoritys;
		this.cargo = cargo;
		this.nome = nome;
	}
	
	public UsuarioDetalhe(Usuario usuario) {
		this.id = usuario.getId();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		List<SimpleGrantedAuthority> simpleGrantedAuthoritys = new ArrayList<>();
		simpleGrantedAuthoritys.add(new SimpleGrantedAuthority("ROLE_USER"));
		this.autorizacoes = simpleGrantedAuthoritys;
		this.cargo = usuario.getCargo().getDescricao();
		this.nome = usuario.getNome();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.autorizacoes;
	}
	
	public Long getId() {
		return this.id;
	}

	public String getCargo() {
		return cargo;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	public boolean hasRole(String role) {
		return this.autorizacoes.contains(new SimpleGrantedAuthority(role));
	}

}
