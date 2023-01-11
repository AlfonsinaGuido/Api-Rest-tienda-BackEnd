package com.tienda.controlador;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tienda.excepciones.ResourceNotFoundException;
import com.tienda.modelo.Producto;
import com.tienda.repositorio.ProductoRepositorio;

@RestController // defino que no será un controlador sencillo sino que será una api rest.
@RequestMapping("/productos")
public class ProductoControlador {

	@Autowired
	private ProductoRepositorio repositorio;// uso la anotación @Autowired para asegurarme de que la clase
											// ProductoControlador tiene una instancia de ProductoRepositorio disponible
											// para usarla cuando sea necesario.

	@GetMapping("/lista") // indico el endpoint que se corresponde con esta clase. Este método sirve para
							// listar todos los productos.
	public List<Producto> listarTodosLosProductos() {
		return repositorio.findAll();
	}

	// este método sirve para buscar productos con stock considerado como bajo, cuyo
	// valor de tope es variable. Por ejemplo, si indico 10 me devuelve todos los
	// productos que encuentra con valor de stock hasta 10u.
	@GetMapping("/stock-bajo")
	public List<Producto> getProductoConBajoStock(@RequestParam Integer stock) {
		return repositorio.findByStockLessThan(stock);
	}
	
	// este método consulta una lista de productos a una api externa
	@GetMapping("/api-externa")
	public List<Object> getProductosApiExterna() {
		String url = "https://lmz22.wiremockapi.cloud/lista-de-productos";
		RestTemplate restTemplate = new RestTemplate();
		Object[] productos = restTemplate.getForObject(url, Object[].class);
		return Arrays.asList(productos);
	}

	// este método sirve para guardar el producto
	@PostMapping("/guardar")
	public Producto guardarProducto(@RequestBody Producto producto) {
		return repositorio.save(producto);
	}

	// este método sirve para buscar un producto
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
		Producto producto = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el ID : " + id));
		return ResponseEntity.ok(producto);
	}

	// este método sirve para actualizar, dar de baja o volver a dar de alta a un
	// producto (modificando la fecha de baja predeterminada por defecto).
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
		Producto producto = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el ID : " + id));

		producto.setNombre(detallesProducto.getNombre());
		producto.setDescripcion(detallesProducto.getDescripcion());
		producto.setPrecio(detallesProducto.getPrecio());
		producto.setStock(detallesProducto.getStock());
		producto.setFechaAlta(detallesProducto.getFechaAlta());
		producto.setFechaBaja(detallesProducto.getFechaBaja());
		producto.setProveedor(detallesProducto.getProveedor());

		Producto productoActualizado = repositorio.save(producto);
		return ResponseEntity.ok(productoActualizado);
	}
}
