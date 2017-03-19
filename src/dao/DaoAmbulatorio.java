/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.Dao;
import model.Ambulatorio;
import org.hibernate.Session;

/**
 *
 * @author ronaldo
 */
public class DaoAmbulatorio extends Dao<Ambulatorio>{
    
    public DaoAmbulatorio(Session session) {
        super(session);
    }
    
    public DaoAmbulatorio(){
        super();
    }
    
}
