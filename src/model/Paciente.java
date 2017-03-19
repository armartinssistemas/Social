/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import model.medicinatrabalho.GuiaMedicinaTrabalho;

@Entity
@Table(name = "paciente",catalog = "TEOR")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "flag", length = 6, discriminatorType = DiscriminatorType.STRING)
public abstract class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 15)
    private String telefone;
    
    @Column(length = 15)
    private String celular;
    
    @Column(length = 20)
    private String rg;
    
    @Column(length = 20)
    private String cpf;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "fornecedorCana", referencedColumnName = "IDFornecedor")
    private FornecedorCana fornecedorCana;
    
    @OneToMany(mappedBy = "paciente")
    private List<GuiaMedicinaTrabalho> guiasMedicinaTrabalho;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public FornecedorCana getFornecedorCana() {
        return fornecedorCana;
    }

    public void setFornecedorCana(FornecedorCana fornecedorCana) {
        this.fornecedorCana = fornecedorCana;
    }

    public List<GuiaMedicinaTrabalho> getGuiasMedicinaTrabalho() {
        return guiasMedicinaTrabalho;
    }

    public void setGuiasMedicinaTrabalho(List<GuiaMedicinaTrabalho> guiasMedicinaTrabalho) {
        this.guiasMedicinaTrabalho = guiasMedicinaTrabalho;
    }
}
