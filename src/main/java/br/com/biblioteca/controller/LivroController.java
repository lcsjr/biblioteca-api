package br.com.biblioteca.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.dto.LivroDTO;
import br.com.biblioteca.service.LivroService;

@RestController
@RequestMapping(path = "/livros/api")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@GetMapping(path = "/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Livro>> listarLivros() {
		return livroService.listarLivros();
	}

	@GetMapping(path = "/v1/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Livro> buscaIdLivro(@PathVariable int id) {
		return livroService.buscaLivro(id);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<LinkedHashMap<String, Object>> incluirLivro(@RequestBody LivroDTO livroDTO) {
		return livroService.incluirLivro(livroDTO);
	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<LinkedHashMap<String, Object>> atualizarLivro(@RequestBody LivroDTO livroDTO,
			@PathVariable int id) {
		return livroService.atualizarLivro(id, livroDTO);
	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> excluirLivro(@PathVariable int id) {
		return livroService.excluirLivro(id);
	}

}
