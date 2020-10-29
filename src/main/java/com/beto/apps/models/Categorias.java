package com.beto.apps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="categorias")
public class Categorias {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column
private String nombre;
@Column
private int activo;
@Transient
private boolean faltantes;
public void setFaltantes(boolean faltantes) {
	this.faltantes=faltantes;
}
public boolean getFaltantes() {
	return faltantes;
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
public int getActivo() {
	return activo;
}
public void setActivo(int activo) {
	this.activo = activo;
}
@Override
public String toString() {
	StringBuilder sb= new StringBuilder();
	sb.append("{");
	sb.append("\"nombre\":");
	sb.append("\""+nombre+"\"");
	sb.append(",");
	sb.append("\"activo\":");
	sb.append("\""+activo+"\"");
	sb.append(",");
	sb.append("\"productos\":");
	sb.append("kkkkk");	
	sb.append("}");	
	return sb.toString();
	
}

}
