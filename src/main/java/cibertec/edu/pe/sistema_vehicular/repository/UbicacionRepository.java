package cibertec.edu.pe.sistema_vehicular.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import cibertec.edu.pe.sistema_vehicular.entity.Ubicacion;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {

    // Listar todas las ubicaciones ordenadas por ID
    List<Ubicacion> findByOrderByIdUbicacion();

    // Buscar ubicaciones por ID
    List<Ubicacion> findByIdUbicacion(int idUbicacion);

    // Buscar ubicaciones por nombre
    List<Ubicacion> findByNombreUbicacionContaining(String nombreUbicacion);

    /* Filtrar ubicaciones por tipo de ubicación (buscando por la entidad)
    @Query("SELECT u FROM Ubicacion u WHERE u.tipoUbicacion.idUbicacion = :idTipoUbicacion")
    List<Ubicacion> findByTipoUbicacionId(@Param("idTipoUbicacion") Integer idTipoUbicacion);*/

    // Filtrar ubicaciones por estado de espacio (comentado temporalmente)
    /*@Query("SELECT u FROM Ubicacion u WHERE u.estadoEspacios.idEstadoEspacios = :estadoId")
    List<Ubicacion> findByEstadoEspacios(@Param("estadoId") Integer estadoId);*/

    // Contar ubicaciones por tipo de ubicación (comentado temporalmente)
    /*@Query("SELECT COUNT(u) FROM Ubicacion u WHERE u.tipoUbicacion.idTipoUbicacion = :tipoId")
    long countByTipoUbicacion(@Param("tipoId") Integer tipoId);*/

    // Filtrar ubicaciones por límite de parqueos (comentado temporalmente)
    /*@Query("SELECT u FROM Ubicacion u WHERE u.limiteParqueos = :limite")
    List<Ubicacion> findByLimiteParqueos(@Param("limite") Integer limite);*/
}
