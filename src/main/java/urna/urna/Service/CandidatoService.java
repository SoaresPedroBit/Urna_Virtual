package urna.urna.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urna.urna.Entity.Candidato;
import urna.urna.Entity.Cargo;
import urna.urna.Entity.StatusCandidato;
import urna.urna.Repository.CandidatoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatoService {

    @Autowired
    CandidatoRepository candidatoRepository;

    public String salvar(Candidato candidato){
        candidato.setStatusCandidato(StatusCandidato.ATIVO);
        this.candidatoRepository.save(candidato);
        return "Candidato cadastrado";
    }
    public String atualizar(Candidato candidato, Long id){
        candidato.setId(id);
        this.candidatoRepository.save(candidato);
        return "Canditado atualizado";
    }
    public String deletar(Long id){

        Optional<Candidato> candidato1 = this.candidatoRepository.findById(id);
        if (candidato1.isPresent()){
            Candidato candidato = candidato1.get();
            candidato.setStatusCandidato(StatusCandidato.INATIVO);
            candidatoRepository.save(candidato);
            return "Canditado foi inativado";
        }else{
            return "Canditado não encontrado";
        }
    }
    public Optional<Candidato> buscaId(Long id){
        return this.candidatoRepository.findById(id);
    }
    public List<Candidato> buscaTudo(){
        return candidatoRepository.findAllByStatusCandidato(StatusCandidato.ATIVO);
    }

    public List<Candidato> buscaTudoPrefeito(){
        return  candidatoRepository.findAllByCargoAndStatusCandidato(Cargo.Prefeito,StatusCandidato.ATIVO);
    }

    public List<Candidato> buscaTudoVereador(){
        return candidatoRepository.findAllByCargoAndStatusCandidato(Cargo.Vereador,StatusCandidato.ATIVO);
    }


}
