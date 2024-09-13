package urna.urna.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urna.urna.Entity.Eleitor;
import urna.urna.Entity.StatusEleitor;
import urna.urna.Repository.EleitorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EleitorService {

    @Autowired
    EleitorRepository eleitorRepository;

    public String salvar(Eleitor eleitor){
        if(eleitor.getCpf() == null ||eleitor.getCpf().isEmpty() || eleitor.getEmail() == null || eleitor.getEmail().isEmpty() ){
            eleitor.setStatusEleitor(StatusEleitor.PENDENTE);
            this.eleitorRepository.save(eleitor);
            return "Eleitor salvo com informaçoes pendentes";
        }else{
            eleitor.setStatusEleitor(StatusEleitor.APTO);
            this.eleitorRepository.save(eleitor);
            return "Eleitor cadastrado";
        }


    }
    public String atualizar(Eleitor eleitor,Long id){
        eleitor.setId(id);
        if(eleitor.getCpf() == null ||eleitor.getCpf().isEmpty() || eleitor.getEmail() == null || eleitor.getEmail().isEmpty() ){
            eleitor.setStatusEleitor(StatusEleitor.PENDENTE);
            this.eleitorRepository.save(eleitor);
            return "Eleitor atualizado com informaçoes pendentes";
        }else{
            eleitor.setStatusEleitor(StatusEleitor.APTO);
            this.eleitorRepository.save(eleitor);
            return "Eleitor atualizado";
        }
    }
    public Eleitor buscaId(Long id){
        Optional<Eleitor> eleitor = this.eleitorRepository.findById(id);
        return eleitor.orElse(null);
    }
    public List<Eleitor> buscaTudo(){
        return eleitorRepository.findAll();

    }
    public String deletar(Long id) {
        // Busca o eleitor pelo ID
        Optional<Eleitor> optionalEleitor = eleitorRepository.findById(id);
        // Verifica se o eleitor existe
        if (optionalEleitor.isPresent()) {
            Eleitor eleitor = optionalEleitor.get();
            // Define o status como INATIVO
            eleitor.setStatusEleitor(StatusEleitor.INATIVO);
            // Salva as alterações no repositório
            eleitorRepository.save(eleitor);
            return "Eleitor marcado como inativo";
        } else {
            return "Eleitor não encontrado";
        }
    }


}
