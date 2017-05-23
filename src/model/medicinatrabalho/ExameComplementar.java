package model.medicinatrabalho;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import model.FuncaoTrabalhador;


@Entity
@Table(name = "medicinatrabalho_examecomplementar", catalog = "TEOR")
public class ExameComplementar implements Comparable<ExameComplementar> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "examesComplementares")
    private List<Modeloexames> modeloexameses = new ArrayList<>();    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public int compareTo(ExameComplementar o) {
        return this.descricao.compareTo(o.getDescricao());
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExameComplementar other = (ExameComplementar) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Modeloexames> getModeloexameses() {
        return modeloexameses;
    }

    public void setModeloexameses(List<Modeloexames> modeloexameses) {
        this.modeloexameses = modeloexameses;
    }
}
