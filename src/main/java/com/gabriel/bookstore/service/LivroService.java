package com.gabriel.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.bookstore.model.Categoria;
import com.gabriel.bookstore.model.Livro;
import com.gabriel.bookstore.repositories.LivroRepository;
import com.gabriel.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private CategoriaService categoriaService;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = livroRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto nao encontrado Id: "+id + ",Tipo : " +Livro.class.getName()));
	} 
	
	public List<Livro> findAll(Integer id_cat){		
		categoriaService.findById(id_cat);//para verificar se existe essa categoria
		return livroRepository.findAllByCategoria(id_cat);
	}

	public Livro update(Integer id, Livro obj) {
		Livro newobj = findById(id);
		updateDate(newobj, obj);
		return livroRepository.save(newobj);
		
	}

	private void updateDate(Livro newobj, Livro obj) {
		newobj.setTitulo(obj.getTitulo());
		newobj.setNome_autor(obj.getNome_autor());
		newobj.setTexto(obj.getTexto());
		
	}

	public Livro create(Integer id_cat, Livro obj) {
		obj.setId(null);// para tirar o id pois gera automaticamente no banco de dados
		Categoria cat = categoriaService.findById(id_cat);//para pegar id da categoria
		obj.setCategoria(cat);
		
		return livroRepository.save(obj);
	}

	public void delete(Integer id) {
		Livro obj = findById(id);
		livroRepository.delete(obj);
	}
	
	
}
