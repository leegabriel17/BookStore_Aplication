package com.gabriel.bookstore.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gabriel.bookstore.model.Livro;

public class LivroDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "campo Titulo requerido")
	@Length(min=3,max= 50,message ="o Campo titulo deve ter entre 3 a 50 caracteres")	
	private String titulo;
	public LivroDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LivroDTO(Livro l) {
		super();
		this.id = l.getId();
		this.titulo = l.getTitulo();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
