package com.tienda.controlador;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.excepciones.ResourceNotFoundException;
import com.tienda.modelo.Venta;
import com.tienda.repositorio.VentaRepositorio;

@RestController // defino que no será un controlador sencillo sino que será una api rest.
@RequestMapping("/ventas")
public class VentaControlador {

	@Autowired
	private VentaRepositorio repositorio;// uso la anotación @Autowired para asegurarme de que la clase
											// VentaControlador tiene una instancia de VentaRepositorio disponible
											// para usarla cuando sea necesario.

	@GetMapping("/lista") // indico el endpoint que se corresponde con esta clase. Este método sirve para
							// listar todas las ventas.
	public List<Venta> listarTodasLasVentas() {
		return repositorio.findAll();
	}

	// este método sirve para guardar la venta
	@PostMapping("/guardar")
	public Venta guardarVenta(@RequestBody Venta venta) {
		return repositorio.save(venta);
	}

	// este método sirve para buscar una venta por id
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable Long id) {
		Venta venta = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe la venta con el ID : " + id));
		return ResponseEntity.ok(venta);
	}

	// este método sirve para buscar las ventas por fecha
	@GetMapping("/filtrar/{fechaVenta}")
	public ResponseEntity<List<Venta>> getVentasPorFechaVenta(
			@PathVariable("fechaVenta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaVenta) {
		List<Venta> venta = repositorio.findByFechaVenta(fechaVenta);
		if (venta.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(venta, HttpStatus.OK);
	}

	// este método sirve para modificar datos de la venta.
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Venta> actualizarVenta(@PathVariable Long id, @RequestBody Venta detallesVenta) {
		Venta venta = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe la venta con el ID : " + id));

		venta.setFechaVenta(detallesVenta.getFechaVenta());
		venta.setPrecioTotal(detallesVenta.getPrecioTotal());
		venta.setCantidad(detallesVenta.getCantidad());

		Venta ventaActualizada = repositorio.save(venta);
		return ResponseEntity.ok(ventaActualizada);
	}
}
