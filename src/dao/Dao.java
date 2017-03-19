package dao;

import util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.lang.Class;
import java.util.Collections;
import javax.persistence.Entity;
import model.Ambulatorio;

/**
 *
 * @author ronaldo
 */
public abstract class Dao<T> {
    
    private static Session session;
    
    public Dao(Session session){
        this.session = session;
    }
    
    public Dao(){
        this.session = getSession();
    }    
    
    public void delete(Class<T> obj){
        Transaction transaction = session.beginTransaction();
        try{
          session.delete(obj);
          transaction.commit();
        }catch(Exception e){
          transaction.rollback();
          throw e;
        }         
    }

    public void insert(Class<T> obj){
        Transaction transaction = session.beginTransaction();
        try{
          session.persist(obj);
          transaction.commit();
        }catch(Exception e){
          transaction.rollback();
          throw e;
        }
    }

    public void update(Class<T> obj){
         Transaction transaction = session.beginTransaction();
         try{
           session.merge(obj);
           transaction.commit();
         }catch(Exception e){
           transaction.rollback();
           throw e;
        }        
    }
    
    public T getById(Class<T> clazz, Long Id){
        Transaction transaction = session.beginTransaction();
        T obj = (T) session.get(clazz, Id);
        transaction.commit();
        return obj;
    }
    
    public List<T> listar(Class<T> clazz){
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
    
    public static Session getSession(){
        if (session != null && session.isConnected())
            return session;
        else
            return HibernateUtil.getSessionFactory().openSession();
    }
    
    public static void closeSession(){
        session.close();
    }
}
