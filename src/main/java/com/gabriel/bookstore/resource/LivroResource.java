package com.gabriel.bookstore.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.bookstore.dtos.LivroDTO;
import com.gabriel.bookstore.model.Livro;
import com.gabriel.bookstore.service.LivroService;


@CrossOrigin("*")//pode receber requisicoes de diversas fontes 
@RestController
@RequestMapping(value="/livros")
public class LivroResource {
	
	@Autowired
	private LivroService livroService;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Integer id){
		Livro obj = livroService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0")Integer id_cat){
		List<Livro> list = livroService.findAll(id_cat);
		List<LivroDTO> listDto = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PutMapping(value = "/{id}")//Pode atualizar o objeto inteiro
	public ResponseEntity<Livro> update(@Valid@PathVariable Integer id,@Valid@RequestBody Livro obj){
			 Livro newobj = livroService.update(id,obj);
			 return ResponseEntity.ok().body(newobj);
	}
	
	@PatchMapping(value = "/{id}")//patch pode atualizar uma informacao
	public ResponseEntity<Livro> updatePatch(@PathVariable Integer id,@Valid@RequestBody Livro obj){
			 Livro newobj = livroService.update(id,obj);
			 return ResponseEntity.ok().body(newobj);
	}
	
	@PostMapping
	public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat,@Valid @RequestBody Livro obj){//para receber dados do front usa-se @RequestParam
		Livro newobj = livroService.create(id_cat,obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newobj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		livroService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
