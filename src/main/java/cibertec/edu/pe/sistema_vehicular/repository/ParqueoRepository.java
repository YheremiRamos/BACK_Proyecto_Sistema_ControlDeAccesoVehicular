package cibertec.edu.pe.sistema_vehicular.repository;

import cibertec.edu.pe.sistema_vehicular.entity.Parqueo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParqueoRepository extends JpaRepository<Parqueo, Integer> {
    @Query("SELECT p FROM Parqueo p WHERE p.tipoVehiculoPermitido= :tipoVehiculoPermitido")
    Optional<Parqueo> findByTipoVehiculoPermitido(@Param("tipoVehiculoPermitido") String tipoVehiculoPermitido);

}
