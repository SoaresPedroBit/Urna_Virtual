package urna.urna.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urna.urna.Entity.Voto;
import urna.urna.Service.VotoService;

@RestController
@RequestMapping("/voto")
public class VotoController {
    @Autowired
    VotoService votoService;

    @PostMapping("/votar/{cpf}/{prefeito}/{vereador}")
    public ResponseEntity<String>votar(@PathVariable String cpf, @PathVariable Integer prefeito, @PathVariable Integer vereador){
        try {
            String menssagem = this.votoService.votar(cpf, prefeito, vereador);
            return new ResponseEntity<>(menssagem, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Erro na votação",HttpStatus.BAD_REQUEST);
        }

    }
}
