/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.Dao.getSession;
import java.util.ArrayList;
import java.util.List;
import model.Paciente;
import model.medicinatrabalho.GuiaMedicinaTrabalho;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ronaldo
 */
public class DaoPaciente extends Dao<Paciente>{
    
    public DaoPaciente(Session session) {
        super(session);
    }

    public DaoPaciente() throws Exception{
        super();
    }

    
    public Paciente getById(Long Id) {
        return super.getById(Paciente.class, Id);
    }

    public List<Paciente> listar() {
        return super.listar(Paciente.class); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Paciente> listarPorNome(String nome){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        List<Paciente> lista = new ArrayList<>();
        try{
            lista = 
                    session.createQuery("from Paciente p where p.nome like :nome")
                    .setParameter("nome", nome+"%")
                    .list();
            transaction.commit();
        }catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }finally{
            return lista;
        }        
    }
    
    public List<Paciente> listarPorCPF(String cpf){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        List<Paciente> lista = new ArrayList<>();
        try{
            lista = 
                    session.createQuery("from Paciente p where p.cpf like :cpf")
                    .setParameter("cpf", cpf+"%")
                    .list();
            transaction.commit();
        }catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }finally{
            return lista;
        }        
    }

    public List<Paciente> listarPorRG(String rg){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        List<Paciente> lista = new ArrayList<>();
        try{
            lista = 
                    session.createQuery("from Paciente p where p.rg like :rg")
                    .setParameter("nome", rg+"%")
                    .list();
            transaction.commit();
        }catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }finally{
            return lista;
        }        
    }    
}
