package urna.urna.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import urna.urna.Entity.*;
import urna.urna.Repository.CandidatoRepository;
import urna.urna.Service.CandidatoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CandidatoServiceTest {

    @Autowired
    CandidatoService candidatoService;
    @MockBean
    CandidatoRepository candidatoRepository;

    @BeforeEach
    void setup() {
        Candidato candidato1 = new Candidato(1L, "Fabiano", "795.395.320-00", 22, Cargo.Prefeito, StatusCandidato.ATIVO);
        Candidato candidato2 = new Candidato(2L,"Pedrao", "538.175.500-730", 13205, Cargo.Vereador, StatusCandidato.ATIVO);

        List<Candidato> lista = new ArrayList<>();
        lista.add(candidato1);
        lista.add(candidato2);

        // Mock do repositório para retornar os candidatos ativos

        Mockito.when(candidatoRepository.findAllByStatusCandidato(StatusCandidato.ATIVO)).thenReturn(lista);

        // Mock para retornar candidato por ID
        Mockito.when(candidatoRepository.findById(2L)).thenReturn(Optional.of(candidato2));

        // Mock para candidatos a vereador
        List<Candidato> listaVereador = new ArrayList<>();
        listaVereador.add(candidato2);
        when(candidatoRepository.findAllByCargoAndStatusCandidato(Cargo.Vereador, StatusCandidato.ATIVO)).thenReturn(listaVereador);
        // Mock para candidatos a prefeito
        List<Candidato> listaPrefeito = new ArrayList<>();
        listaPrefeito.add(candidato1);
        when(candidatoRepository.findAllByCargoAndStatusCandidato(Cargo.Prefeito, StatusCandidato.ATIVO)).thenReturn(listaPrefeito);
    }


    @Test
    void cenario1(){
        List<Candidato> retorno = this.candidatoService.buscaTudo();
        assertEquals(2,retorno.size());
    }
    @Test
    void cenario2(){
        List<Candidato> retorno = this.candidatoService.buscaTudoVereador();
        assertEquals(1,retorno.size());
    }
    @Test
    void cenario3(){
        List<Candidato> retorno = this.candidatoService.buscaTudoPrefeito();
        assertEquals(1,retorno.size());
    }
    @Test
    void cenario4() {
        Long idInexistente = 999L;
        // Mockando o repositório para retornar Optional.empty() quando findById é chamado com o ID inexistente
        when(candidatoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        String resultado = candidatoService.deletar(idInexistente);
        // Verificando se a mensagem de "Candidato não encontrado" é retornada
        assertEquals("Canditado não encontrado", resultado);
    }


}
