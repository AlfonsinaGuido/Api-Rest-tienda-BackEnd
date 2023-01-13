package com.tienda.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
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
	public List<Producto> getListarTodosLosProductos() {
		return repositorio.findAll();
	}

	// este método sirve para buscar productos con stock considerado como bajo, cuyo
	// valor de tope es variable. Por ejemplo, si indico 10 me devuelve todos los
	// productos que encuentra con valor de stock hasta 10u.
	@GetMapping("/stock-bajo")
	public List<Producto> getProductoConBajoStock(@RequestParam Integer stock) {
		return repositorio.findByStockLessThan(stock);
	}

	// este método consulta una lista de productos a una api externa y la devuelve
	// con los campos modificados.
	@GetMapping("/api-externa")
	public List<Map<String, Object>> getProductosApiExterna() throws Exception {
		try {
			RestTemplate restTemplate = new RestTemplate();// creo una nueva instancia de la clase RestTemplate, que se
															// utilizará para hacer la solicitud GET a la URL externa.
			String url = "https://lmz22.wiremockapi.cloud/lista-de-productos";// asigno la URL de la API externa a una
																				// variable de cadena.
			// utilizo el método exchange de RestTemplate para hacer una solicitud GET a la
			// URL especificada y especifica el tipo de objeto que espera recibir como
			// respuesta (en este caso una lista de mapas con clave de tipo string y valor
			// de tipo object). El resultado se almacena en una variable de respuesta.
			ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(url, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Map<String, Object>>>() {
					});
			List<Map<String, Object>> productos = response.getBody();// extraigo el cuerpo de la respuesta y lo asigno a
																		// una lista de mapas.
			for (Map<String, Object> producto : productos) {// bucle que recorre cada elemento de la lista de productos.
				producto.put("name", producto.get("productName"));// agrego un nuevo elemento con clave "name" y valor
																	// del valor actual del elemento con clave
																	// "productName" al mapa del producto actual.
				producto.remove("productName");// elimino el elemento con clave "productName" del mapa del producto
												// actual.
				producto.put("sku", producto.get("productCode"));
				producto.remove("productCode");
				producto.put("stock", producto.get("productQuantity"));
				producto.remove("productQuantity");
				producto.put("imgUrl", producto.get("productImg"));
				producto.remove("productImg");
			}
			return productos;// retorno la lista de productos modificada
		} catch (RestClientException ex) {// capturo la excepción genérica que se utiliza para indicar errores en las
											// solicitudes HTTP que se realizan mediante el RestTemplate. Esto puede
											// incluir problemas de conexión, problemas de autenticación, problemas de
											// red, entre otros.
			System.out.println("Error en la peticion: " + ex.getMessage());// imprimo en consola el error específico.
			throw new Exception("Error al obtener productos", ex);// creo una nueva excepción con un mensaje e indico
																	// que se genera por la excepción de referencia.
		}
	}

	// este método sirve para guardar el producto
	@PostMapping("/guardar")
	public Producto postGuardarProducto(@RequestBody Producto producto) {
		return repositorio.save(producto);
	}

	// este método sirve para buscar un producto
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Producto> getObtenerProductoPorId(@PathVariable Long id) {
		Producto producto = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el ID : " + id));
		return ResponseEntity.ok(producto);
	}

	// este método sirve para actualizar, dar de baja o volver a dar de alta a un
	// producto (modificando la fecha de baja predeterminada por defecto).
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Producto> putActualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
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
