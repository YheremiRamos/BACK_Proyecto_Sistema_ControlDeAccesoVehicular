package cibertec.edu.pe.sistema_vehicular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cibertec.edu.pe.sistema_vehicular.entity.Cliente;
import cibertec.edu.pe.sistema_vehicular.entity.EspacioParqueo;
import cibertec.edu.pe.sistema_vehicular.service.ClienteService;
import cibertec.edu.pe.sistema_vehicular.util.AppSettings;

@RestController
@RequestMapping("/url/cliente")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ClienteController {

	  @Autowired
	    private ClienteService clienteService;
	  
		@GetMapping
		@ResponseBody
		public ResponseEntity<List<Cliente>> listaTodosClientes(){
			List<Cliente> lista = clienteService.ListarTodosClientes();
			return ResponseEntity.ok(lista);
		}
		
}
