/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.medicinatrabalho;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ronaldo
 */
@Entity
@Table(name = "tipo_medicina_trabalho",catalog = "TEOR")
public class TipoMedicinaTrabalho implements Comparable<TipoMedicinaTrabalho>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 50)
    private String descricao;
    
    @OneToMany(mappedBy = "tipoMedicinaTrabalho")
    private List<GuiaMedicinaTrabalho> guiasMedicinaTrabalho;

    @OneToMany(mappedBy = "tipoMedicinaTrabalho")
    private List<Modeloexames> modeloexames;
    
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

    public List<GuiaMedicinaTrabalho> getGuiasMedicinaTrabalho() {
        return guiasMedicinaTrabalho;
    }

    public void setGuiasMedicinaTrabalho(List<GuiaMedicinaTrabalho> guiasMedicinaTrabalho) {
        this.guiasMedicinaTrabalho = guiasMedicinaTrabalho;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    @Override
    public int compareTo(TipoMedicinaTrabalho o) {
        return this.descricao.compareTo(o.getDescricao());
    }

    public List<Modeloexames> getModeloexames() {
        return modeloexames;
    }

    public void setModeloexames(List<Modeloexames> modeloexames) {
        this.modeloexames = modeloexames;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoMedicinaTrabalho other = (TipoMedicinaTrabalho) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
