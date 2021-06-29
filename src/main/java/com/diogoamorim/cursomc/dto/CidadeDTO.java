package com.diogoamorim.cursomc.dto;

import com.diogoamorim.cursomc.domain.Cidade;
import com.diogoamorim.cursomc.domain.Estado;

public class CidadeDTO {


	private Integer id;
	private String nome;
	private Estado id_estado;


	public CidadeDTO() {

	}
	
	public CidadeDTO(Cidade obj) {
		id = obj.getId();
		nome = obj.getNome();
		setId_estado(obj.getEstado());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getId_estado() {
		return id_estado;
	}

	public void setId_estado(Estado id_estado) {
		this.id_estado = id_estado;
	}
	
}
