package urna.urna.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import urna.urna.Entity.Candidato;
import urna.urna.Entity.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long>{

    // Conta os votos pelo ID do candidato
    @Query("SELECT COUNT(v) FROM Voto v WHERE v.prefeito.id = :candidatoId OR v.vereador.id = :candidatoId")
    long countVotosByCandidatoId(@Param("candidatoId") Long candidatoId);

}
