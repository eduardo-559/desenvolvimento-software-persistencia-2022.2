package ufc.br.trabalho2.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ufc.br.trabalho2.entidade.Filme;

@Repository
public interface FilmeDAO extends JpaRepository<Filme, Integer> {
    // c) Listar os nomes de todos os atores de um determinado filme.
    @Query("select f from Filme f left join fetch f.atores where f.id = :id")
    Optional<Filme> findByIdWithAtores(@Param("id") int id);

    // d) Listar os títulos de filmes lançados em determinado ano.
    // Native Query
    @Query(value = "SELECT * FROM FILME f where f.ano_lancamento = :ano ", nativeQuery = true)
    List<Filme> findFilmesPorAno(@Param("ano") int ano);

    // e) Listar os títulos de filmes cujo título contém determinada string.
    // Named Query
    @Query(name = "findSubstring")
    List<Filme> filmeContemString(@Param("titulo") String titulo);

    //g) Mostrar a quantidade total de filmes cadastrados
    //Tá na interface


}
