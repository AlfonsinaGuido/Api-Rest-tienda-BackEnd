package com.tienda.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tienda.modelo.Compra;

@Repository // Genero la interfaz. Le paso la entidad Compra y el tipo de id Long.
public interface CompraRepositorio extends JpaRepository<Compra, Long> {
	@Query("SELECT p FROM Compra p WHERE p.proveedor.id = :id_Proveedor")
	List<Compra> findById_Proveedor(@Param("id_Proveedor") Long id_Proveedor);
}
