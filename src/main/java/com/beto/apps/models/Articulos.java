package com.beto.apps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="articulos")
public class Articulos {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(name="idpedido")
private int idPedido;
@Column(name="idproducto")
private int idProducto;
@Column
private int cantidad;
@Column
private double precio;
@Transient
private String nombre;
@Transient
private double total;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getIdPedido() {
	return idPedido;
}
public void setIdPedido(int idPedido) {
	this.idPedido = idPedido;
}
public int getIdProducto() {
	return idProducto;
}
public void setIdProducto(int idProducto) {
	this.idProducto = idProducto;
}
public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
public double getPrecio() {
	return precio;
}
public void setPrecio(double precio) {
	this.precio = precio;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
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
	sb.append("\""+nombre+"\"");
	sb.append(",");
	sb.append("\"cantidad\":");
	sb.append(cantidad);
	sb.append(",");
	sb.append("\"precioUnitario\":");
	sb.append(precio);
	sb.append(",");
	sb.append("\"precioTotal\":");
	sb.append(total);
	sb.append("}");
	return sb.toString();
}
}
