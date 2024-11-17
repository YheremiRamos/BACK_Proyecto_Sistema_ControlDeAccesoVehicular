package cibertec.edu.pe.sistema_vehicular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.sistema_vehicular.entity.Parqueos;

import cibertec.edu.pe.sistema_vehicular.repository.ParqueosRepository;
@Service
public class ParqueosServiceImpl implements ParqueosService{
	 @Autowired
	    private ParqueosRepository repository;
	 
	@Override
	public List<Parqueos> listarTodos() {
		  return repository.findByOrderByIdParqueo();
	}

	@Override
	public Parqueos buscarPorId(int idParqueo) {
		return repository.findById(idParqueo).orElse(null);
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
	public void eliminarParqueo(int idParqueo) {
		 repository.deleteById(idParqueo);
	}

	@Override
	public List<Parqueos> listarPorEstado(String estado_espacios) {
		
        //estado como id int
        Integer idEstadoEspacios = Integer.valueOf(estado_espacios); // Si el estado es String, conviértelo a Integer
        return repository.findByEstado(idEstadoEspacios);
	}

	@Override
	public List<Parqueos> listarPorTipo(String tipo_parqueo) {
		 Integer idTipoParqueo = Integer.valueOf(tipo_parqueo); // Convertir tipo a Integer si es necesario
	        return repository.findByTipo(idTipoParqueo);
	}
	
	/*--------------------LISTADO Y AGRUPACION DE PARQUEOS EN UBICACIONES------------------*/

	

	/*@Override
	public List<Parqueos> listarParqueosPorUbicacionYEstado(int idUbicacion, String estado) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
