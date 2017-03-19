/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Paciente;
import org.hibernate.Session;

/**
 *
 * @author ronaldo
 */
public class DaoPaciente extends Dao<Paciente>{
    
    public DaoPaciente(Session session) {
        super(session);
    }

    public DaoPaciente(){
        super();
    }

    
    public Paciente getById(Long Id) {
        return super.getById(Paciente.class, Id);
    }

    public List<Paciente> listar() {
        return super.listar(Paciente.class); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
