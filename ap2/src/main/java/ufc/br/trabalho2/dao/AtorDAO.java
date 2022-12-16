package ufc.br.trabalho2.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ufc.br.trabalho2.entidade.Ator;

@Repository
public interface AtorDAO extends JpaRepository<Ator,Integer>{
    //b) Listar todos os títulos de filmes de um determinado ator.
    @Query("select a from Ator a left join fetch a.filmes where a.id = :id")
    Optional<Ator> findByIdWithFilmes(@Param("id") int id);
    
    // f) Listar os nomes de atores nascidos em determinado ano.
    @Query("select a from Ator a where a.dataNascimento between :inicio and :fim ")
    List<Ator> getPorAno(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
