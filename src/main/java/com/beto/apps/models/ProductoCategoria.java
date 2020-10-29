package com.beto.apps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="producto_categoria")
public class ProductoCategoria {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(name="idcat")
private int idCat;
@Column(name="idprod")
private int idProd;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getIdCat() {
	return idCat;
}
public void setIdCat(int idCat) {
	this.idCat = idCat;
}
public int getIdProd() {
	return idProd;
}
public void setIdProd(int idProd) {
	this.idProd = idProd;
}


}
