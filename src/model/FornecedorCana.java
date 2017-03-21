/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import model.medicinatrabalho.GuiaMedicinaTrabalho;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author ronaldo
 */
@Entity
@Table(name = "fornecedores",catalog = "TEOR")
public class FornecedorCana implements Comparable<FornecedorCana>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDFornecedor;
    
    @Column(length = 50)
    private String Nome;
    
    @Column(length = 50)
    private String Endereco;
    
    @Column(length = 30)
    private String Bairro;

    @OneToMany(mappedBy = "fornecedorCana")
    private List<Paciente> pacientes = new ArrayList<>(); 
    
    @OneToMany(mappedBy = "fornecedorCana")
    private List<GuiaMedicinaTrabalho> guiasMedicinaTrabalho;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "cidade")
    @NotFound(action=NotFoundAction.IGNORE)    
    private Cidade cidade;

    public Long getIDFornecedor() {
        return IDFornecedor;
    }

    public void setIDFornecedor(Long IDFornecedor) {
        this.IDFornecedor = IDFornecedor;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<GuiaMedicinaTrabalho> getGuiasMedicinaTrabalho() {
        return guiasMedicinaTrabalho;
    }

    public void setGuiasMedicinaTrabalho(List<GuiaMedicinaTrabalho> guiasMedicinaTrabalho) {
        this.guiasMedicinaTrabalho = guiasMedicinaTrabalho;
    }

    @Override
    public int compareTo(FornecedorCana o) {
        return this.Nome.compareTo(o.getNome());
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
