package com.example.demo.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.entity.Usuario;
 
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenario
		Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").build();
		entityManager.persist(usuario);
		//acao/execucao
		boolean result = repository.existsByEmail("usuario@email.com");
		//verificacao
		Assertions.assertThat(result).isTrue();
	}
	
	
	@Test
	public void deveRetornarFalsoUsuarioComEmail() {
		//cenario
 		
		//acao/execucao
		boolean result = repository.existsByEmail("usuario@email.com");
		//verificacao
		Assertions.assertThat(result).isFalse();
	}
	
	
	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		//cenario
		Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").senha("senha").build();
		//acao/execucao
		Usuario usuarioSalvo = repository.save(usuario);
		//verificacao
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
		
		
	}
	
	@Test
	public void deveBuscarUmUsuarioPorEmail() {
		//cenario
		Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").senha("senha").build();
		entityManager.persist(usuario);
		
		boolean result = repository.existsByEmail("usuario@email.com");
		
		//verificacao
		Assertions.assertThat(result).isTrue();
		
	}
}
