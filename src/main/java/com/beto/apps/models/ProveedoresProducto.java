package com.beto.apps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="proveedorproducto")
public class ProveedoresProducto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(name="idprov")
private int idProv;
@Column(name="idprod")
private int idProd;
@Column
private double precio1;
@Column
private double precio2;
@Column
private double precio3;
@Transient
private String sku;
@Transient
private String nombre;
@Transient
private String activo;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getIdProv() {
	return idProv;
}
public void setIdProv(int idProv) {
	this.idProv = idProv;
}
public int getIdProd() {
	return idProd;
}
public void setIdProd(int idProd) {
	this.idProd = idProd;
}
public double getPrecio1() {
	return precio1;
}
public void setPrecio1(double precio1) {
	this.precio1 = precio1;
}
public double getPrecio2() {
	return precio2;
}
public void setPrecio2(double precio2) {
	this.precio2 = precio2;
}
public double getPrecio3() {
	return precio3;
}
public void setPrecio3(double precio3) {
	this.precio3 = precio3;
}
public String getSku() {
	return sku;
}
public void setSku(String sku) {
	this.sku = sku;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getActivo() {
	return activo;
}
public void setActivo(String activo) {
	this.activo = activo;
}
public String toString() {
	StringBuilder sb= new StringBuilder();
	sb.append("{");
	sb.append("\"nombre\":");
	sb.append("\""+nombre+"\"");
	sb.append(",");
	sb.append("\"idProd\":");
	sb.append("\""+idProd+"\"");
	sb.append(",");
	sb.append("\"sku\":");
	sb.append("\""+sku+"\"");
	sb.append(",");
	sb.append("\"activo\":");
	sb.append("\""+activo+"\"");
	sb.append(",");
	sb.append("\"precio1\":");
	sb.append("\""+precio1+"\"");
	sb.append(",");
	sb.append("\"precio2\":");
	sb.append("\""+precio2+"\"");
	sb.append(",");
	sb.append("\"precio3\":");
	sb.append("\""+precio3+"\"");
	sb.append("}");
	
	
	
	return sb.toString();
	
	
}
}
