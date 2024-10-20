package org.generation.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//POJO Plain Old Java Object

@Entity//se utiliza en JPA (Java Persistence API) para indicar que una clase es una entidad, es decir, que está mapeada a una tabla en una base de datos. Cada instancia de la clase representa una fila en la tabla
@Table(name="productos")// se utiliza en JPA para especificar el nombre de la tabla en la base de datos a la que se va a mapear la clase. Mientras que @Entity simplemente indica que la clase es una entidad de JPA, @Table permite personalizar el nombre de la tabla.Si no usas @Table, JPA asumirá que la tabla en la base de datos tiene el mismo nombre que la clase. Sin embargo, si el nombre de la tabla es diferente, debes usar @Table para indicar el nombre correcto.
public class Producto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	@Column(nullable=false)
	private String nombre;
	private String descripcion;
	private String imagen;
	@Column(nullable=false)
	private double precio;
	private Long categoria_id;
	//private static Long total = Long.valueOf(0);//solo para version con arrayList

	//1.constructor(nombre, descripcion, imagen, precio)
	public Producto(String nombre, String descripcion, String imagen, double precio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.precio = precio;
		//Producto.total++;
		//id = Producto.total;
	}
	
	public Producto(){
		//Producto.total++;
		//id = Producto.total;
	}//constructor

	//2.setters and getters
	public Long getId() {
		return id;
	}//getId

	public void setId(Long id) {
		this.id = id;
	}//setId

	public String getNombre() {
		return nombre;
	}//getNombre

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}//setNombre

	public String getDescripcion() {
		return descripcion;
	}//getDescripcion

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}//setDescripcion

	public String getImagen() {
		return imagen;
	}//getImagen

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}//setImagen

	public double getPrecio() {
		return precio;
	}//getPrecio

	public void setPrecio(double precio) {
		this.precio = precio;
	}//setPrecio	

	public Long getCategoria_id() {
		return categoria_id;
	}//getCategoria_id

	public void setCategoria_id(Long categoria_id) {
		this.categoria_id = categoria_id;
	}//setCategoria_id

	//3.toString
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen=" + imagen
				+ ", precio=" + precio + ", getId()=" + getId() + ", getNombre()=" + getNombre() + ", getDescripcion()="
				+ getDescripcion() + ", getImagen()=" + getImagen() + ", getPrecio()=" + getPrecio() + "]";
	}//toString	
}//class Producto
