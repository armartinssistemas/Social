/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.medicinatrabalho;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import model.FuncaoTrabalhador;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author ronaldo
 */
@Entity
@Table(name = "medicinatrabalho_modeloexames", schema = "TEOR")
public class Modeloexames {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmodelo")
    private Long idModelo;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "idcargo")
    private FuncaoTrabalhador cargo;

    @ManyToOne(optional = true)
    @JoinColumn(name = "idtipomedicinatrabalho")    
    private TipoMedicinaTrabalho tipoMedicinaTrabalho;
    
    private String agentesAgressores;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinTable(name = "medicinatrabalho_cargo_examecomplementar", catalog = "TEOR", 
            joinColumns = { 
                    @JoinColumn(name = "idmodeloexames", nullable = false, updatable = false) 
            },        
            inverseJoinColumns = { 
                @JoinColumn(name = "idexamecomplementar", 
                                    nullable = false, updatable = false) 
            }
    )
    private List<ExameComplementar> examesComplementares = new ArrayList<>();    

    public Long getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Long idModelo) {
        this.idModelo = idModelo;
    }

    public FuncaoTrabalhador getCargo() {
        return cargo;
    }

    public void setCargo(FuncaoTrabalhador cargo) {
        this.cargo = cargo;
    }

    public TipoMedicinaTrabalho getTipoMedicinaTrabalho() {
        return tipoMedicinaTrabalho;
    }

    public void setTipoMedicinaTrabalho(TipoMedicinaTrabalho tipoMedicinaTrabalho) {
        this.tipoMedicinaTrabalho = tipoMedicinaTrabalho;
    }

    public String getAgentesAgressores() {
        return agentesAgressores;
    }

    public void setAgentesAgressores(String agentesAgressores) {
        this.agentesAgressores = agentesAgressores;
    }

    public List<ExameComplementar> getExamesComplementares() {
        return examesComplementares;
    }

    public void setExamesComplementares(List<ExameComplementar> examesComplementares) {
        this.examesComplementares = examesComplementares;
    }
}
