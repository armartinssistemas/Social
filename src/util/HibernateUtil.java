package util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.Ambulatorio;
import model.Dependente;
import model.FornecedorCana;
import model.Paciente;
import model.Titular;
import model.medicinatrabalho.GuiaMedicinaTrabalho;
import model.medicinatrabalho.TipoMedicinaTrabalho;
import model.permissao.Usuario;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author ronaldo
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    
    /*static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }*/
    
    public static SessionFactory getSessionFactory() {
        //return sessionFactory;
        if(sessionFactory == null){
            try{
                AnnotationConfiguration ac = new AnnotationConfiguration();
                ac.addAnnotatedClass(Ambulatorio.class);
                ac.addAnnotatedClass(Dependente.class);
                ac.addAnnotatedClass(FornecedorCana.class);
                ac.addAnnotatedClass(Paciente.class);
                ac.addAnnotatedClass(Titular.class);
                ac.addAnnotatedClass(GuiaMedicinaTrabalho.class);
                ac.addAnnotatedClass(TipoMedicinaTrabalho.class);
                ac.addAnnotatedClass(Usuario.class);
                
                sessionFactory = ac.configure().buildSessionFactory();
            }catch(Throwable ex){
                ex.printStackTrace();
                throw new ExceptionInInitializerError(ex);
            }
            return sessionFactory;
        }else{
            return sessionFactory;
        }
        
    }
}
