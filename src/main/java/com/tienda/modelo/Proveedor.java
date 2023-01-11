package com.tienda.modelo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "proveedores") // defino que la tabla se llamará proveedores.
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // defino esta estrategia para que el id sea autoincrementable.
	private Long id_Proveedor;

	// defino el nombre de las columnas, que la longitud será 60 y que no va a
	// aceptar valores nulos.
	@Column(name = "nombre", length = 60, nullable = false)
	private String nombre;

	// con unique defino que el cuit no podrá repetirse para otro registro.
	@Column(name = "cuit", length = 60, nullable = false, unique = true)
	private String cuit;

	@Column(name = "telefono", length = 60, nullable = false)
	private String telefono;

	@Column(name = "direccion", length = 60, nullable = false)
	private String direccion;

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

	// Genero constructor vacío sin argumentos para que si no se incluye código que
	// inicialice los atributos de la clase a valores específicos, estos se
	// inicializarán en sus valores predeterminados.
	public Proveedor() {

	}

	// Genero constructor con atributos para crear una instancia de una clase con
	// valores específicos para sus atributos.
	public Proveedor(Long id_Proveedor, String nombre, String cuit, String telefono, String direccion, Date fechaAlta,
			Date fechaBaja) {
		super();
		this.id_Proveedor = id_Proveedor;
		this.nombre = nombre;
		this.cuit = cuit;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
	}

	// Genero este constructor para poder pasar en el json de las request de postman
	// el dato numérico directo del id
	public Proveedor(Long id) {
		super();
		this.id_Proveedor = id;
	}

	// Getters (para obtener el valor de los atributos de la clase) & Setters (para
	// establecer el valor de los atributos de la clase).

	public Long getId_Proveedor() {
		return id_Proveedor;
	}

	public void setId_Proveedor(Long id_Proveedor) {
		this.id_Proveedor = id_Proveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

}
