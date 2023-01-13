package com.tienda.auxiliares;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.tienda.modelo.Cliente;

public class ClienteVenta { // Objeto auxiliar para representar el conjunto de un cliente con sus compras.
	private Cliente cliente;
	private List<VentaResponse> ventas;
	private String nombreProducto;
	private BigDecimal precioTotal;
	private Date fechaVenta;
	private int cantidad;

	// constructor vacio
	public ClienteVenta() {
	}

	// getters y setters
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<VentaResponse> getVentas() {
		return ventas;
	}

	public void setVentas(List<VentaResponse> ventas) {
		this.ventas = ventas;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public BigDecimal getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(BigDecimal precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}