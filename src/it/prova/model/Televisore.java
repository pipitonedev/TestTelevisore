package it.prova.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Televisore {

	private Long id;
	private String marca;
	private String modello;
	private Date dataProduzione;

	public Televisore() {

	}

	public Televisore(Long id, String marca, String modello, Date dataProduzione) {
		super();
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.dataProduzione = dataProduzione;
	}

	public Televisore(String marca, String modello, Date dataProduzione) {
		super();
		this.marca = marca;
		this.modello = modello;
		this.dataProduzione = dataProduzione;
	}

	public Televisore(String marca, String modello) {
		super();
		this.marca = marca;
		this.modello = modello;
	}

	public Televisore(String marca) {
		super();
		this.marca = marca;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public Date getDataProduzione() {
		return dataProduzione;
	}

	public void setDataProduzione(Date dataProduzione) {
		this.dataProduzione = dataProduzione;
	}

	public String toString() {
		String dateCreatedString = dataProduzione != null ? new SimpleDateFormat("dd/MM/yyyy").format(dataProduzione)
				: " N.D.";

		return "Televisore [id=" + id + ", marca: " + marca + ", modello: " + modello + ", dataproduzione="
				+ dateCreatedString + "]";
	}

}
