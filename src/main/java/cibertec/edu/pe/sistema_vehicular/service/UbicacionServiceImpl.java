package cibertec.edu.pe.sistema_vehicular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.sistema_vehicular.entity.Ubicacion;
import cibertec.edu.pe.sistema_vehicular.repository.UbicacionRepository;
@Service
public class UbicacionServiceImpl implements UbicacionService {

	@Autowired
    private UbicacionRepository ubicacionRepository;
	
	@Override
	public List<Ubicacion> listarTodos() {
		  return ubicacionRepository.findByOrderByIdUbicacion();
	}

	@Override
	public Ubicacion buscarPorId(int idUbicacion) {
		return ubicacionRepository.findByIdUbicacion(idUbicacion).stream().findFirst().orElse(null);
	}

	@Override
	public Ubicacion registrarUbicacion(Ubicacion ubicacion) {
		 return ubicacionRepository.save(ubicacion);
	}

	@Override
	public Ubicacion actualizarUbicacion(Ubicacion ubicacion) {
		 return ubicacionRepository.save(ubicacion);
	}

	@Override
	public void eliminarUbicacion(int idUbicacion) {
		 ubicacionRepository.deleteById(idUbicacion);
		
	}

	/*@Override
	public List<Ubicacion> listarPorTipoUbicacion(int idTipoUbicacion) {
		 return ubicacionRepository.findByTipoUbicacion(idTipoUbicacion);
	}*/

	
}
