package com.tienda.modelo;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos") // defino que la tabla se llamará productos.
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // defino esta estrategia para que el id sea autoincrementable.
	private Long id_Producto;

	// defino el nombre de las columnas, su longitud y que no va a aceptar valores
	// nulos.
	@Column(name = "nombre", length = 60, nullable = false)
	private String nombre;

	@Column(name = "descripcion", length = 100, nullable = false)
	private String descripcion;

	@Column(name = "precio", nullable = false, columnDefinition = "DECIMAL(10,2)")
	private BigDecimal precio;

	@Column(name = "stock", nullable = false, columnDefinition = "INT")
	private int stock;

	@Column(name = "fecha_alta", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE") // indico que la
																									// fecha de alta
																									// será la actual.
	private Date fechaAlta;

	@Column(name = "fecha_baja", nullable = false, columnDefinition = "DATE DEFAULT '9999-12-31 00:00:00'") // indico
																											// que la
																											// fecha de
																											// baja será
																											// la
																											// indicada
																											// por
																											// defecto.
	private Date fechaBaja;

	@ManyToOne
	@JoinColumn(name = "id_Proveedor", nullable = false)
	private Proveedor proveedor;

	// Genero constructor vacío sin argumentos para que si no se incluye código que
	// inicialice los atributos de la clase a valores específicos, estos se
	// inicializarán en sus valores predeterminados.
	public Producto() {

	}

	// Genero constructor con atributos para crear una instancia de una clase con
	// valores específicos para sus atributos.
	public Producto(Long id_Producto, String nombre, String descripcion, BigDecimal precio, int stock, Date fechaAlta,
			Date fechaBaja, Proveedor proveedor) {
		super();
		this.id_Producto = id_Producto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.proveedor = proveedor;
	}

	// Genero este constructor para poder pasar en el json de las request de postman
	// el dato numérico directo del id
	public Producto(Long id) {
		super();
		this.id_Producto = id;
	}

	// Getters (para obtener el valor de los atributos de la clase) & Setters (para
	// establecer el valor de los atributos de la clase).
	public Long getId_Producto() {
		return id_Producto;
	}

	public void setId_Producto(Long id_Producto) {
		this.id_Producto = id_Producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}
