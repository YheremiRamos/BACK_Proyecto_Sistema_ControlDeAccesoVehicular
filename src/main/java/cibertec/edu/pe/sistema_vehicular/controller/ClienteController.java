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

import cibertec.edu.pe.sistema_vehicular.entity.Cliente;
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
	    @GetMapping("/consultaClientePorParametros") //http://localhost:8090/url/consultaAutor/consultaAutorPorParametros?nombres=&apellidos&fecNacDesde=1900-01-01&fecNacHasta=2020-01-01&telefono=&celular=&orcid=&estado=1&idPais=-1&idGrado=-1

		 public ResponseEntity<?> consultaClientePorParametros(
		            @RequestParam(name = "nombres", required = true, defaultValue = "") String nombres,
		            @RequestParam(name = "apellidos", required = true, defaultValue = "") String apellidos,
		            @RequestParam(name = "identificador", required = true, defaultValue = "") String identificador)
		           {

		        List<Cliente> lstSalida = clienteService.listaCompleja(
		        		"%"+ nombres+"%", "%"+apellidos+"%", "%" +identificador + "%");

		        return ResponseEntity.ok(lstSalida);
		    }

		  
		  
}
