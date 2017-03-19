/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.medicinatrabalho;
import dao.Dao;
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
}
