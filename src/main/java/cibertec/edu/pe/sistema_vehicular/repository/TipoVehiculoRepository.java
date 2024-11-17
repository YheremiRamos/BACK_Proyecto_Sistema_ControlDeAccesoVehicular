package cibertec.edu.pe.sistema_vehicular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cibertec.edu.pe.sistema_vehicular.entity.TipoVehiculo;
import java.util.List;

public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo, Integer> {
    public abstract List<TipoVehiculo> findByOrderByNombreTipoVehiculoAsc();
}
