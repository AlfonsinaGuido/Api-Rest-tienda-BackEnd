package com.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.modelo.Cliente;

@Repository // Genero la interfaz. Le paso la entidad Cliente y el tipo de id Long.
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}