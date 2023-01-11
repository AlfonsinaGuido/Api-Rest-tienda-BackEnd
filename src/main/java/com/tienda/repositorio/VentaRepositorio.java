package com.tienda.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.modelo.Venta;

@Repository // Genero la interfaz. Le paso la entidad Venta y el tipo de id Long.
public interface VentaRepositorio extends JpaRepository<Venta, Long> {
	List<Venta> findByFechaVenta(Date fechaVenta);
}
