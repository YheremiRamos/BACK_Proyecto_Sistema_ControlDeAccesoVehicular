package cibertec.edu.pe.sistema_vehicular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cibertec.edu.pe.sistema_vehicular.entity.TipoUbicacion;
import java.util.List;

public interface TipoUbicacionRepository extends JpaRepository<TipoUbicacion, Integer> {
    public abstract List<TipoUbicacion> findByOrderByNombreTipoUbicacionAsc();
}
