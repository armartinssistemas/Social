package util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.medicinatrabalho.ExameComplementar;
import model.Ambulatorio;
import model.Cidade;
import model.Dependente;
import model.FornecedorCana;
import model.FuncaoTrabalhador;
import model.Paciente;
import model.Titular;
import model.medicinatrabalho.GuiaMedicinaTrabalho;
import model.medicinatrabalho.Modeloexames;
import model.medicinatrabalho.TipoMedicinaTrabalho;
import model.permissao.Usuario;
import model.recolhimento.RecolhimentoDiario;
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
    
    public static SessionFactory getSessionFactory() throws Exception{
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
                ac.addAnnotatedClass(FuncaoTrabalhador.class);
                ac.addAnnotatedClass(Cidade.class);
                ac.addAnnotatedClass(RecolhimentoDiario.class);
                ac.addAnnotatedClass(ExameComplementar.class);
                ac.addAnnotatedClass(Modeloexames.class);
                
                sessionFactory = ac.configure().buildSessionFactory();
            }catch(Exception ex){
                throw ex;
            }
            return sessionFactory;
        }else{
            return sessionFactory;
        }
        
    }
}
