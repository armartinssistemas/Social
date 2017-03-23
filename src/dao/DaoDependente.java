/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
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
    
    public DaoDependente() throws Exception{
        super();
    }

    public Dependente getById(Long Id) throws Exception{
        return super.getById(Dependente.class, Id); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Dependente> listar() throws Exception{
        return super.listar(Dependente.class); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
