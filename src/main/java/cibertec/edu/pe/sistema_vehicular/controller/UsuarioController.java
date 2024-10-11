package cibertec.edu.pe.sistema_vehicular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cibertec.edu.pe.sistema_vehicular.entity.Usuario;
import cibertec.edu.pe.sistema_vehicular.service.UsuarioService;
import cibertec.edu.pe.sistema_vehicular.util.AppSettings;

@RestController
@RequestMapping("/url/usuario")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
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
	
}
