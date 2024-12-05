package cibertec.edu.pe.sistema_vehicular.controller;

import java.util.List;

import cibertec.edu.pe.sistema_vehicular.entity.Cliente;

import cibertec.edu.pe.sistema_vehicular.entity.TipoUsuario;
import cibertec.edu.pe.sistema_vehicular.service.ClienteService;
import cibertec.edu.pe.sistema_vehicular.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cibertec.edu.pe.sistema_vehicular.entity.Usuario;
import cibertec.edu.pe.sistema_vehicular.service.UsuarioService;
import cibertec.edu.pe.sistema_vehicular.util.AppSettings;

@RestController
@RequestMapping("/url/usuario")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TipoUsuarioService tipoUsuarioService;
	
	/*
	 * @GetMapping
	@ResponseBody
	public ResponseEntity<List<Usuario>> usuarioBuscado(){
		List<Usuario> lista = usuarioService.buscaUsuarioPorDniLike();
		return ResponseEntity.ok(lista);
	}*/
	
	@GetMapping("/buscarUsuarioPorDni")
    @ResponseBody
    public ResponseEntity<?> consultaUsuarioDNI(@RequestParam(name = "dni", required = true, defaultValue = "") String dni) {

        List<Usuario> lstSalida = usuarioService.buscaUsuarioPorDni(dni);
        return ResponseEntity.ok(lstSalida);
    }

    @GetMapping("/buscarClientePorDni")
    @ResponseBody
    public ResponseEntity<?> consultaCliente(@RequestParam(name = "identificador", required = true, defaultValue = "") String identificador) {

        List<Cliente> lstSalida = clienteService.buscaClientePorDni(identificador);
        return ResponseEntity.ok(lstSalida);
    }

    @GetMapping("/listarTipoUsuario")
	@ResponseBody
    public List<TipoUsuario> listarTipoUsuario() {return tipoUsuarioService.listarTipoUsuario();}




}
