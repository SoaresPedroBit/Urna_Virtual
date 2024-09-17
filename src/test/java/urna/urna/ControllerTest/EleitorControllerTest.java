package urna.urna.ControllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import urna.urna.Controller.EleitorController;
import urna.urna.Entity.Eleitor;
import urna.urna.Entity.Cargo;
import urna.urna.Entity.StatusEleitor;
import urna.urna.Repository.EleitorRepository;
import urna.urna.Service.EleitorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static urna.urna.Entity.Cargo.*;

@SpringBootTest
public class EleitorControllerTest {

    @Autowired
    EleitorController eleitorController;
    @MockBean
    EleitorRepository eleitorRepository;

    @BeforeEach
    void setup() {
        Eleitor eleitor1 = new Eleitor("João Silva", "123.456.789-00","Engenheiro","(11) 91234-5678", "(11) 3456-7890", "joao.silva@email.com", StatusEleitor.APTO);
        Eleitor eleitor2 = new Eleitor("Maria Souza", "987.654.321-00", "Médica", "(21) 99876-5432", "(21) 2345-6789", "maria.souza@email.com", StatusEleitor.APTO);

        List<Eleitor> lista = new ArrayList<>();
        lista.add(eleitor1);
        lista.add(eleitor2);


        when(eleitorRepository.findAll()).thenReturn(lista);
        // Mock para retornar eleitor por ID
        Mockito.when(eleitorRepository.findById(1L)).thenReturn(Optional.of(eleitor1));
        // Mock retornar salvar/atualizar
        when(eleitorRepository.save(eleitor1)).thenReturn(eleitor1);

    }
    @Test
    void cenario1(){
        ResponseEntity<Eleitor> retorno = this.eleitorController.buscarId(1L);

        assertEquals(HttpStatus.ACCEPTED,retorno.getStatusCode() );
    }
    @Test
    void cenario2(){
        Eleitor eleitor1 = new Eleitor("João Silva", "123.456.789-00","Engenheiro","(11) 91234-5678", "(11) 3456-7890", "joao.silva@email.com", StatusEleitor.APTO);
        ResponseEntity<String> retorno = this.eleitorController.salvar(eleitor1);
        assertEquals(HttpStatus.CREATED,retorno.getStatusCode());
    }
    @Test
    void cenario3(){
        Eleitor eleitor = new Eleitor("Maria Souza", "987.654.321-00", "Médica", "(21) 99876-5432", "(21) 2345-6789", "maria.souza@email.com", StatusEleitor.APTO);
        ResponseEntity<String> retorno = this.eleitorController.atualizar(eleitor,1L);
        assertEquals(HttpStatus.CREATED,retorno.getStatusCode());
    }
    @Test
    void cenario4(){
        ResponseEntity<String> retorno = this.eleitorController.deletar(1L);
        assertEquals(HttpStatus.OK,retorno.getStatusCode());

    }
    @Test
    void cenario5(){
        ResponseEntity<List<Eleitor>> retorno = this.eleitorController.buscaTudo();
        assertEquals(HttpStatus.ACCEPTED,retorno.getStatusCode());
    }



}
