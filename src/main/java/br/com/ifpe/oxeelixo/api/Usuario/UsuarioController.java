package br.com.ifpe.oxeelixo.api.Usuario;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxeelixo.modelo.usuario.Usuario;
import br.com.ifpe.oxeelixo.modelo.usuario.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Serviço responsável por salvar um usuário no sistema.")
    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody @Valid UsuarioRequest request) {

        Usuario usuario = usuarioService.save(request.build());
        return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Serviço responsável por listar todos os usuários do sistema.")
    @GetMapping
    public List<Usuario> findAll() {

        return usuarioService.findAll();
    }

    
      @ApiOperation(value =
      "Serviço responsável por obter um usuário referente ao Id passado na URL.")
      
      @ApiResponses(value = {
      
      @ApiResponse(code = 200, message = "Retorna  o usuário."),
      
      @ApiResponse(code = 401, message = "Acesso não autorizado."),
      
      @ApiResponse(code = 403, message =
      "Você não tem permissão para acessar este recurso."),
      
      @ApiResponse(code = 404, message =
      "Não foi encontrado um registro para o Id informado."),
      
      @ApiResponse(code = 500, message = "Foi gerado um erro no servidor."),
      })
     

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Long id) {

        return usuarioService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") Long id, @RequestBody UsuarioRequest request) {

        usuarioService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }

}
