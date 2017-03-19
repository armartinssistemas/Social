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
import model.permissao.Usuario;

/**
 *
 * @author ronaldo
 */
@Entity
@Table(name = "guia_medicina_trabalho", catalog = "TEOR")
public class GuiaMedicinaTrabalho implements Comparable<GuiaMedicinaTrabalho>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ambulatorio")
    private Ambulatorio ambulatorio;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_exame")
    private TipoMedicinaTrabalho tipoMedicinaTrabalho;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente")
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fornecedor_cana")    
    private FornecedorCana fornecedorCana;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_cad") 
    private Usuario usuarioCadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    @Override
    public int compareTo(GuiaMedicinaTrabalho o) {
        return this.id.compareTo(o.getId());
    }
}
