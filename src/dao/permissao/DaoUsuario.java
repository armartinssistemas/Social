/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.permissao;

import dao.Dao;
import model.permissao.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author ronaldo
 */
public class DaoUsuario extends Dao<Usuario>{

    public DaoUsuario(Session session) {
        super(session);
    }

    public DaoUsuario() {
        super();
    }
    
    public  boolean login(String usuario, String senha){
        boolean retorno = false;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Object obj = 
            session.createQuery("from Usuario u where u.login = :login and u.senha = :senha")
                  .setParameter("login", usuario)
                  .setParameter("senha", senha)
                  .uniqueResult();
            retorno = obj != null;
            transaction.commit();
        }catch(Exception e){
          transaction.rollback();
          throw e;
        }finally{
            return retorno;
        }
    }
}
