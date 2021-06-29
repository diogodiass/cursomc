package com.diogoamorim.cursomc.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.diogoamorim.cursomc.domain.Cidade;
import com.diogoamorim.cursomc.domain.Estado;

public class EstadoDTO {

	@NotEmpty(message = "Preenchimento Obrigatorio")
	private Integer id;
	@NotEmpty(message = "Preenchimento Obrigatorio")
	private String nome;
	private List<Cidade> cidades = new ArrayList<>();

	public EstadoDTO() {

	}
	
	public EstadoDTO(Estado obj) {
		id = obj.getId();
		nome = obj.getNome();
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

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EstadoDTO [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", cidades=");
		builder.append(cidades);
		builder.append("]");
		return builder.toString();
	}

}
