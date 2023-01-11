package com.tienda.modelo;

import java.util.Date;
import java.util.List;

//Jakarta Persistence es una evolución de la especificación Javax Persistence y ofrece algunas mejoras y características adicionales sobre la especificación original.
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "clientes") // defino que la tabla se llamará clientes.
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // defino esta estrategia para que el id sea autoincrementable.
	private Long id_Cliente;

	// defino el nombre de las columnas, que la longitud será 60 y que no va a
	// aceptar valores nulos.
	@Column(name = "nombre", length = 60, nullable = false)
	private String nombre;

	@Column(name = "apellido", length = 60, nullable = false)
	private String apellido;

	// con unique defino que el dni no podrá repetirse para otro registro.
	@Column(name = "dni", length = 60, nullable = false, unique = true)
	private String dni;

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

	// private List<Venta> compras;

	// Genero constructor vacío sin argumentos para que si no se incluye código que
	// inicialice los atributos de la clase a valores específicos, estos se
	// inicializarán en sus valores predeterminados.
	public Cliente() {

	}

	// Genero constructor con atributos para crear una instancia de una clase con
	// valores específicos para sus atributos.
	public Cliente(Long id_Cliente, String nombre, String apellido, String dni, String telefono, String direccion,
			Date fechaAlta, Date fechaBaja, List<Venta> compras) {
		super();
		this.id_Cliente = id_Cliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		// this.compras = compras;
	}

	// Genero este constructor para poder pasar en el json de las request de postman
	// el dato numérico directo del id
	public Cliente(Long id) {
		super();
		this.id_Cliente = id;
	}

	// Getters (para obtener el valor de los atributos de la clase) & Setters (para
	// establecer el valor de los atributos de la clase).
	public Long getId_Cliente() {
		return id_Cliente;
	}

	public void setId_Cliente(Long id_Cliente) {
		this.id_Cliente = id_Cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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
	/*
	 * public List<Venta> getCompras() { return compras; }
	 * 
	 * public void setCompras(List<Venta> compras) { this.compras = compras; }
	 */

}
