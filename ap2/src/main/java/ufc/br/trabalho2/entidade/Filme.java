package ufc.br.trabalho2.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NamedQueries({
    @NamedQuery(name = "findSubstring", query = "from Filme where LOWER(titulo)LIKE CONCAT('%',LOWER(:titulo),'%')")
})

@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Filme {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter private Integer id;
    @Getter @Setter private String titulo;
    @Getter @Setter private int anoLancamento;

    @ManyToMany
    @ToString.Exclude
    @Getter @Setter
    private List<Ator> atores;
}
