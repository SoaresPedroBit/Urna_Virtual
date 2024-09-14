package urna.urna.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urna.urna.Entity.Candidato;
import urna.urna.Service.CandidatoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {
    @Autowired
    CandidatoService candidatoService;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@RequestBody Candidato candidato){
        try {
            String menssagem = this.candidatoService.salvar(candidato);
            return new ResponseEntity<>(menssagem, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Deu ruim o salvamento"+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@RequestBody Candidato candidato, @PathVariable Long id){
        try {
            String menssagem = this.candidatoService.atualizar(candidato,id);
            return new ResponseEntity<>(menssagem,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Não foi possivel atualizar"+e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscaId/{id}")
    public ResponseEntity<Optional<Candidato>> buscarId(@PathVariable Long id) {
        try {
            Optional<Candidato> candidato = this.candidatoService.buscaId(id);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(candidato);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/buscaTudo")
    public ResponseEntity<List<Candidato>> buscaTudo(){
        try {
            List<Candidato> candidatoes = this.candidatoService.buscaTudo();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(candidatoes);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        try {
            String menssagem = this.candidatoService.deletar(id);
            return new ResponseEntity<>(menssagem, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>("Erro ao deletar"+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
