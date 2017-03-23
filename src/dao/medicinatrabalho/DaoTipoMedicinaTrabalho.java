/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.medicinatrabalho;
import dao.Dao;
import java.util.List;
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
    
    public DaoTipoMedicinaTrabalho() throws Exception{
        super();
    }

    public TipoMedicinaTrabalho getById(Long Id) {
        return super.getById(TipoMedicinaTrabalho.class, Id); //To change body of generated methods, choose Tools | Templates.
    }

    public List<TipoMedicinaTrabalho> listar() {
        return super.listar(TipoMedicinaTrabalho.class); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
