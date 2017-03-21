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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ronaldo
 */
@Entity
@Table(name = "cargo", catalog = "TEOR")
public class FuncaoTrabalhador implements Comparable<FuncaoTrabalhador>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String excomplementares;
    
    private String agagressores;
   
    @Column(length = 50)
    private String descricao;
    
    @OneToMany(mappedBy = "funcaoTrabalhador")
    private List<Paciente> pacientes = new ArrayList<Paciente>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public int compareTo(FuncaoTrabalhador o) {
        return this.descricao.compareTo(o.getDescricao());
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    public String getExcomplementares() {
        return excomplementares;
    }

    public void setExcomplementares(String excomplementares) {
        this.excomplementares = excomplementares;
    }

    public String getAgagressores() {
        return agagressores;
    }

    public void setAgagressores(String agagressores) {
        this.agagressores = agagressores;
    }
}