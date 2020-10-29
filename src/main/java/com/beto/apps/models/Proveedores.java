package com.beto.apps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="proveedores")
public class Proveedores {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column
private String nombre;
@Column
private long telefono;
@Transient
private String productos;
public String getProductos() {
	return productos;
}
public void setProductos(String productos) {
	this.productos = productos;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public long getTelefono() {
	return telefono;
}
public void setTelefono(long telefono) {
	this.telefono = telefono;
}
public String toString() {
	StringBuilder sb= new StringBuilder();
	sb.append("{");
	sb.append("\"nombre\":");

	sb.append("\""+nombre+"\"");
	sb.append(",");
	sb.append("\"telefono\":");
	sb.append("\""+telefono+"\"");

	sb.append(",");
	sb.append("\"productos\":");
	sb.append("kkkkk");
	sb.append("}");
	
	
	return sb.toString();
	
}
}
