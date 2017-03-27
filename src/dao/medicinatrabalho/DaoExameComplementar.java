/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.medicinatrabalho;
import dao.Dao;
import java.util.List;
import model.medicinatrabalho.ExameComplementar;
import org.hibernate.Session;

/**
 *
 * @author ronaldo
 */
public class DaoExameComplementar extends Dao<ExameComplementar>{
    
    public DaoExameComplementar(Session session) {
        super(session);
    }
    
    public DaoExameComplementar() throws Exception{
        super();
    }

    public ExameComplementar getById(Long Id) throws Exception{
        return super.getById(ExameComplementar.class, Id); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ExameComplementar> listar() throws Exception{
        return super.listar(ExameComplementar.class); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
