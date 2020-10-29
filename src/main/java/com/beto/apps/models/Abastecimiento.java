package com.beto.apps.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="abastecimiento")
public class Abastecimiento {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column
private LocalDate fecha;
@Column
private int cantidad;
@Column
private int estado;
@Column(name="idproveedor")
private int idProveedor;
@Column(name="preciounitario")
private double precioTotal;
@Transient
private String nombre;
@Transient
private long telefono;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public LocalDate getFecha() {
	return fecha;
}
public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}
public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
public int getEstado() {
	return estado;
}
public void setEstado(int estado) {
	this.estado = estado;
}
public int getIdProveedor() {
	return idProveedor;
}
public void setIdProveedor(int idProveedor) {
	this.idProveedor = idProveedor;
}
public double getPrecioTotal() {
	return precioTotal;
}
public void setPrecioTotal(double precioTotal) {
	this.precioTotal = precioTotal;
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
	sb.append("\"id\":");
	sb.append("\""+id+"\"");
	sb.append(",");
	sb.append("\"nombre\":");
	sb.append("\""+nombre+"\"");
	sb.append(",");
	sb.append("\"estado\":");
	sb.append("\""+estado+"\"");
	sb.append(",");
	sb.append("\"productos\":");
	sb.append("kkkkk");
	sb.append(",");
	sb.append("\"telefono\":");
	sb.append("\""+telefono+"\"");
	sb.append("}");
	return sb.toString();
	
}

}
