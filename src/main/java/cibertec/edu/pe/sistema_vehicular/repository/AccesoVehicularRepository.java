package cibertec.edu.pe.sistema_vehicular.repository;

import cibertec.edu.pe.sistema_vehicular.entity.Acceso_Vehicular;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AccesoVehicularRepository extends JpaRepository<Acceso_Vehicular, Integer> {

                //EJECUTAR QUERY NATIVO DE SQL
                @Query(value = """

            SELECT
                av.idAccesoVehicular,
                CONCAT(c.nombres, ' ', c.apellidos) AS nombreCompleto,
                tp.nombreTipoVehiculo,
                av.placaVehiculo,
                av.fechaRegistro,
                av.fechaActualizacion,
                c.numIncidencias
            FROM acceso_vehicular av
                     JOIN cliente c ON av.idCliente = c.idCliente
                     JOIN parqueos p ON av.idParqueos = p.idParqueos
                     JOIN tipo_vehiculo tp ON p.idTipoVehiculo = tp.idTipoVehiculo
""", nativeQuery = true)
                List<Object[]> listarAccesoVehicularConDetalles();


    @Query(value = "SELECT MAX(a.idAccesoVehicular) FROM Acceso_Vehicular a", nativeQuery = true)
    Integer findMaxId();


    //EJECUTAR STORE PROCEDURE
    @Transactional
    @Modifying
    @Query(value = "CALL REGISTRAR_INCIDENCIA(:idCliente)", nativeQuery = true)
    void ejecutarRegistrarIncidencia(@Param("idCliente") Integer idCliente);

    @Transactional
    @Modifying
    @Query(value = "CALL REGISTRAR_SALIDA(:idAccesoVehicular)", nativeQuery = true)
    void ejecutarRegistrarSalida(@Param("idAccesoVehicular") Integer idAccesoVehicular);

 // Método para listar los accesos filtrados por el id del parqueo
    @Query("SELECT a FROM Acceso_Vehicular a WHERE a.parqueos.idParqueos = :idParqueos")
    List<Acceso_Vehicular> findByParqueoId(@Param("idParqueos") Integer idParqueos);

}
