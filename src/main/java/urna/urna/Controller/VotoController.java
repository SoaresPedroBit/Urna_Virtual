package urna.urna.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urna.urna.Entity.Apuracao;
import urna.urna.Entity.Voto;
import urna.urna.Service.VotoService;

@RestController
@RequestMapping("/voto")
public class VotoController {
    @Autowired
    VotoService votoService;

    @PostMapping("/votar/{cpf}")
    public ResponseEntity<String>votar(@PathVariable String cpf,@RequestBody Voto voto){
        try {
            String menssagem = this.votoService.votar(cpf,voto);
            return new ResponseEntity<>(menssagem, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Erro na votação",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/apuracao")
    public ResponseEntity<Apuracao> apuracao() {
        try {
            // Chama o método de apuração
            Apuracao apuracao = this.votoService.realizarApuracao();

            // Retorna a apuração com o status CREATED
            return new ResponseEntity<>(apuracao, HttpStatus.CREATED);

        } catch (Exception e) {
            // Retorna uma mensagem de erro apropriada com o status BAD_REQUEST
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
