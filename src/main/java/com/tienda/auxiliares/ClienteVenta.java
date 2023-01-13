package com.tienda.auxiliares;

import java.util.List;

import com.tienda.modelo.Cliente;

public class ClienteVenta { // Objeto auxiliar para representar el conjunto de un cliente con sus compras.
	private Cliente cliente;
	private List<VentaResponse> ventas;

	// constructor vacío
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

}