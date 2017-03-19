/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.permissao;

import dao.Dao;
import java.util.List;
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
    
    public  Usuario login(String usuario, String senha){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Object obj = null;
        try{
            obj = 
            session.createQuery("from Usuario u where u.login = :login and u.senha = :senha")
                  .setParameter("login", usuario)
                  .setParameter("senha", senha)
                  .uniqueResult();
            
            transaction.commit();
        }catch(Exception e){
          transaction.rollback();
          throw e;
        }finally{
            return (Usuario) obj;
        }
    }
    
    public List<Usuario> listar() {
        return super.listar(Usuario.class); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario getById(Long Id) {
        return super.getById(Usuario.class, Id); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
