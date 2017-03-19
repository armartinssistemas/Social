/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.medicinatrabalho;

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
 * @author ronaldo
 */
@Entity
@Table(name = "tipo_medicina_trabalho",catalog = "TEOR")
public class TipoMedicinaTrabalho implements Comparable<TipoMedicinaTrabalho>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 50)
    private String descricao;
    
    @OneToMany(mappedBy = "tipoMedicinaTrabalho")
    private List<GuiaMedicinaTrabalho> guiasMedicinaTrabalho;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    
    
}
