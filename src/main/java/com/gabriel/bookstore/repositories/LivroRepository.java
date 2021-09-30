package com.gabriel.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.gabriel.bookstore.model.Livro;


@Repository
public interface LivroRepository extends JpaRepository<Livro , Integer>{

	@Query("SELECT obj FROM Livro obj WHERE obj.categoria.id = :id_cat ORDER BY titulo")//fazer o select pois essa seria uma nova funcao que foi criado pois nao tinha
	List<Livro> findAllByCategoria(@Param(value = "id_cat")Integer id_cat);

}
