package urna.urna.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urna.urna.Entity.Eleitor;
import urna.urna.Service.EleitorService;

import java.util.List;

@RestController
@RequestMapping("/eleitor")
public class EleitorController {
    @Autowired
    EleitorService eleitorService;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@RequestBody Eleitor eleitor){
        try {
            String menssagem = this.eleitorService.salvar(eleitor);
            return new ResponseEntity<>(menssagem, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Deu ruim o salvamento"+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@RequestBody Eleitor eleitor, @PathVariable Long id){
        try {
            String menssagem = this.eleitorService.atualizar(eleitor,id);
            return new ResponseEntity<>(menssagem,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Não foi possivel atualizar"+e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscaId/{id}")
    public ResponseEntity<Eleitor> buscarId(@PathVariable Long id) {
        try {
            Eleitor eleitor = this.eleitorService.buscaId(id);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(eleitor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/buscaTudo")
    public ResponseEntity<List<Eleitor>> buscaTudo(){
        try {
            List<Eleitor> eleitores = this.eleitorService.buscaTudo();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(eleitores);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        try {
            String menssagem = this.eleitorService.deletar(id);
            return new ResponseEntity<>(menssagem, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>("Erro ao deletar"+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
