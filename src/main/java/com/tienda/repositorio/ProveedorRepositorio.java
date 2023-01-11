package com.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.modelo.Proveedor;

@Repository // Genero la interfaz. Le paso la entidad Proveedor y el tipo de id Long.
public interface ProveedorRepositorio extends JpaRepository<Proveedor, Long> {

}
