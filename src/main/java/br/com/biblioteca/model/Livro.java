package br.com.biblioteca.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Livro {

	private int id;
	private String nome;
	private LocalDate publicacao;

	public Livro() {
	}

	public Livro(int id, String nome, LocalDate publicacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.publicacao = publicacao;
	}

}
