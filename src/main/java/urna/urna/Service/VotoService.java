package urna.urna.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import urna.urna.Entity.Voto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class VotoService {

    public String salvar(Voto voto){
        LocalDateTime dataHora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Formata a data e hora
        String dataHoraFormatada = dataHora.format(formato);

        return "Salvado" ;
    }
}
