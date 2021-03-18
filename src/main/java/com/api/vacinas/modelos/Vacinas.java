
package com.api.vacinas.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import javax.persistence.*;

@Entity // fala para o Hibernate tranformar essa classe em tabela
public class Vacinas {

    @Id // define o parâmetro id como chave primária e é gerado automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idVac;
    @Email(message = "Email deve ser válido")
    @Column(name = "emailVacinado", nullable = false) // define o nome da coluna como "emailVacinado",
                                                                     // não podendo ser nula
    public String emailVacinado;

    @Column(name = "dataAplicacao", nullable = false) // define o nome da coluna como "dataAplicacao", não podendo ser
                                                      // nula
    @PastOrPresent(message = "A data deve ser no passado ou presente") // define que a data deve estar no presente ou
                                                                       // passado
    @JsonFormat(pattern = "dd/MM/yyyy") // formata a data na saída JSON
    private Date dataAplicacao;

    @Column(name = "nomeVacina", nullable = false) // define o nome da coluna como "nomeVacina", não podendo ser nula
    private String nomeVacina;

    public String getEmailVacinado() {
        return emailVacinado;
    }

    public void setEmailVacinado(String emailVacinado) {
        this.emailVacinado = emailVacinado;
    }

    public Date getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(Date dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }
}
