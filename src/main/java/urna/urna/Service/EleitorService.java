package urna.urna.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urna.urna.Entity.Eleitor;
import urna.urna.Repository.EleitorRepository;

@Service
public class EleitorService {

    @Autowired
    EleitorRepository eleitorRepository;

    public String salvar(Eleitor eleitor){
        this.eleitorRepository.save(eleitor);
        return "Eleitor cadastrado";
    }
    
}
