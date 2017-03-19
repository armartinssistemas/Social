/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.medicinatrabalho;
import dao.Dao;
import model.medicinatrabalho.TipoMedicinaTrabalho;
import org.hibernate.Session;

/**
 *
 * @author ronaldo
 */
public class DaoTipoMedicinaTrabalho extends Dao<TipoMedicinaTrabalho>{
    
    public DaoTipoMedicinaTrabalho(Session session) {
        super(session);
    }
    
    public DaoTipoMedicinaTrabalho(){
        super();
    }
}
