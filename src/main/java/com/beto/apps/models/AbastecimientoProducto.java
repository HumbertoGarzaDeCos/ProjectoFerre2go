package com.beto.apps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="abastecimientoproducto")
public class AbastecimientoProducto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(name="idprod")
private int idProd;
@Column(name="idabastecimiento")
private int idAbastecimiento;
@Column
private int cantidad;
@Column
private double precio;
@Transient
private String nombreProducto;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getIdProd() {
	return idProd;
}
public void setIdProd(int idProd) {
	this.idProd = idProd;
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
public int getIdAbastecimiento() {
	return idAbastecimiento;
}
public void setIdAbastecimiento(int idAbastecimiento) {
	this.idAbastecimiento = idAbastecimiento;
}
public String getNombreProducto() {
	return nombreProducto;
}
public void setNombreProducto(String nombreProducto) {
	this.nombreProducto = nombreProducto;
}
public String toString() {
	StringBuilder sb= new StringBuilder();
	sb.append("{");
	sb.append("\"nombreProducto\":");
	sb.append("\""+nombreProducto+"\"");
	sb.append(",");
	sb.append("\"cantidad\":");
	sb.append("\""+cantidad+"\"");
	sb.append(",");
	sb.append("\"precio\":");
	sb.append("\""+precio+"\"");
	
	sb.append("}");
	
	return sb.toString();
}
}
