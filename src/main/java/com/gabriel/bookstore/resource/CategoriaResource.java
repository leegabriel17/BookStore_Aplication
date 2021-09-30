package com.gabriel.bookstore.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.bookstore.dtos.CategoriaDTO;
import com.gabriel.bookstore.model.Categoria;
import com.gabriel.bookstore.service.CategoriaService;

@RestController
@RequestMapping(value = "categorias")// nome do dns para chamar as categorias
public class CategoriaResource {
	
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id){
		Categoria obj = categoriaService.findById(id);
		return ResponseEntity.ok().body(obj);
	
	}
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>>findAll(){
		List<Categoria> list = categoriaService.findAll();
		List<CategoriaDTO> listDTO = list.stream().map(obj-> new CategoriaDTO(obj)).collect(Collectors.toList());//convert para list da categoria para listdto da categoria onde o list.stream().map(para cada obj ,ele instancia para categoriaDTO assim pegando so os categoria separando do livro e coleta 
		return ResponseEntity.ok().body(listDTO);
	}
	
	
	@PostMapping
	public ResponseEntity<Categoria> created(@Valid @RequestBody Categoria obj){
		obj = categoriaService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //uri de acesso que recebe da servlet com os id,pegando o id do obj novo.
		return ResponseEntity.created(uri).build();//build() ou pode usar body() que retorna direto 
	}
	//UPDATE categoria
	@PutMapping(value="/{id}")//a diferenca entre o post e put e que o post ele gera o id e put nao gera id
	public ResponseEntity<CategoriaDTO> update(@Valid@PathVariable Integer id,@RequestBody CategoriaDTO objDto){
		Categoria newObj= categoriaService.update(id, objDto);
		return ResponseEntity.ok(new CategoriaDTO(newObj));//newObj = categoria newObj= new Categoria
		
	}
	//DELETE categoria
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
