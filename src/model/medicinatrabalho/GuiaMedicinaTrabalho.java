/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.medicinatrabalho;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import model.Ambulatorio;
import model.FornecedorCana;
import model.Paciente;

/**
 *
 * @author ronaldo
 */
@Entity
@Table(name = "guia_medicina_trabalho", catalog = "TEOR")
public class GuiaMedicinaTrabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "ambulatorio", referencedColumnName = "id")
    private Ambulatorio ambulatorio;
    
    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "tipo_exame", referencedColumnName = "id")
    private TipoMedicinaTrabalho tipoMedicinaTrabalho;
    
    private Date data_cad;
    
    private Date hora_cad;
    
    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "paciente", referencedColumnName = "id")
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "fornecedor_cana", referencedColumnName = "IDFornecedor")    
    private FornecedorCana fornecedorCana;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData_cad() {
        return data_cad;
    }

    public void setData_cad(Date data_cad) {
        this.data_cad = data_cad;
    }

    public Date getHora_cad() {
        return hora_cad;
    }

    public void setHora_cad(Date hora_cad) {
        this.hora_cad = hora_cad;
    }

    public Ambulatorio getAmbulatorio() {
        return ambulatorio;
    }

    public void setAmbulatorio(Ambulatorio ambulatorio) {
        this.ambulatorio = ambulatorio;
    }

    public TipoMedicinaTrabalho getTipoMedicinaTrabalho() {
        return tipoMedicinaTrabalho;
    }

    public void setTipoMedicinaTrabalho(TipoMedicinaTrabalho tipoMedicinaTrabalho) {
        this.tipoMedicinaTrabalho = tipoMedicinaTrabalho;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public FornecedorCana getFornecedorCana() {
        return fornecedorCana;
    }

    public void setFornecedorCana(FornecedorCana fornecedorCana) {
        this.fornecedorCana = fornecedorCana;
    }
}
