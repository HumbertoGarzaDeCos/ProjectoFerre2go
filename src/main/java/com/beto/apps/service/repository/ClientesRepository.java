package com.beto.apps.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beto.apps.models.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes,Integer>{

}
