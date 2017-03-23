/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.FuncaoTrabalhador;
import org.hibernate.Session;

/**
 *
 * @author ronaldo
 */
public class DaoFuncaoTrabalhador extends Dao<FuncaoTrabalhador>{
    
    public DaoFuncaoTrabalhador(Session session) {
        super(session);
    }
 
    public DaoFuncaoTrabalhador() throws Exception{
        super();
    }
    
    public FuncaoTrabalhador getById(Long Id) throws Exception{
        return super.getById(FuncaoTrabalhador.class, Id); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<FuncaoTrabalhador> listar() throws Exception{
        return super.listar(FuncaoTrabalhador.class); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
