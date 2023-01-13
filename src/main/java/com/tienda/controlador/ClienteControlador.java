package com.tienda.controlador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.auxiliares.ClienteVenta;
import com.tienda.excepciones.ResourceNotFoundException;
import com.tienda.modelo.Cliente;
import com.tienda.modelo.Venta;
import com.tienda.repositorio.ClienteRepositorio;
import com.tienda.repositorio.VentaRepositorio;
import com.tienda.auxiliares.VentaResponse;

@RestController // defino que no será un controlador sencillo sino que será una api rest.
@RequestMapping("/clientes")
public class ClienteControlador {

	@Autowired
	private ClienteRepositorio repositorio;// uso la anotación @Autowired para asegurarme de que la clase
											// ClienteControlador tiene una instancia de ClienteRepositorio disponible
											// para usarla cuando sea necesario.
	@Autowired
	private VentaRepositorio repositorioVentas;

	// método para retornar la lista de clientes con sus compras (la relación es
	// entre las clases Cliente y Venta) Creo dos clases auxiliares para poder
	// implementar el objetivo.
	@GetMapping("/lista")
	public List<ClienteVenta> getTodosLosClientesConCompras() {// declaro el método y especifico que devuelvo una lista
																// de objetos "ClienteVenta".
		List<Venta> ventas = repositorioVentas.findAll();
		List<ClienteVenta> clientesVentas = new ArrayList<ClienteVenta>();// creo una nueva lista vacía para almacenar
																			// los objetos "ClienteVenta".
		Set<Cliente> clientes = new HashSet<Cliente>();// creo un nuevo conjunto vacío para almacenar los clientes que
														// ya se han procesado.
		for (Venta venta : ventas) {// genero bucle for que itera a través de la lista de ventas.
			Cliente cliente = venta.getCliente();// obtengo el objeto cliente de la venta actual.
			if (!clientes.contains(cliente)) {// verifico si el cliente actual ya ha sido procesado. Si no es así, entra
												// en el bloque del if.
				clientes.add(cliente);// agrego el cliente actual al conjunto de clientes procesados.
				ClienteVenta clienteVenta = new ClienteVenta();// creo un nuevo objeto "ClienteVenta".
				clienteVenta.setCliente(cliente);// establezco el objeto cliente en el objeto "ClienteVenta".
				List<VentaResponse> ventasResponse = new ArrayList<VentaResponse>();// creo una nueva lista vacía para
																					// almacenar los objetos
																					// "VentaResponse" para el cliente
																					// actual.
				VentaResponse ventaResponse = new VentaResponse(venta);// creo un nuevo objeto "VentaResponse" para la
																		// venta actual.
				ventasResponse.add(ventaResponse);// agrego el objeto "VentaResponse" a la lista de objetos
													// "VentaResponse" para el cliente actual.
				clienteVenta.setVentas(ventasResponse);// establezco la lista de objetos "VentaResponse" en el objeto
														// "ClienteVenta".
				clientesVentas.add(clienteVenta);// agrego el objeto "ClienteVenta" a la lista de objetos
													// "ClienteVenta".
			} else {
				for (ClienteVenta clienteVenta : clientesVentas) {// genero bucle for que itera a través de la lista de
																	// objetos "ClienteVenta".
					if (clienteVenta.getCliente().equals(cliente)) {// verifico si el objeto "ClienteVenta" actual tiene
																	// el mismo cliente que el cliente actual. Si es
																	// así, entra en el bloque del if.
						VentaResponse ventaResponse = new VentaResponse(venta);// creo un nuevo objeto "VentaResponse"
																				// para la venta actual.
						clienteVenta.getVentas().add(ventaResponse);// agrego el objeto "VentaResponse" a la lista de
																	// objetos "VentaResponse" del objeto "ClienteVenta"
																	// actual.
					}
				}
			}
		}
		return clientesVentas;// devuelvo la lista de objetos "ClienteVenta" que contiene los clientes y sus
								// respectivas ventas.
	}

	// este método sirve para guardar el cliente
	@PostMapping("/guardar")
	public Cliente postGuardarCliente(@RequestBody Cliente cliente) {
		return repositorio.save(cliente);
	}

	// este método sirve para buscar un cliente
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Cliente> getObtenerClientePorId(@PathVariable Long id) {
		Cliente cliente = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el cliente con el ID : " + id));
		return ResponseEntity.ok(cliente);
	}

	// este método sirve para actualizar, dar de baja o volver a dar de alta a un
	// cliente (modificando la fecha de baja predeterminada por defecto).
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Cliente> putActualizarCliente(@PathVariable Long id, @RequestBody Cliente detallesCliente) {
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