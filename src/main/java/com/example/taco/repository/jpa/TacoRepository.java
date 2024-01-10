package com.example.taco.repository.jpa;

import com.example.taco.model.Taco;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
