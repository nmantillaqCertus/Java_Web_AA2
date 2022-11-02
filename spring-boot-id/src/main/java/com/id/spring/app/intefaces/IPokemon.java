package com.id.spring.app.intefaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.id.spring.app.model.Pokemon;

@Repository
public interface IPokemon extends CrudRepository<Pokemon, Integer>{

}
