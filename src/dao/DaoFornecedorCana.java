/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.FornecedorCana;
import org.hibernate.Session;

/**
 *
 * @author ronaldo
 */
public class DaoFornecedorCana extends Dao<FornecedorCana>{
    
    public DaoFornecedorCana(Session session) {
        super(session);
    }
    
    public DaoFornecedorCana(){
        super();
    }

    public FornecedorCana getById(Long Id) {
        return super.getById(FornecedorCana.class, Id); //To change body of generated methods, choose Tools | Templates.
    }

    public List<FornecedorCana> listar() {
        return super.listar(FornecedorCana.class); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
