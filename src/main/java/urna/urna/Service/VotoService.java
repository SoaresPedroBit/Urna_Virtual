package urna.urna.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urna.urna.Entity.*;
import urna.urna.Repository.CandidatoRepository;
import urna.urna.Repository.EleitorRepository;
import urna.urna.Repository.VotoRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class VotoService {

    @Autowired
    VotoRepository votoRepository;
    @Autowired
    CandidatoRepository candidatoRepository;
    @Autowired
    EleitorRepository eleitorRepository;
    @Autowired
    CandidatoService candidatoService;




    public String votar(String cpf,Voto voto){
        Eleitor eleitor = eleitorRepository.findByCpf(cpf);
        if (eleitor != null) {

            if(eleitor.getStatusEleitor() == StatusEleitor.APTO) {

                votarApto(voto);

            }else if (eleitor.getStatusEleitor() == StatusEleitor.PENDENTE){

                eleitor.setStatusEleitor(StatusEleitor.BLOQUEADO);
                eleitorRepository.save(eleitor);

                return "Eleitor estava pendente para votar e foi bloqueado";

            }else if (eleitor.getStatusEleitor() == StatusEleitor.BLOQUEADO){
                return "O eleitor esta bloqueado";

            }else if(eleitor.getStatusEleitor() == StatusEleitor.VOTOU){
                return "eleitor ja votou";
            }else {
                return "O eleitor esa inativo impossibilitado de votar";
            }

        }else {
            return "cpf invaliido";

        }
        return "";
        }

        public Apuracao realizarApuracao(){

        Apuracao apuracao= new Apuracao();

        List<Candidato> listaPrefeito = candidatoService.buscaTudoPrefeito();
        List<Candidato> listaVereador = candidatoService.buscaTudoVereador();


            // Percorre os prefeitos e calcula o total de votos para cada um
            for (Candidato prefeito : listaPrefeito) {
                long totalVotos = votoRepository.countVotosByCandidatoId(prefeito.getId());
                prefeito.setVotosApurados(totalVotos); // Método setter para armazenar os votos no candidato
            }

            // Percorre os vereadores e calcula o total de votos para cada um
            for (Candidato vereador : listaVereador) {
                long totalVotos = votoRepository.countVotosByCandidatoId(vereador.getId());
                vereador.setVotosApurados(totalVotos); // Método setter para armazenar os votos no candidato
            }
            // Ordena os prefeitos pelo total de votos (ordem decrescente)
            Collections.sort(listaPrefeito, (p1, p2) -> Long.compare(p2.getVotosApurados(), p1.getVotosApurados()));

            // Ordena os vereadores pelo total de votos (ordem decrescente)
            Collections.sort(listaVereador, (v1, v2) -> Long.compare(v2.getVotosApurados(), v1.getVotosApurados()));

            apuracao.setPrefeitos(listaPrefeito);
            apuracao.setVereadores(listaVereador);

            return apuracao;
        }


        public String votarApto(Voto voto) {
            // Obtém a data e hora atuais formatadas
            LocalDateTime dataHora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataHoraFormatada = dataHora.format(formato);
            voto.setDataHora(dataHoraFormatada);

            //Verificando se o candidatos foram encontrados
            if (voto.getPrefeito() == null) {
                throw new IllegalArgumentException("Candidato a prefeito não foi informado.");
            }
            if (voto.getVereador() == null) {
                throw new IllegalArgumentException("Candidato a vereador não foi informado.");
            }
            // Verifica se o candidato a prefeito é de fato um prefeito
            if (voto.getPrefeito().getCargo() != Cargo.Prefeito) {
                throw new IllegalArgumentException("O candidato escolhido para prefeito é um candidato a vereador. Refaça a requisição!");
            }
            // Verifica se o candidato a vereador é de fato um vereador
            if (voto.getVereador().getCargo() != Cargo.Vereador) {
                throw new IllegalArgumentException("O candidato escolhido para vereador é um candidato a prefeito. Refaça a requisição!");
            }
            String hash = UUID.randomUUID().toString();
            voto.setComprovante(hash);
            votoRepository.save(voto);
            return "Voto realizado com sucesso";


        }
}
