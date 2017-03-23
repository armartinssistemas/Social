/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.medicinatrabalho;
import dao.Dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.medicinatrabalho.GuiaMedicinaTrabalho;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ronaldo
 */
public class DaoGuiMedicinaTrabalho extends Dao<GuiaMedicinaTrabalho>{
    
    public DaoGuiMedicinaTrabalho(Session session) {
        super(session);
    }

    public DaoGuiMedicinaTrabalho() throws Exception{
        super();
    }

    
    public List<GuiaMedicinaTrabalho> listar() {
        return super.listar(GuiaMedicinaTrabalho.class); 
    }
    
    public GuiaMedicinaTrabalho getById(Long Id) {
        return super.getById(GuiaMedicinaTrabalho.class, Id); 
    }
    
    public List<GuiaMedicinaTrabalho> listarPorPacienteNome(String nome){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        List<GuiaMedicinaTrabalho> lista = new ArrayList<>();
        try{
            lista = 
                    session.createQuery("from GuiaMedicinaTrabalho g where g.paciente.nome like :nome")
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
    
    public List<GuiaMedicinaTrabalho> listarPorData(Date data){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        List<GuiaMedicinaTrabalho> lista = null;
        try{
            lista = 
                    session.createQuery("from GuiaMedicinaTrabalho g where g.data = :data")
                    .setDate("data", data)
                    .list();
            transaction.commit();
            return lista;
        }catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }finally{
            return lista;
        }
    }
    
    public List<GuiaMedicinaTrabalho> listarPorNumero(Long numero){
                        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        List<GuiaMedicinaTrabalho> lista = null;
        try{
            lista = 
                    session.createQuery("from GuiaMedicinaTrabalho g where g.id = :numero")
                    .setParameter("numero", numero)
                    .list();
            transaction.commit();
            return lista;
        }catch(Exception ex){
            transaction.rollback();
            ex.printStackTrace();
        }finally{
            return lista;
        }
    }
}
