package urna.urna.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import urna.urna.Entity.Candidato;
import urna.urna.Entity.StatusCandidato;

import java.util.List;

public interface CandidatoRepository extends JpaRepository<Candidato,Long> {

    List<Candidato> findAllByStatusCandidato(StatusCandidato statusCandidato);
    Candidato findByNumeroCandidato(Integer numeroCandidato);
}
