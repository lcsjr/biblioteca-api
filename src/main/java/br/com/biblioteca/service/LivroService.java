package br.com.biblioteca.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.dto.LivroDTO;

@Service
public class LivroService {

	private static List<Livro> livros = new ArrayList<>();

	static {

		Livro l1 = new Livro(1, "Java 8", LocalDate.of(2018, 1, 15));
		Livro l2 = new Livro(2, "Sprint Boot", LocalDate.of(2019, 3, 15));
		Livro l3 = new Livro(3, "Sprint MVC", LocalDate.of(2019, 8, 1));
		livros = Arrays.asList(l1, l2, l3);
	}

	public ResponseEntity<List<Livro>> listarLivros() {
		return ResponseEntity.ok(livros);
	}

	public ResponseEntity<Livro> buscaLivro(Integer id) {
		for (Livro livro : livros) {
			if (livro.getId() == id) {
				return ResponseEntity.ok(livro);
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	public ResponseEntity<LinkedHashMap<String, Object>> incluirLivro(LivroDTO livroDTO) {

		LinkedHashMap<String, Object> livro = new LinkedHashMap<>();

		if (livroDTO.getNome().equals("Java")) {
			livro.put("id", 1);
			livro.put("nome", livroDTO.getNome());
			livro.put("publicacao", livroDTO.getPublicacao());

			return ResponseEntity.status(HttpStatus.CONFLICT).body(livro);
		} else {
			livro.put("id", 1);
			livro.put("nome", livroDTO.getNome());
			livro.put("publicacao", livroDTO.getPublicacao());
			livro.put("dataCadastro", LocalDateTime.now());

			return ResponseEntity.status(HttpStatus.CREATED).body(livro);
		}

	}

	public ResponseEntity<LinkedHashMap<String, Object>> atualizarLivro(Integer id, LivroDTO livroDTO) {

		LinkedHashMap<String, Object> livro = new LinkedHashMap<>();

		Livro l1 = findId(id);

		if (l1 != null) {
			livro.put("id", l1.getId());
			livro.put("nome", livroDTO.getNome());
			livro.put("publicacao", livroDTO.getPublicacao());
			livro.put("dataAtualizacao", LocalDateTime.now());

			return ResponseEntity.status(HttpStatus.OK).body(livro);

		} else {
			livro.put("id", 1);
			livro.put("nome", livroDTO.getNome());
			livro.put("publicacao", livroDTO.getPublicacao());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(livro);
		}
	}

	public ResponseEntity<Void> excluirLivro(int id) {

		if (excluirId(id)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	private boolean excluirId(int id) {
		return true;
	}

	private Livro findId(Integer id) {

		for (Livro livro : livros) {
			if (livro.getId() == id.intValue())
				return livro;
		}
		return null;
	}
}
