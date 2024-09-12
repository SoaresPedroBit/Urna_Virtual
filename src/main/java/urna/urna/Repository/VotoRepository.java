package urna.urna.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import urna.urna.Entity.Candidato;
import urna.urna.Entity.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long>{


}
