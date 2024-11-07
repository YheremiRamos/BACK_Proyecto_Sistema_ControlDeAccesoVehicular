package cibertec.edu.pe.sistema_vehicular.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cibertec.edu.pe.sistema_vehicular.entity.EspacioParqueo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EspacioParqueoRepository extends JpaRepository<EspacioParqueo, Integer> {

	public abstract List<EspacioParqueo> findByOrderByIdParqueo();


	/*
	 * @Query("select e from EspacioParqueo e where e.idParqueo = ?1 ")
	public abstract List<EspacioParqueo> findByOrderByIdParqueo(int idParqueo);*/
	public abstract List<EspacioParqueo> findByIdParqueo(int idParqueo);

	@Query("SELECT e FROM EspacioParqueo e WHERE e.numeroEspacio = :numeroEspacio")
	Optional<EspacioParqueo> findByNumeroEspacio(@Param("numeroEspacio") Integer numeroEspacio);

}
