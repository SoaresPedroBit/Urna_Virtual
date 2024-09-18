package urna.urna.ServiceTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import urna.urna.Entity.Eleitor;
import urna.urna.Entity.StatusEleitor;
import urna.urna.Repository.EleitorRepository;
import urna.urna.Service.EleitorService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EleitorServiceTest {
    @Autowired
    EleitorService eleitorService;
    @MockBean
    EleitorRepository eleitorRepository;

    @Test
    void cenario1(){

            Long idInexistente = 999L;
            // Mockando o repositório para retornar Optional.empty() quando findById é chamado com o ID inexistente
            when(eleitorRepository.findById(idInexistente)).thenReturn(Optional.empty());
            String resultado = eleitorService.deletar(idInexistente);
            // Verificando se a mensagem de "Candidato não encontrado" é retornada
            assertEquals("Eleitor não encontrado", resultado);
    }
    @Test
    void cenario5() {
        Eleitor eleitor = new Eleitor();
        eleitor.setId(1L);
        eleitor.setStatusEleitor(StatusEleitor.VOTOU);

        when(eleitorRepository.findById(1L)).thenReturn(Optional.of(eleitor));
        String resultado = eleitorService.deletar(1L);
        assertEquals("Usuário já votou,Não foi possível inativálo", resultado);
    }
    @Test
    void cenario6() {

        Eleitor eleitor = new Eleitor();
        eleitor.setId(1L);
        eleitor.setCpf("123.456.789-01");
        eleitor.setEmail("eleitor@teste.com");
        eleitor.setStatusEleitor(StatusEleitor.INATIVO);
         String resultado = eleitorService.atualizar(eleitor, 1L);
         assertEquals("Eleitor atualizado mas continua inativo",resultado);

    }
    @Test
    void cenario7() {

        Eleitor eleitor = new Eleitor();
        eleitor.setId(1L);
        eleitor.setCpf(null);
        eleitor.setEmail("eleitor@teste.com");
        eleitor.setStatusEleitor(StatusEleitor.INATIVO);
        String resultado = eleitorService.atualizar(eleitor, 1L);
        assertEquals("Eleitor atualizado com informaçoes pendentes",resultado);

    }
    @Test
    void cenario8() {

        Eleitor eleitor = new Eleitor();
        eleitor.setId(1L);
        eleitor.setCpf(null);
        eleitor.setEmail("eleitor@teste.com");
        eleitor.setStatusEleitor(StatusEleitor.APTO);
        String resultado = eleitorService.atualizar(eleitor, 1L);
        assertEquals("Eleitor atualizado com informaçoes pendentes",resultado);
    }
    @Test
    void cenario9() {

        Eleitor eleitor = new Eleitor();
        eleitor.setCpf(null);
        eleitor.setEmail("eleitor@teste.com");
        eleitor.setStatusEleitor(StatusEleitor.APTO);
        String resultado = eleitorService.salvar(eleitor);
        assertEquals("Eleitor salvo com informaçoes pendentes",resultado);
    }




}
