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
import com.tienda.modelo.Proveedor;
import com.tienda.repositorio.ProveedorRepositorio;

@RestController // defino que no será un controlador sencillo sino que será una api rest.
@RequestMapping("/proveedores")
public class ProveedorControlador {

	@Autowired
	private ProveedorRepositorio repositorio;// uso la anotación @Autowired para asegurarme de que la clase
												// ProveedorControlador tiene una instancia de ProveedorRepositorio
												// disponible
												// para usarla cuando sea necesario.

	@GetMapping("/lista") // indico el endpoint que se corresponde con esta clase. Este método sirve para
							// listar todos los proveedores.
	public List<Proveedor> listarTodosLosProveedores() {
		return repositorio.findAll();
	}

	// este método sirve para guardar el proveedor
	@PostMapping("/guardar")
	public Proveedor guardarProveedor(@RequestBody Proveedor proveedor) {
		return repositorio.save(proveedor);
	}

	// este método sirve para buscar un proveedor
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable Long id) {
		Proveedor proveedor = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el proveedor con el ID : " + id));
		return ResponseEntity.ok(proveedor);
	}

	// este método sirve para actualizar, dar de baja o volver a dar de alta a un
	// proveedor (modificando la fecha de baja predeterminada por defecto).
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id,
			@RequestBody Proveedor detallesProveedor) {
		Proveedor proveedor = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el proveedor con el ID : " + id));

		proveedor.setNombre(detallesProveedor.getNombre());
		proveedor.setCuit(detallesProveedor.getCuit());
		proveedor.setTelefono(detallesProveedor.getTelefono());
		proveedor.setDireccion(detallesProveedor.getDireccion());
		proveedor.setFechaAlta(detallesProveedor.getFechaAlta());
		proveedor.setFechaBaja(detallesProveedor.getFechaBaja());

		Proveedor proveedorActualizado = repositorio.save(proveedor);
		return ResponseEntity.ok(proveedorActualizado);
	}
}