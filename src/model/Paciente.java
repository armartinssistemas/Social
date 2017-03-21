/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import model.medicinatrabalho.GuiaMedicinaTrabalho;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "paciente",catalog = "TEOR")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "flag", length = 6, discriminatorType = DiscriminatorType.STRING)
public abstract class Paciente implements Comparable<Paciente>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 45)
    private String nome;
    
    @Column(length = 15)
    private String telefone;
    
    @Column(length = 15)
    private String celular;
    
    @Column(length = 20)
    private String rg;
    
    @Column(length = 20)
    private String cpf;
    
    @Column(name = "data_nascimento")
    private Date dataNacimento;

    @Column(length = 15, name = "carteira_proficional")
    private String numeroCarteiraTrabalho;
    
    @Column(length = 20, name = "seriecarteiraproficional")
    private String serieCateiraTrabalho;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "fornecedorCana")
    @NotFound(action=NotFoundAction.IGNORE)
    private FornecedorCana fornecedorCana;
    
    @OneToMany(mappedBy = "paciente")
    private List<GuiaMedicinaTrabalho> guiasMedicinaTrabalho;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "cargo")
    @NotFound(action=NotFoundAction.IGNORE)
    private FuncaoTrabalhador funcaoTrabalhador;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public FornecedorCana getFornecedorCana() {
        return fornecedorCana;
    }

    public void setFornecedorCana(FornecedorCana fornecedorCana) {
        this.fornecedorCana = fornecedorCana;
    }

    public List<GuiaMedicinaTrabalho> getGuiasMedicinaTrabalho() {
        return guiasMedicinaTrabalho;
    }

    public void setGuiasMedicinaTrabalho(List<GuiaMedicinaTrabalho> guiasMedicinaTrabalho) {
        this.guiasMedicinaTrabalho = guiasMedicinaTrabalho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNacimento() {
        return dataNacimento;
    }

    public void setDataNacimento(Date dataNacimento) {
        this.dataNacimento = dataNacimento;
    }

    public String getNumeroCarteiraTrabalho() {
        return numeroCarteiraTrabalho;
    }

    public void setNumeroCarteiraTrabalho(String numeroCarteiraTrabalho) {
        this.numeroCarteiraTrabalho = numeroCarteiraTrabalho;
    }

    public String getSerieCateiraTrabalho() {
        return serieCateiraTrabalho;
    }

    public void setSerieCateiraTrabalho(String serieCateiraTrabalho) {
        this.serieCateiraTrabalho = serieCateiraTrabalho;
    }

    @Override
    public int compareTo(Paciente o) {
        return this.nome.compareTo(o.getNome());
    }

    public FuncaoTrabalhador getFuncaoTrabalhador() {
        return funcaoTrabalhador;
    }

    public void setFuncaoTrabalhador(FuncaoTrabalhador funcaoTrabalhador) {
        this.funcaoTrabalhador = funcaoTrabalhador;
    }
    
    public int getIdade(){
        Calendar dataNasc = new GregorianCalendar();
        dataNasc.setTime(this.dataNacimento);

        // Cria um objeto calendar com a data atual
        Calendar hoje = Calendar.getInstance();
        
        // Obtém a idade baseado no ano
        int idade = hoje.get(Calendar.YEAR) - dataNasc.get(Calendar.YEAR);



        dataNasc.add(Calendar.YEAR, idade);

        if (hoje.before(dataNasc)) {
            idade--;
        }

        return idade;
    }
}
