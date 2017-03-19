/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author ronaldo
 */
@Entity
@DiscriminatorValue(value = "depdep")
public class Dependente extends Paciente{
    
    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "titular", referencedColumnName = "id")
    private Titular titular;

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }
}
