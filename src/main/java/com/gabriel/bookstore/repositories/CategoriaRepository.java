package com.gabriel.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.bookstore.model.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria , Integer>{

}
