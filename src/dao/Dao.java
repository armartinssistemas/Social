package dao;

import util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.lang.Class;
import java.net.UnknownHostException;
import java.util.Collections;
import javax.persistence.Entity;
import javax.swing.JOptionPane;
import model.Ambulatorio;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author ronaldo
 */
public abstract class Dao<T> {
    
    private static Session session;
    
    public Dao(Session session){
        this.session = session;
    }
    
    public Dao() throws Exception{
        this.session = getSession();
    }    
    
    public void delete(T obj) throws Exception{
        Transaction transaction = session.beginTransaction();
        try{
          session.delete(obj);
          transaction.commit();
        }catch(Exception e){
          transaction.rollback();
          throw e;
        }         
    }

    public void insert(T obj) throws Exception{
        Transaction transaction = session.beginTransaction();
        try{
          session.persist(obj);
          transaction.commit();
        }catch(Exception e){
          transaction.rollback();
          throw e;
        }
    }

    public void update(T obj) throws Exception{
         Transaction transaction = session.beginTransaction();
         try{
           session.merge(obj);
           transaction.commit();
         }catch(Exception e){
           transaction.rollback();
           throw e;
        }        
    }
    
    public T getById(Class<T> clazz, Long Id) throws Exception{
        Transaction transaction = session.beginTransaction();
        T obj = (T) session.get(clazz, Id);
        transaction.commit();
        return obj;
    }
    
    public List<T> listar(Class<T> clazz) throws Exception{
        Transaction transaction = session.beginTransaction();
        try{
          List<T> lista = session.createCriteria(clazz).list();
          transaction.commit();
          return lista;
        }catch(Exception e){
          transaction.rollback();
          throw e;
        }
    }
    
    public static Session getSession() throws Exception{
            if (session != null && session.isDirty())
                return session;
            else{
                session = HibernateUtil.getSessionFactory().openSession();
                return session;
            }
    }
    
    public static void closeSession(){
        session.close();
    }
}
