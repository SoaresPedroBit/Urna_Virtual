package urna.urna.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import urna.urna.Entity.Eleitor;
import urna.urna.Entity.StatusEleitor;

import java.util.List;

public interface EleitorRepository extends JpaRepository<Eleitor, Long> {
    Eleitor findByCpf(String cpf);
    List<Eleitor> findAllByStatusEleitor( StatusEleitor StatusEleitor);
}
