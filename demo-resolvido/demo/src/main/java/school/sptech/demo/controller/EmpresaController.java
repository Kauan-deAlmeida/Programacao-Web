package school.sptech.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import school.sptech.demo.entity.Empresa;
import school.sptech.demo.service.EmpresaService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Empresa>> listar(){
        List<Empresa> empresas = empresaService.buscarTodos();

        if(empresas.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(empresaService.buscarPorId(id));
    }
}
