package com.gabriel.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.bookstore.dtos.CategoriaDTO;
import com.gabriel.bookstore.model.Categoria;
import com.gabriel.bookstore.repositories.CategoriaRepository;
import com.gabriel.bookstore.service.exceptions.DataIntegrityViolationException;
import com.gabriel.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	
	public Categoria create(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Categoria update(Integer id, CategoriaDTO objDto) {
		Categoria obj = findById(id);
		obj.setNome(objDto.getNome());
		obj.setDescricao(objDto.getDescricao());
		
		return categoriaRepository.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			//throw new DataIntegrityViolationException("Categoria nao pode ser deletada! Possui livros associado");
			throw new DataIntegrityViolationException("Categoria nao pode ser deletada! Possui livros associado");
		}
		
	}
	
}
