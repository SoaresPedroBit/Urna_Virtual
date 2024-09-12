package urna.urna.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import urna.urna.Entity.Eleitor;

public interface EleitorRepository extends JpaRepository<Eleitor, Long> {
    Eleitor findByCpf(String cpf);
}
