package com.beto.apps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Clientes {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column
private String nombre;
@Column
private Long telefono;
@Column
private String direccion;
@Column
private String usuario;
@Column
private String contraseña;
@Column
private String email;
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
public Long getTelefono() {
	return telefono;
}
public void setTelefono(Long telefono) {
	this.telefono = telefono;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public String getUsuario() {
	return usuario;
}
public void setUsuario(String usuario) {
	this.usuario = usuario;
}
public String getContraseña() {
	return contraseña;
}
public void setContraseña(String contraseña) {
	this.contraseña = contraseña;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String toString() {
	StringBuilder sb= new StringBuilder();
	
	sb.append("[{");
	sb.append("\"username\":");
	sb.append("\""+usuario+"\",");
	sb.append("\"displayName\":");
	sb.append("\""+nombre+"\",");
	sb.append("\"email\":");
	sb.append("\""+email+"\",");
	sb.append("\"direccion\":");
	sb.append("\""+direccion+"\",");
	sb.append("\"telefono\":");
	sb.append("\""+telefono+"\"");
	sb.append("}]");
	return sb.toString();
	
}

}
