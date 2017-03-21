package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cidade", catalog = "TEOR")
public class Cidade {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String nome;
   
   @OneToMany(mappedBy = "cidade")
   private List<FornecedorCana> fornecedores = new ArrayList<FornecedorCana>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<FornecedorCana> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<FornecedorCana> fornecedores) {
        this.fornecedores = fornecedores;
    }
}
