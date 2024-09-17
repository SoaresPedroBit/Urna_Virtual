package urna.urna.ControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import urna.urna.Controller.VotoController;
import urna.urna.Entity.*;
import urna.urna.Repository.VotoRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VotoControllerTest {

    @Autowired
    VotoController votoController;
    @MockBean
    VotoRepository votoRepository;

    @Test
    void cenario1(){
        Eleitor eleitor1 = new Eleitor("João Silva", "123.456.789-00","Engenheiro","(11) 91234-5678", "(11) 3456-7890", "joao.silva@email.com", StatusEleitor.APTO);
        Candidato candidato1 = new Candidato(1L, "Fabiano", "795.395.320-00", 22, Cargo.Prefeito, StatusCandidato.ATIVO);
        Candidato candidato2 = new Candidato(2L,"Pedrao", "538.175.500-730", 13205, Cargo.Vereador, StatusCandidato.ATIVO);

        Voto voto = new Voto(1L,null,candidato1,candidato2,null);
        ResponseEntity<String> retorno = this.votoController.votar("123.456.789-00",voto);
        assertEquals(HttpStatus.OK,retorno.getStatusCode());

    }
    @Test
    void cenario2(){
        List<Candidato> listaVereador = new ArrayList<>();
        List<Candidato> listaPrefeito = new ArrayList<>();
        Apuracao apuracao = new Apuracao();
        Candidato candidato1 = new Candidato(1L, "Fabiano", "795.395.320-00", 22, Cargo.Prefeito, StatusCandidato.ATIVO);
        Candidato candidato2 = new Candidato(2L,"Pedrao", "538.175.500-730", 13205, Cargo.Vereador, StatusCandidato.ATIVO);
        listaVereador.add(candidato2);
        listaPrefeito.add(candidato1);
        apuracao.setTotalVotos(20);
        apuracao.setPrefeitos(listaPrefeito);
        apuracao.setVereadores(listaVereador);
        ResponseEntity<Apuracao> retorno = this.votoController.apuracao();
        assertEquals(HttpStatus.CREATED,retorno.getStatusCode());


    }
}
