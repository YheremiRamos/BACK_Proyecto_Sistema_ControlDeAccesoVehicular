package cibertec.edu.pe.sistema_vehicular.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.sistema_vehicular.entity.Parqueos;

import cibertec.edu.pe.sistema_vehicular.repository.ParqueosRepository;

@Service
public class ParqueosServiceImpl implements ParqueosService {
	
	@Autowired
	private ParqueosRepository repository;

	@Override
	public List<Parqueos> listarTodos() {
		return repository.findByOrderByIdParqueos();
	}

	// busqueda por id
	@Override
	public Optional<Parqueos> findById(int idParqueos) {
		return repository.findById(idParqueos); // Devuelve el Optional directamente
	}

	@Override
	public Parqueos buscarPorId(int idParqueos) {
		return repository.findById(idParqueos).orElse(null);
	}

//---
	@Override
	public Parqueos registrarParqueo(Parqueos parqueos) {
		return repository.save(parqueos);
	}
	
	/*
	@Override
	public Parqueos registrarParqueo(Parqueos parqueo) {
	    // Verificar si el número de parqueos actuales en la ubicación ha alcanzado el límite
	    int countParqueos = countByUbicacion_IdUbicacion(parqueo.getUbicacion().getIdUbicacion());

	    // Si el número de parqueos actuales es mayor o igual al límite de parqueos de la ubicación, lanzar una excepción
	    if (countParqueos >= parqueo.getUbicacion().getLimiteParqueos()) {
	        throw new LímiteDeParqueosAlcanzadoException("Límite de parqueos alcanzado en esta ubicación");
	    }

	    // Si no se ha alcanzado el límite, guardar el parqueo
	    return repository.save(parqueo);
	}
	public class LímiteDeParqueosAlcanzadoException extends RuntimeException {
	    public LímiteDeParqueosAlcanzadoException(String mensaje) {
	        super(mensaje);
	    }
	}
*/

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

		// estado como id int
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
/*validacion limites
	 @Override
	    public int countByUbicacion_IdUbicacion(int idUbicacion) {
	        long count = repository.countByUbicacion_IdUbicacion(idUbicacion); // Devuelve long
	        System.out.println("Cantidad de parqueos en la ubicación " + idUbicacion + ": " + count);
	        return (int) count; // Convertir long a int
	    }*/

}