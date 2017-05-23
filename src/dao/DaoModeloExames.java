/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.Dao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.medicinatrabalho.Modeloexames;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ronaldo
 */
public class DaoModeloExames extends Dao<Modeloexames>{
    
    public DaoModeloExames(Session session) {
        super(session);
    }
    
    public DaoModeloExames() throws Exception{
        super();
    }

    public List<Modeloexames> listar() throws Exception{
        return super.listar(Modeloexames.class); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Modeloexames getById(Long Id) throws Exception{
        return super.getById(Modeloexames.class, Id); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Modeloexames getByCargoTipoMedicinaTrabalho(Long idCargo, Long idTipoMedicinaTrabalho) throws Exception{
        try{
            Transaction transaction = getSession().beginTransaction();
            try{
              Modeloexames modelo = (Modeloexames) getSession().
                      createQuery("from Modeloexames m where m.cargo.id = :idCargo and m.tipoMedicinaTrabalho.id = :idTipoMedicinaTrabalho")
                      .setParameter("idCargo", idCargo)
                      .setParameter("idTipoMedicinaTrabalho", idTipoMedicinaTrabalho)
                      .uniqueResult();
              transaction.commit();
              return modelo;
            }catch(Exception e){
              transaction.rollback();
              throw e;
            }
        } catch (Exception ex) {
            Logger.getLogger(DaoModeloExames.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    
    
}
