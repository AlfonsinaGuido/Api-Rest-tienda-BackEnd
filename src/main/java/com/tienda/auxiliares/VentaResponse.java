package com.tienda.auxiliares;

import java.math.BigDecimal;
import java.util.Date;

import com.tienda.modelo.Venta;

public class VentaResponse {// objeto auxiliar para detallar los datos que quiero responder de cada venta.
	private String nombreProducto;
    private BigDecimal precioTotal;
    private Date fechaVenta;
    private Integer cantidad;
    
    public VentaResponse(Venta venta) {
        this.nombreProducto = venta.getProducto().getNombre();
        this.precioTotal = venta.getPrecioTotal();
        this.fechaVenta = venta.getFechaVenta();
        this.cantidad = venta.getCantidad();
    }
    //getters
    public String getNombreProducto() {
        return nombreProducto;
    }
    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }
    public Date getFechaVenta() {
        return fechaVenta;
    }
    public Integer getCantidad() {
        return cantidad;
    }
}