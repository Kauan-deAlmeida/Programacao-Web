package school.sptech.ex_many_to_one_dto1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.sptech.ex_many_to_one_dto1.entity.Ativo;

import java.util.List;

// TODO: TERMINAR A CLASSE
@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Integer> {

    List<Ativo> findByCarteiraInvestidorContainsIgnoreCase(String nome);
    @Query("SELECT AVG(a.valorAtual) FROM Ativo a join carteira WHERE investidor = :nomeInvestidor")
    Double media(@Param("nomeInvestidor") String nomeInvestidor);
}
