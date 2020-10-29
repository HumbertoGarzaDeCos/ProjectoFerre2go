package com.beto.apps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="productos")
public class Productos {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column
private String nombre;
@Column
private String sku;
@Column
private double precio;
@Column
private String detalles;
@Column(name="dimesniones")
private String dimensiones;
@Transient
private String Categoria;
@Column
private int activo;
@Column
private int cantidad;
@Column
private int alerta;
public void setAlerta(int alerta) {
	this.alerta=alerta;
}
public int getAlerta() {
	return this.alerta;
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
public String getSku() {
	return sku;
}
public void setSku(String sku) {
	this.sku = sku;
}
public double getPrecio() {
	return precio;
}
public void setPrecio(double precio) {
	this.precio = precio;
}
public String getDetalles() {
	return detalles;
}
public void setDetalles(String detalles) {
	this.detalles = detalles;
}
public String getDimensiones() {
	return dimensiones;
}
public void setDimensiones(String dimensiones) {
	this.dimensiones = dimensiones;
}
public int getActivo() {
	return activo;
}
public void setActivo(int activo) {
	this.activo = activo;
}
public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
public String getCategoria() {
	return Categoria;
}
public void setCategoria(String categoria) {
	Categoria = categoria;
}

@Override
public String toString() {
	StringBuilder sb= new StringBuilder();
	sb.append("{");
	sb.append("\"nombre\":");
	sb.append("\""+nombre+"\"");
	sb.append(",");
	sb.append("\"precio\":");
	sb.append(precio);
	sb.append(",");
	sb.append("\"detalles\":");
	sb.append("\""+detalles+"\"");
	sb.append(",");
	sb.append("\"categoria\":");
	sb.append("\""+Categoria+"\"");
	sb.append(",");
	sb.append("\"activo\":");
	sb.append(activo);
	sb.append(",");
	sb.append("\"sku\":");
	sb.append("\""+sku+"\"");
	sb.append(",");
	sb.append("\"imagen\":");
	sb.append("\""+dimensiones+"\"");
	sb.append(",");
	sb.append("\"cantidad\":");
	sb.append(cantidad);
	sb.append(",");
	sb.append("\"minimo\":");
	sb.append(alerta);
	sb.append("}");
	
	return sb.toString();
	
}
}
