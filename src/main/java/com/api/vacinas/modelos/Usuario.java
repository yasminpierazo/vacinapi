package com.api.vacinas.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

import javax.persistence.*;

@Entity // fala para o Hibernate tranformar essa classe em tabela
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }),  //define as
@UniqueConstraint(columnNames = "cpf") })									//colunas "cpf" "email"
																			//únicas
public class Usuario {
																											
	@Id // define o parâmetro id como chave primária e é gerado automaticamente
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Email(message = "Email deve ser válido")
	@Column(name = "email", nullable = false, unique = true) // define o nome da coluna como "email", não podendo ser nula
	private String email;

	@Column(name = "dataNascimento", nullable = false) // define o nome da coluna como "dataNascimento", não podendo ser nula
	@PastOrPresent(message = "A data deve ser no passado ou presente")//define que a data deve estar no presente ou passado
	@JsonFormat(pattern = "dd/MM/yyyy")//formata a data na saída JSON
	private Date dataNascimento;

	@Column(name = "nome", nullable = false) // define o nome da coluna como "nome", não podendo ser nula
	private String nome;

	@Column(name = "cpf", nullable = false, unique = true) // define o nome da coluna como "cpf", nao podendo ser nula
	@CPF(message = "CPF inválido")//verifica se o cpf é válido
	private String cpf;


	//função de formatação do cpf
	public String formatar(String cpfCon) {
		/*
		 * Irá converter CPF não formatado para um com pontos e traço. Ex.: 35524519887
		 * torna-se 355.245.198-87.
		 */
		cpfCon = cpfCon.replaceAll("\\.", "").replaceAll("\\-", "");

		/*
		 * Irá converter CPF formatado para um sem pontos e traço. Ex.: 355.245.198-87
		 * torna-se 35524519887.
		 */
		cpfCon = cpfCon.substring(0, 3) + "." + cpfCon.substring(3, 6) + "." + cpfCon.substring(6, 9) + "-"
				+ cpfCon.substring(9, 11);

		return cpfCon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		cpf = formatar(cpf);
		return cpf;

	}

	public void setCPF(String cpf) {

		this.cpf = cpf = formatar(cpf);
	}
}
