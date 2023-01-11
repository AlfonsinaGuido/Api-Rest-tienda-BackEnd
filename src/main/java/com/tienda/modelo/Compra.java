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
@Table(name = "compras") // defino que la tabla se llamará compras.
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // defino esta estrategia para que el id sea autoincrementable.
	private Long id_Compra;

	// defino el nombre de las columnas, que no va a aceptar valores nulos y su
	// tipo.

	@Column(name = "precioTotal", nullable = false, columnDefinition = "DECIMAL(10,2)")
	private BigDecimal precioTotal;

	@Column(name = "cantidad", nullable = false, columnDefinition = "INT")
	private int cantidad;

	@Column(name = "fechaCompra", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE") // indico que la
																									// fecha de compra
																									// será la actual.
	private Date fechaCompra;

	@ManyToOne
	@JoinColumn(name = "id_Producto", nullable = false)
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "id_Proveedor", nullable = false)
	private Proveedor proveedor;

	// Genero constructor vacío sin argumentos para que si no se incluye código que
	// inicialice los atributos de la clase a valores específicos, estos se
	// inicializarán en sus valores predeterminados.
	public Compra() {

	}

	// Genero constructor con atributos para crear una instancia de una clase con
	// valores específicos para sus atributos.
	public Compra(Long id_Compra, BigDecimal precioTotal, int cantidad, Date fechaCompra, Producto producto,
			Proveedor proveedor) {
		super();
		this.id_Compra = id_Compra;
		this.precioTotal = precioTotal;
		this.cantidad = cantidad;
		this.fechaCompra = fechaCompra;
		this.producto = producto;
		this.proveedor = proveedor;
	}

	// Getters (para obtener el valor de los atributos de la clase) & Setters (para
	// establecer el valor de los atributos de la clase).
	public Long getId_Compra() {
		return id_Compra;
	}

	public void setId_Compra(Long id_Compra) {
		this.id_Compra = id_Compra;
	}

	public BigDecimal getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(BigDecimal precioTotal) {
		this.precioTotal = precioTotal;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}