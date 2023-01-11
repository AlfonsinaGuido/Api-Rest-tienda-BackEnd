package com.tienda.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.excepciones.ResourceNotFoundException;
import com.tienda.modelo.Compra;
import com.tienda.repositorio.CompraRepositorio;

@RestController // defino que no será un controlador sencillo sino que será una api rest.
@RequestMapping("/compras")
public class CompraControlador {

	@Autowired
	private CompraRepositorio repositorio;// uso la anotación @Autowired para asegurarme de que la clase
											// CompraControlador tiene una instancia de CompraRepositorio disponible
											// para usarla cuando sea necesario.

	@GetMapping("/lista") // indico el endpoint que se corresponde con esta clase. Este método sirve para
							// listar todas las compras.
	public List<Compra> listarTodosLasCompras() {
		return repositorio.findAll();
	}

	// este método sirve para buscar las compras por proveedor
	@GetMapping("/filtrar/{id_Proveedor}")
	public List<Compra> getComprasPorProveedor(@PathVariable Long id_Proveedor) {
		return repositorio.findById_Proveedor(id_Proveedor);
	}

	// este método sirve para guardar la compra
	@PostMapping("/guardar")
	public Compra guardarCompra(@RequestBody Compra compra) {
		return repositorio.save(compra);
	}

	// este método sirve para buscar una compra
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Compra> obtenerCompraPorId(@PathVariable Long id) {
		Compra compra = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe la compra con el ID : " + id));
		return ResponseEntity.ok(compra);
	}

	// este método sirve para modificar datos de la compra.
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Compra> actualizarCompra(@PathVariable Long id, @RequestBody Compra detallesCompra) {
		Compra compra = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe la compra con el ID : " + id));

		compra.setFechaCompra(detallesCompra.getFechaCompra());
		compra.setPrecioTotal(detallesCompra.getPrecioTotal());
		compra.setCantidad(detallesCompra.getCantidad());

		Compra compraActualizada = repositorio.save(compra);
		return ResponseEntity.ok(compraActualizada);
	}
}
