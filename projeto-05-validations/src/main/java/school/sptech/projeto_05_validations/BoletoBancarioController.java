package school.sptech.projeto_05_validations;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/boleto-bancario")
public class BoletoBancarioController {

    @Autowired
    private BoletoBancarioRepository repository;

    @PostMapping
    public ResponseEntity<BoletoBancario> cadastrar(@RequestBody @Valid BoletoBancario boletoBancario){
        return ResponseEntity.status(200).body(repository.save(boletoBancario));
    }


//    @PostMapping
//    public ResponseEntity<BoletoBancario> cadastrar(@RequestBody BoletoBancario boletoBancario){
//        if(Objects.isNull(boletoBancario.getBanco()) ||
//                boletoBancario.getBanco().isEmpty() ||
//                boletoBancario.getBanco().isBlank() ||
//                boletoBancario.getBanco().length() != 3){
//            return ResponseEntity.status(400).build();
//        }
//        if(Objects.isNull(boletoBancario.getAgencia()) ||
//                boletoBancario.getAgencia().isEmpty() ||
//                boletoBancario.getAgencia().isBlank() ||
//                boletoBancario.getAgencia().length() != 4){
//            return ResponseEntity.status(400).build();
//        }
//        if(Objects.isNull(boletoBancario.getConta()) ||
//                boletoBancario.getConta().isEmpty() ||
//                boletoBancario.getConta().isBlank() ||
//                boletoBancario.getConta().length() != 4){
//            return ResponseEntity.status(400).build();
//        }
//        if(!expressao(boletoBancario)){
//            return ResponseEntity.status(400).build();
//        }
//
//        return ResponseEntity.status(200).body(repository.save(boletoBancario));
//    }
//
//    public boolean expressao(BoletoBancario boletoBancario){
//        if(boletoBancario.getCodigoBarra().matches("\\\\d{5}\\\\.\\\\d{5} \\\\d{5}\\\\.\\\\d{6} \\\\d{5}\\\\.\\\\d{6} \\\\d \\\\d{14}")){
//            return true;
//        }
//        return false;
//    }

}
