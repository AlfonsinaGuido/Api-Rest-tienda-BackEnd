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
import com.tienda.modelo.Cliente;
import com.tienda.repositorio.ClienteRepositorio;

@RestController // defino que no será un controlador sencillo sino que será una api rest.
@RequestMapping("/clientes")
public class ClienteControlador {

	@Autowired
	private ClienteRepositorio repositorio;// uso la anotación @Autowired para asegurarme de que la clase
											// ClienteControlador tiene una instancia de ClienteRepositorio disponible
											// para usarla cuando sea necesario.

	@GetMapping("/lista") // indico el endpoint que se corresponde con esta clase. Este método sirve para
							// listar todos los clientes.
	public List<Cliente> getTodosLosClientesConCompras() {
		return repositorio.findAll();
	}

	// este método sirve para guardar el cliente
	@PostMapping("/guardar")
	public Cliente guardarCliente(@RequestBody Cliente cliente) {
		return repositorio.save(cliente);
	}

	// este método sirve para buscar un cliente
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
		Cliente cliente = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el cliente con el ID : " + id));
		return ResponseEntity.ok(cliente);
	}

	// este método sirve para actualizar, dar de baja o volver a dar de alta a un
	// cliente (modificando la fecha de baja predeterminada por defecto).
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente detallesCliente) {
		Cliente cliente = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el cliente con el ID : " + id));

		cliente.setNombre(detallesCliente.getNombre());
		cliente.setApellido(detallesCliente.getApellido());
		cliente.setDni(detallesCliente.getDni());
		cliente.setTelefono(detallesCliente.getTelefono());
		cliente.setDireccion(detallesCliente.getDireccion());
		cliente.setFechaAlta(detallesCliente.getFechaAlta());
		cliente.setFechaBaja(detallesCliente.getFechaBaja());

		Cliente clienteActualizado = repositorio.save(cliente);
		return ResponseEntity.ok(clienteActualizado);
	}

}