package cibertec.edu.pe.sistema_vehicular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cibertec.edu.pe.sistema_vehicular.entity.Cliente;
import cibertec.edu.pe.sistema_vehicular.entity.EspacioParqueo;
import cibertec.edu.pe.sistema_vehicular.service.EspacioParqueoService;
import cibertec.edu.pe.sistema_vehicular.util.AppSettings;

@RestController
@RequestMapping("/url/espacioParqueo")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class EspacioParqueoController {

	@Autowired
	private EspacioParqueoService espacioService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<EspacioParqueo>> listaTodosEspacios(){
		List<EspacioParqueo> lista = espacioService.listarEspaciosParqueoTodos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/listarEspaciosIdParqueo/{idParqueo}")
	@ResponseBody
	public ResponseEntity<List<EspacioParqueo>> listaEspaciosIdParqueo(@PathVariable int idParqueo){
		List<EspacioParqueo> lista = espacioService.listarEspaciosIdParqueo(idParqueo);
		if(lista.isEmpty()) {
			return ResponseEntity.notFound().build(); //404
		} else {
			return ResponseEntity.ok(lista); //200 y la lista
		}
	}
	
	@PostMapping
	public EspacioParqueo crearEspacioParqueo(@RequestBody EspacioParqueo espacioParqueo) {
		return espacioService.crearEspacioParqueo(espacioParqueo);
	}
	
}
