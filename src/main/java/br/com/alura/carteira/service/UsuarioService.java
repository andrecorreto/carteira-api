package br.com.alura.carteira.service;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.carteira.dto.UsuarioDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.infra.EnviadorDeEmail;
import br.com.alura.carteira.modelo.Perfil;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.PerfilRepository;
import br.com.alura.carteira.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private PerfilRepository perfilRepository;
	@Autowired
	private EnviadorDeEmail enviadorDeEmail;
	
	public Page<UsuarioDto> listar(Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return usuarios.map(u -> modelMapper.map(u, UsuarioDto.class));	
	}
	
	@Transactional
	public UsuarioDto cadastrar(UsuarioFormDto dto) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);
		usuario.setId(null);		
		Perfil perfil = perfilRepository.getById(dto.getPerfilId());
		usuario.adicionarPerfil(perfil);
		
		String senha = new Random().nextInt(999999) + "";
		System.out.println(usuario + "senha = " + senha );
		usuario.setSenha(bCryptPasswordEncoder.encode(senha));
		
		usuarioRepository.save(usuario);
		
//		String mensagem = 
//				"Você foi cadastrado no aplicativo carteira." +
//				"seja bem vindo!" +
//				" 	login: " + usuario.getLogin() +
//				"   senha: " + senha;
		String mensagem = String.format(
				"Olá %s!\n\n"
				+ "Seguem seus dados de acesso ao sistema Carteira:\n"
				+ "Login: %s\n"
				+ "Senha: %s", 
				usuario.getNome(),
				usuario.getLogin(),
				senha);
							
		enviadorDeEmail.enviarEmail(
				usuario.getEmail(), 
				"Carteira - Bem vindo(a)", 
				mensagem);
		
		return modelMapper.map(usuario,  UsuarioDto.class);
	}
}
