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
@Table(name = "ventas") // defino que la tabla se llamará ventas.
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // defino esta estrategia para que el id sea autoincrementable.
	private Long id_Venta;

	// defino el nombre de las columnas, que no va a aceptar valores nulos y su
	// tipo.

	@Column(name = "precioTotal", nullable = false, columnDefinition = "DECIMAL(10,2)")
	private BigDecimal precioTotal;

	@Column(name = "cantidad", nullable = false, columnDefinition = "INT")
	private int cantidad;

	@Column(name = "fechaVenta", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE") // indico que la
																									// fecha de venta
																									// será la actual.
	private Date fechaVenta;

	@ManyToOne
	@JoinColumn(name = "id_Producto", nullable = false)
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "id_Cliente", nullable = false)
	private Cliente cliente;

	// Genero constructor vacío sin argumentos para que si no se incluye código que
	// inicialice los atributos de la clase a valores específicos, estos se
	// inicializarán en sus valores predeterminados.
	public Venta() {

	}

	// Genero constructor con atributos para crear una instancia de una clase con
	// valores específicos para sus atributos.
	public Venta(Long id_Venta, BigDecimal precioTotal, int cantidad, Date fechaVenta, Producto producto,
			Cliente cliente) {
		super();
		this.id_Venta = id_Venta;
		this.precioTotal = precioTotal;
		this.cantidad = cantidad;
		this.fechaVenta = fechaVenta;
		this.producto = producto;
		this.cliente = cliente;
	}

	// Getters (para obtener el valor de los atributos de la clase) & Setters (para
	// establecer el valor de los atributos de la clase).
	public Long getId_Venta() {
		return id_Venta;
	}

	public void setId_Venta(Long id_Venta) {
		this.id_Venta = id_Venta;
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

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}