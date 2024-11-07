package cibertec.edu.pe.sistema_vehicular.service;

import java.util.List;

import cibertec.edu.pe.sistema_vehicular.entity.EspacioParqueo;

public interface EspacioParqueoService {

	List<EspacioParqueo> listarEspaciosParqueoTodos();
	List<EspacioParqueo> listarEspaciosIdParqueo(int idParq);
	EspacioParqueo crearEspacioParqueo (EspacioParqueo espacioParqueo);
}
