/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Titular;
import org.hibernate.Session;

/**
 *
 * @author ronaldo
 */
public class DaoTitular extends Dao<Titular>{
    
    public DaoTitular(Session session) {
        super(session);
    }
 
    public DaoTitular(){
        super();
    }
    
    public Titular getById(Long Id) {
        return super.getById(Titular.class, Id); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Titular> listar() {
        return super.listar(Titular.class); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
