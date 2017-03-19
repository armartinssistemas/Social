/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
}
