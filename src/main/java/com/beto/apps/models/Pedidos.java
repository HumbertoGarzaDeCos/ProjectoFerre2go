package com.beto.apps.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedidos")
public class Pedidos {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column( name="numarticulos")
private int numArticulos;
@Column	( name="nombrecliente")
private String nombrecliente;
@Column	( name="direccion")
private String direccion;
@Column	( name="telefono")
private Long telefono;
@Column	( name="estado")
private int estado;
@Column	( name="idcliente")
private int idCliente;
@Column	( name="articulosid")
private int articulosId;
@Column(name="creacion")
private LocalDate fechaCreacion;
@Column(name="actualizado")
private LocalDate fechaActualizacion;
@Column 
private double total;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getNumArticulos() {
	return numArticulos;
}
public void setNumArticulos(int numArticulos) {
	this.numArticulos = numArticulos;
}
public String getNombrecliente() {
	return nombrecliente;
}
public void setNombrecliente(String nombrecliente) {
	this.nombrecliente = nombrecliente;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public Long getTelefono() {
	return telefono;
}
public void setTelefono(Long telefono) {
	this.telefono = telefono;
}
public int getEstado() {
	return estado;
}
public void setEstado(int estado) {
	this.estado = estado;
}
public int getIdCliente() {
	return idCliente;
}
public void setIdCliente(int idCliente) {
	this.idCliente = idCliente;
}
public int getArticulosId() {
	return articulosId;
}
public LocalDate getFechaCreacion() {
	return fechaCreacion;
}
public void setFechaCreacion(LocalDate fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
}
public LocalDate getFechaActualizacion() {
	return fechaActualizacion;
}
public void setFechaActualizacion(LocalDate fechaActualizacion) {
	this.fechaActualizacion = fechaActualizacion;
}
public void setArticulosId(int articulosId) {
	this.articulosId = articulosId;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
public String toString() {
	StringBuilder sb=new StringBuilder();
	sb.append("{");
	sb.append("\"nombre\":");
	sb.append("\""+nombrecliente+"\"");
	sb.append(",");
	sb.append("\"direccion\":");
	sb.append("\""+direccion+"\"");
	sb.append(",");
	sb.append("\"telefono\":");
	sb.append("\""+telefono+"\"");
	sb.append(",");
	sb.append("\"fecha\":");
	sb.append("\""+fechaCreacion+"\"");
	sb.append(",");
	sb.append("\"estado\":");
	sb.append(estado);
	sb.append(",");
	sb.append("\"productos\":");
	sb.append("kkkkk");
	sb.append("}");
	return sb.toString();
}
}
