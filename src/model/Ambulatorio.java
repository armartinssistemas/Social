/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import model.medicinatrabalho.GuiaMedicinaTrabalho;

/**
 *
 * @author ronaldo
 */
@Entity
@Table(name = "ambulatorio", catalog = "TEOR")
public class Ambulatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 50)
    private String descricao;
    
    @Column(length = 40)
    private String apelido;

    @OneToMany(mappedBy = "ambulatorio")
    private List<GuiaMedicinaTrabalho> guiasMedicinaTrabalho;
    
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

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public List<GuiaMedicinaTrabalho> getGuiasMedicinaTrabalho() {
        return guiasMedicinaTrabalho;
    }

    public void setGuiasMedicinaTrabalho(List<GuiaMedicinaTrabalho> guiasMedicinaTrabalho) {
        this.guiasMedicinaTrabalho = guiasMedicinaTrabalho;
    }
}
