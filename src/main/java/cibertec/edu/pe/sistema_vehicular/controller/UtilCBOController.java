package cibertec.edu.pe.sistema_vehicular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cibertec.edu.pe.sistema_vehicular.entity.EstadoEspacios;
import cibertec.edu.pe.sistema_vehicular.entity.TipoParqueo;
import cibertec.edu.pe.sistema_vehicular.entity.TipoUbicacion;
import cibertec.edu.pe.sistema_vehicular.entity.TipoVehiculo;
import cibertec.edu.pe.sistema_vehicular.entity.Ubicacion;
import cibertec.edu.pe.sistema_vehicular.service.EstadoEspaciosService;
import cibertec.edu.pe.sistema_vehicular.service.TipoParqueoService;
import cibertec.edu.pe.sistema_vehicular.service.TipoUbicacionService;
import cibertec.edu.pe.sistema_vehicular.service.TipoVehiculoService;
import cibertec.edu.pe.sistema_vehicular.service.UbicacionService;
import cibertec.edu.pe.sistema_vehicular.util.AppSettings;

@RestController
@RequestMapping("/url/utilCBO")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)

public class UtilCBOController {
	
	@Autowired
	private UbicacionService ubicacionService;
	@Autowired
	private TipoParqueoService tipoParqueoService;
	
	@Autowired
	private TipoVehiculoService tipoVehiculoService;
	
	@Autowired
	private EstadoEspaciosService estadoEspaciosService;
	
	@Autowired
	private TipoUbicacionService tipoUbicacionService;
	
	//-----------------------------------------
	@GetMapping("/listaUbicacion")
	@ResponseBody
	public List<Ubicacion> listaUbicacion() {
		return ubicacionService.listarTodos();
	}
	
	@GetMapping("/listaTipoParqueo")
	@ResponseBody
	public List<TipoParqueo> listaTipoParqueo() {
		return tipoParqueoService.listaTodos();
	}
	
	@GetMapping("/listaTipoVehiculo")
	@ResponseBody
	public List<TipoVehiculo> listaTipoVehiculo() {
		return tipoVehiculoService.listaTodos();
	}
	
	@GetMapping("/listaEstadoEspacios")
	@ResponseBody
	public List<EstadoEspacios> listaEstadoEspacios() {
		return estadoEspaciosService.listaTodos();
	}
	
	@GetMapping("/listaTipoUbicacion")
	@ResponseBody
	public List<TipoUbicacion> listaTipoUbicacion() {
		return tipoUbicacionService.listaTodos();
	}
	
}
