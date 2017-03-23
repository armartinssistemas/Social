/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.recolhimento;

import dao.Dao;
import java.util.List;
import model.recolhimento.RecolhimentoDiario;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ronaldo
 */
public class DaoRecolhimentoDiario extends Dao<RecolhimentoDiario>{
public DaoRecolhimentoDiario(Session session) {
        super(session);
    }
    
    public DaoRecolhimentoDiario() throws Exception{
        super();
    }

    public RecolhimentoDiario getById(Long Id) {
        return super.getById(RecolhimentoDiario.class, Id); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<RecolhimentoDiario> listar() {
        return super.listar(RecolhimentoDiario.class); //To change body of generated methods, choose Tools | Templates.
    }    
    
    public RecolhimentoDiario getByIDFornecedor(Long Id){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        RecolhimentoDiario recolhimentoDiario = null;
        try{
            
            recolhimentoDiario = (RecolhimentoDiario) session.createQuery("from RecolhimentoDiario r where r.IDFornecedor = :IDFornecedor")
                    .setParameter("IDFornecedor", Id)
                    .uniqueResult();
            transaction.commit();
        }catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }finally{
            return recolhimentoDiario;
        }
    }
}
