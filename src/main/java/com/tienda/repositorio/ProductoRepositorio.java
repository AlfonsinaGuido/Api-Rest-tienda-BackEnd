package com.tienda.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.modelo.Producto;

@Repository // Genero la interfaz. Le paso la entidad Producto y el tipo de id Long.
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
	List<Producto> findByStockLessThan(Integer stock);
}
