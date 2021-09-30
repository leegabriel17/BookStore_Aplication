package com.gabriel.bookstore.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.bookstore.model.Categoria;
import com.gabriel.bookstore.model.Livro;
import com.gabriel.bookstore.repositories.CategoriaRepository;
import com.gabriel.bookstore.repositories.LivroRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRepository livroRepository;

	public void instanciaBasedeDados() {
		Categoria cat1 = new Categoria(null, "informatica", "Livros de TI");
		Categoria cat2 = new Categoria(null, "Ficcao Cientifica", "Ficcao Cientifica");
		Categoria cat3 = new Categoria(null, "Biografia", "Liros de Biografia");

		Livro l1 = new Livro(null, "Clean code", "Robert Martin", "Lorem ipsum", cat1);// na criacao ja vincula a
																						// categoria
		Livro l2 = new Livro(null, "Engenharia de Software", "Louis v.Gerstner", "Lorem ipsum", cat1);
		Livro l3 = new Livro(null, "the time machine", "H.G. Wells", "Lorem ipsum", cat2);
		Livro l4 = new Livro(null, "the war of the worlds", "H.G. Wells", "Lorem ipsum", cat2);
		Livro l5 = new Livro(null, "I, Robot", "Issac Asimov", "Lorem ipsum", cat2);

		cat1.getLivros().addAll(Arrays.asList(l1, l2));// conectar a categoria com o livro
		cat2.getLivros().addAll(Arrays.asList(l3, l4, l5));

		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));// salvando a categoria, que esta com list
																			// assim pode salvar varios categorias
		this.livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));

	}
}
