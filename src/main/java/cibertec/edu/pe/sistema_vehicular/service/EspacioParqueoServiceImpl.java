package cibertec.edu.pe.sistema_vehicular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.sistema_vehicular.entity.EspacioParqueo;
import cibertec.edu.pe.sistema_vehicular.repository.EspacioParqueoRepository;

@Service
public class EspacioParqueoServiceImpl implements EspacioParqueoService{

	@Autowired
	EspacioParqueoRepository espacioRepository;
	
	@Override
	public List<EspacioParqueo> listarEspaciosParqueoTodos() {
		return espacioRepository.findByOrderByIdParqueo();
	}

	@Override
	public List<EspacioParqueo> listarEspaciosIdParqueo(int idParqueo) {
		return espacioRepository.findByIdParqueo(idParqueo);
	}

}
