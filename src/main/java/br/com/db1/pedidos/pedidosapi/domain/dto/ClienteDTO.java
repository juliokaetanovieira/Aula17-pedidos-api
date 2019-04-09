package br.com.db1.pedidos.pedidosapi.domain.dto;

import java.io.Serializable;
import java.util.Objects;

public class ClienteDTO implements Serializable {

	public static final long serialVersionUID = 23L;

	private String nome;
	private String cpf;

	public ClienteDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public ClienteDTO(String nome, String cpf) {
		this.nome = nome;
		this.nome = cpf;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nome, cpf);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ClienteDTO)) {
			return false;
		}
		ClienteDTO other = (ClienteDTO) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(cpf, other.cpf);
	}

	
	
}
