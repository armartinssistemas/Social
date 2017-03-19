/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Dependente;
import org.hibernate.Session;

/**
 *
 * @author ronaldo
 */
public class DaoDependente extends Dao<Dependente>{
    
    public DaoDependente(Session session) {
        super(session);
    }
    
    public DaoDependente(){
        super();
    }
    
}
