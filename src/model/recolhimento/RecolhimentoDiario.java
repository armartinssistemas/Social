/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.recolhimento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ronaldo
 */
@Entity
@Table(name = "recolhimentoDiario", catalog = "TEOR")
public class RecolhimentoDiario {
    @Id
    private Long IDFornecedor;
    
    private double totalPesoDAS;
    
    private double totalR_DAS;
    
    private double totalPesoDT;
    
    private double totalR_DT;
    
    private double totalG_DAS;
    
    private double totalG_DT;

    public Long getIDFornecedor() {
        return IDFornecedor;
    }

    public void setIDFornecedor(Long IDFornecedor) {
        this.IDFornecedor = IDFornecedor;
    }

    public double getTotalPesoDAS() {
        return totalPesoDAS;
    }

    public void setTotalPesoDAS(double totalPesoDAS) {
        this.totalPesoDAS = totalPesoDAS;
    }

    public double getTotalR_DAS() {
        return totalR_DAS;
    }

    public void setTotalR_DAS(double totalR_DAS) {
        this.totalR_DAS = totalR_DAS;
    }

    public double getTotalPesoDT() {
        return totalPesoDT;
    }

    public void setTotalPesoDT(double totalPesoDT) {
        this.totalPesoDT = totalPesoDT;
    }

    public double getTotalR_DT() {
        return totalR_DT;
    }

    public void setTotalR_DT(double totalR_DT) {
        this.totalR_DT = totalR_DT;
    }

    public double getTotalG_DAS() {
        return totalG_DAS;
    }

    public void setTotalG_DAS(double totalG_DAS) {
        this.totalG_DAS = totalG_DAS;
    }

    public double getTotalG_DT() {
        return totalG_DT;
    }

    public void setTotalG_DT(double totalG_DT) {
        this.totalG_DT = totalG_DT;
    }
    
    public int getMaxUsoRecolhimento(){
        return 70;
    }
    
    public int getPorcentagemUso(){
        return (int)((getTotalG_DAS()/getTotalR_DAS())*100);
    }
    
    public String getMenssagemStatus(){
        int porcentagem = getPorcentagemUso();
       
        if (porcentagem <= 0){
            return "Atendimento não Autorizado! Informe ao CPD ou Gerência";
        }else if (porcentagem < 0.5){
            return  "Atendimento OK";
        }else if (porcentagem < 0.7){
            return "Atenção Paciênte prestes a estourar orçamento!";
        }else{
            return "Paciente estourou orçamento, informe a gerência!";
        }
    }
    
    public boolean autorizaAtendimento(){
        return true;
    }
    
}
