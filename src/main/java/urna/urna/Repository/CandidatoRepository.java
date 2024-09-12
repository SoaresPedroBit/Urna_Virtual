package urna.urna.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import urna.urna.Entity.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato,Long> {

    Candidato findByNumeroCandidato(Integer numeroCandidato);
}
