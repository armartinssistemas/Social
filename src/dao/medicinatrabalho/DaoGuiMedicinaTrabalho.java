/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.medicinatrabalho;
import dao.Dao;
import java.util.List;
import model.medicinatrabalho.GuiaMedicinaTrabalho;
import org.hibernate.Session;

/**
 *
 * @author ronaldo
 */
public class DaoGuiMedicinaTrabalho extends Dao<GuiaMedicinaTrabalho>{
    
    public DaoGuiMedicinaTrabalho(Session session) {
        super(session);
    }

    public DaoGuiMedicinaTrabalho(){
        super();
    }

    
    public List<GuiaMedicinaTrabalho> listar() {
        return super.listar(GuiaMedicinaTrabalho.class); //To change body of generated methods, choose Tools | Templates.
    }
    
    public GuiaMedicinaTrabalho getById(Long Id) {
        return super.getById(GuiaMedicinaTrabalho.class, Id); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
