package com.beto.apps.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beto.apps.models.ProductoCategoria;

@Repository
public interface ProductoCategoriaRepository extends JpaRepository<ProductoCategoria,Integer> {

}
