package cibertec.edu.pe.sistema_vehicular.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import cibertec.edu.pe.sistema_vehicular.entity.Parqueos;


public interface ParqueosRepository extends JpaRepository<Parqueos, Integer> {

    // Listar todos los parqueos ordenados por ID
    List<Parqueos> findByOrderByIdParqueo();

    // Buscar por ID de parqueo
    List<Parqueos> findByIdParqueo(int idParqueo);

    // Buscar parqueos por estado (Ej: Activo, Disponible)
    @Query("SELECT p FROM Parqueos p WHERE p.estadoEspacios.idEstadoEspacios = :idEstadoEspacios")
    List<Parqueos> findByEstado(@Param("idEstadoEspacios") Integer idEstadoEspacios);

    // Contar parqueos por estado
    @Query("SELECT COUNT(p) FROM Parqueos p WHERE p.estadoEspacios.idEstadoEspacios = :idEstadoEspacios")
    long countByEstado(@Param("idEstadoEspacios") Integer idEstadoEspacios);

    // Filtrar parqueos por ubicación específica
    @Query("SELECT p FROM Parqueos p WHERE p.ubicacion.idUbicacion = :idUbicacion")
    List<Parqueos> findByUbicacion(@Param("idUbicacion") Integer idUbicacion);

    // Buscar parqueos por tipo
    @Query("SELECT p FROM Parqueos p WHERE p.tipoParqueo.idTipoParqueo = :idTipoParqueo")
    List<Parqueos> findByTipo(@Param("idTipoParqueo") Integer idTipoParqueo);

    /* Buscar parqueos por ubicación y estado
    @Query("SELECT p FROM Parqueos p WHERE p.ubicacion.idUbicacion = :ubicacionId AND p.estado.idEstadoEspacios = :estadoId")
    List<Parqueos> findByUbicacionYEstado(@Param("ubicacionId") Integer ubicacionId, @Param("estadoId") Integer estadoId);*/

/*--------------------LISTADO Y AGRUPACION DE PARQUEOS EN UBICACIONES------------------*/
   


}
