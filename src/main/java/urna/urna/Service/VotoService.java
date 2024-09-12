package urna.urna.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urna.urna.Entity.*;
import urna.urna.Repository.CandidatoRepository;
import urna.urna.Repository.EleitorRepository;
import urna.urna.Repository.VotoRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class VotoService {

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    CandidatoRepository candidatoRepository;
    @Autowired
    EleitorRepository eleitorRepository;
    @Autowired
    Apuracao apuracao;



    public String votar(String cpf,Integer prefeito, Integer vereador){
        Eleitor eleitor = eleitorRepository.findByCpf(cpf);
        if (eleitor != null) {
            // Obtém a data e hora atuais formatadas
            LocalDateTime dataHora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataHoraFormatada = dataHora.format(formato);

            // Cria um novo voto
            Voto voto = new Voto();
            voto.setDataHora(dataHoraFormatada);,

            // Busca os candidatos pelo número
            Candidato candidatoPrefeito = candidatoRepository.findByNumeroCandidato(prefeito);
            Candidato candidatoVereador = candidatoRepository.findByNumeroCandidato(vereador);

            // Verifica se os candidatos foram encontrados
            if (candidatoPrefeito == null || candidatoVereador == null) {
                return "Erro: Candidato não encontrado.";
            }

            // Verifica se os cargos são corretos
            if (candidatoVereador.getCargo() == Cargo.Vereador && candidatoPrefeito.getCargo() == Cargo.Prefeito) {
                // Atribui os candidatos ao voto
                voto.setPrefeito(candidatoPrefeito);
                voto.setVereador(candidatoVereador);

                // Salvar o voto
                eleitor.setStatusEleitor(StatusEleitor.VOTOU);
                int votosTotais =  apuracao.getTotalVotos();
                apuracao.setTotalVotos(votosTotais + 1);


                votoRepository.save(voto);

                return "Voto registrado com sucesso!";
            } else {
                return "Erro: Cargo incorreto para os candidatos.";
            }
        }else{
            return "cpf invaliido";
        }
        }

}
