package ufc.br.trabalho2.entidade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Ator {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter private Integer id;
    @Getter @Setter private String nome;
    @ToString.Exclude
    @Getter @Setter private LocalDate dataNascimento;

    @ToString.Include
    String dataNascimento(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");        
        return this.dataNascimento.format(formatter);
    }
    

    @ManyToMany(mappedBy = "atores")
    @ToString.Exclude
    @Getter @Setter
    private List<Filme> filmes;
}
