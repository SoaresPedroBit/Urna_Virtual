package urna.urna.ServiceTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import urna.urna.Entity.*;
import urna.urna.Repository.VotoRepository;
import urna.urna.Service.VotoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class VotoServiceTest {
    @Autowired
    VotoService votoService;
    @MockBean
    VotoRepository votoRepository;

    //Se o prefeito foi informado
    @Test
    void cenario1(){
        Eleitor eleitor = new Eleitor();
        eleitor.setId(1L);
        eleitor.setCpf("109.696.239-56");
        eleitor.setStatusEleitor(StatusEleitor.APTO);
        Voto voto = new Voto();
        Candidato prefeito = new Candidato();
        Candidato vereador = new Candidato();
        assertThrows(IllegalArgumentException.class,()->votoService.votarApto(voto));
    }
    //Se o vereador foi informado
    @Test
    void cenario2(){
        Eleitor eleitor = new Eleitor();
        eleitor.setId(1L);
        eleitor.setCpf("109.696.239-56");
        eleitor.setStatusEleitor(StatusEleitor.APTO);
        Voto voto = new Voto();
        Candidato prefeito = new Candidato();
        Candidato vereador = new Candidato();
        prefeito.setId(1L);
        voto.setPrefeito(prefeito);
        assertThrows(IllegalArgumentException.class,()->votoService.votarApto(voto));
    }
    //verificar se o prefeito é um prefeito
    @Test
    void cenario3(){
        Eleitor eleitor = new Eleitor();
        eleitor.setId(1L);
        eleitor.setCpf("109.696.239-56");
        eleitor.setStatusEleitor(StatusEleitor.APTO);
        Voto voto = new Voto();
        Candidato prefeito = new Candidato();
        Candidato vereador = new Candidato();
        prefeito.setId(1L);
        vereador.setId(2L);
        prefeito.setCargo(Cargo.Vereador);
        voto.setPrefeito(prefeito);
        voto.setVereador(vereador);
        assertThrows(IllegalArgumentException.class,()->votoService.votarApto(voto));

    }
    //vereificar se vereador é vereador
    @Test
    void cenario4(){
        Eleitor eleitor = new Eleitor();
        eleitor.setId(1L);
        eleitor.setCpf("109.696.239-56");
        eleitor.setStatusEleitor(StatusEleitor.APTO);
        Voto voto = new Voto();
        Candidato prefeito = new Candidato();
        Candidato vereador = new Candidato();
        prefeito.setId(1L);
        vereador.setId(2L);
        prefeito.setCargo(Cargo.Prefeito);
        vereador.setCargo(Cargo.Prefeito);
        voto.setPrefeito(prefeito);
        voto.setVereador(vereador);
        assertThrows(IllegalArgumentException.class,()->votoService.votarApto(voto));

    }
}
