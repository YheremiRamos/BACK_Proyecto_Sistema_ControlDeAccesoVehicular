package cibertec.edu.pe.sistema_vehicular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.sistema_vehicular.entity.Parqueos;
import cibertec.edu.pe.sistema_vehicular.entity.Ubicacion;
import cibertec.edu.pe.sistema_vehicular.repository.ParqueosRepository;
@Service
public class ParqueosServiceImpl implements ParqueosService{
	 @Autowired
	    private ParqueosRepository repository;
	 
	@Override
	public List<Parqueos> listarTodos() {
		  return repository.findByOrderByIdParqueos();
	}

	@Override
	public Parqueos buscarPorId(int idParqueos) {
		return repository.findById(idParqueos).orElse(null);
	}

	@Override
	public Parqueos registrarParqueo(Parqueos parqueos) {
		  return repository.save(parqueos);
	}

	@Override
	public Parqueos actualizarParqueo(Parqueos parqueos) {
		  return repository.save(parqueos); // El método save crea/act
	}

	@Override
	public void eliminarParqueo(int idParqueos) {
		 repository.deleteById(idParqueos);
	}

	@Override
	public List<Parqueos> listarPorEstado(String estado_espacios) {
		
        //estado como id int
        Integer idEstadoEspacios = Integer.valueOf(estado_espacios); // Si el estado es String, conviértelo a Integer
        return repository.findByEstado(idEstadoEspacios);
	}

	@Override
	public List<Parqueos> listarPorTipo(String tipoParqueo) {
		 Integer idTipoParqueo = Integer.valueOf(tipoParqueo); // Convertir tipo a Integer si es necesario
	        return repository.findByTipo(idTipoParqueo);
	}

	
	
	/*--------------------FILTRACIO COMPLEJA------------------*/

	@Override
	public List<Parqueos> listaCompleja(int idTipoVehiculo, int idEstadoEspacio, int idTipoParqueo) {
		return repository.listaConsultaCompleja(idTipoVehiculo, idEstadoEspacio, idTipoParqueo);
	}


	



}
