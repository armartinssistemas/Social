/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.permissao;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ronaldo
 */
@Entity
@Table(name = "usuarios_teor", catalog = "TEOR")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
    private Date dtexpiracao;
    private String email;
    private String nome;
    private Date dtultacesso;
    private Date datacad;
    //private Usuario usercad;
    private Date dataupdate;
    //private Usuario userupdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDtexpiracao() {
        return dtexpiracao;
    }

    public void setDtexpiracao(Date dtexpiracao) {
        this.dtexpiracao = dtexpiracao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtultacesso() {
        return dtultacesso;
    }

    public void setDtultacesso(Date dtultacesso) {
        this.dtultacesso = dtultacesso;
    }

    public Date getDatacad() {
        return datacad;
    }

    public void setDatacad(Date datacad) {
        this.datacad = datacad;
    }

    public Date getDataupdate() {
        return dataupdate;
    }

    public void setDataupdate(Date dataupdate) {
        this.dataupdate = dataupdate;
    }
    
    
}
