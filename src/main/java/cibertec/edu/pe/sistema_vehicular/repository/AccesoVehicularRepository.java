package cibertec.edu.pe.sistema_vehicular.repository;

import cibertec.edu.pe.sistema_vehicular.entity.Acceso_Vehicular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AccesoVehicularRepository extends JpaRepository<Acceso_Vehicular, Integer> {

    @Query(value = """
    SELECT
        av.idAccesoVehicular,
        CONCAT(c.nombres, ' ', c.apellidos) AS nombreCompleto,
        p.tipoVehiculoPermitido,
        av.placaVehiculo,
        av.fechaRegistro,
        av.fechaActualizacion,
        c.numIncidencias
    FROM acceso_vehicular av
             JOIN cliente c ON av.idCliente = c.idCliente
             JOIN parqueo p ON av.idParqueo = p.idParqueo
""", nativeQuery = true)
    List<Object[]> listarAccesoVehicularConDetalles();


}
