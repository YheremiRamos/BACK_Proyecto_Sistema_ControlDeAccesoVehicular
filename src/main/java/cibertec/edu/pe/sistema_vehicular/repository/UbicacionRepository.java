package cibertec.edu.pe.sistema_vehicular.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import cibertec.edu.pe.sistema_vehicular.entity.Ubicacion;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {

    // Listar todas las ubicaciones ordenadas por ID
    List<Ubicacion> findByOrderByIdUbicacion();

    // Buscar ubicaciones por ID - son iguales
    Optional<Ubicacion> findById(int idUbicacion);
    List<Ubicacion> findByIdUbicacion(int idUbicacion);

    // Buscar ubicaciones por nombre
    List<Ubicacion> findByNombreUbicacionContaining(String nombreUbicacion);

   

    /*--------------------SEM 12 - VALIDACIONES DE REGISTRO Y ACTUZALIZACION------------------*/
  //BUSCA UBICAICONES CON NOMBRES IGUALES (PARA VALIDACION)
  	@Query("select u from Ubicacion u where u.nombreUbicacion = ?1")
  	List<Ubicacion> listaPorNombreIgualRegistra(String nombreUbicacion);
  	
  //actualizar //validacion de repetción 
  	@Query("select u from Ubicacion u where u.nombreUbicacion = ?1 and u.idUbicacion != ?2") 
  	List<Ubicacion> listaPorNombreIgualActualiza(String nombreUbicacion, int idUbicacion);
  	//--
 // Buscar ubicaciones por nombre exacto
    Optional<Ubicacion> findByNombreUbicacion(String nombreUbicacion);

    
    
    // Buscar ubicaciones cuyo nombre contenga una cadena específica
    @Query("SELECT u FROM Ubicacion u WHERE LOWER(u.nombreUbicacion) LIKE LOWER(CONCAT('%', :nombreUbicacion, '%'))")
    List<Ubicacion> buscarPorNombreUbicacion(@Param("nombreUbicacion") String nombreUbicacion);


  	 /*--------------------VALIDACIONES/FILTRACIONES OPCIONALES------------------*/
  	 /* Filtrar ubicaciones por tipo de ubicación (buscando por la entidad)
    @Query("SELECT u FROM Ubicacion u WHERE u.tipoUbicacion.idUbicacion = :idTipoUbicacion")
    List<Ubicacion> findByTipoUbicacionId(@Param("idTipoUbicacion") Integer idTipoUbicacion);*/

    // Filtrar ubicaciones por estado de espacio (comentado temporalmente)
    /*@Query("SELECT u FROM Ubicacion u WHERE u.estadoEspacios.idEstadoEspacios = :estadoId")
    List<Ubicacion> findByEstadoEspacios(@Param("estadoId") Integer estadoId);*/

    // Contar ubicaciones por tipo de ubicación (comentado temporalmente)
    /*@Query("SELECT COUNT(u) FROM Ubicacion u WHERE u.tipoUbicacion.idTipoUbicacion = :tipoId")
    long countByTipoUbicacion(@Param("tipoId") Integer tipoId);*/
}