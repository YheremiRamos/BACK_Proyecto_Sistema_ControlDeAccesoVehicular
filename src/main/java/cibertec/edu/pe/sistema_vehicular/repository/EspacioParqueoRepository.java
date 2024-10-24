package cibertec.edu.pe.sistema_vehicular.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cibertec.edu.pe.sistema_vehicular.entity.EspacioParqueo;

public interface EspacioParqueoRepository extends JpaRepository<EspacioParqueo, Integer> {

	public abstract List<EspacioParqueo> findByOrderByIdParqueo();


	/*
	 * @Query("select e from EspacioParqueo e where e.idParqueo = ?1 ")
	public abstract List<EspacioParqueo> findByOrderByIdParqueo(int idParqueo);*/
	public abstract List<EspacioParqueo> findByIdParqueo(int idParqueo);
}
