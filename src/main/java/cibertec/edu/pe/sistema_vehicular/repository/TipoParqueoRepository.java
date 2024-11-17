package cibertec.edu.pe.sistema_vehicular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cibertec.edu.pe.sistema_vehicular.entity.TipoParqueo;
import java.util.List;

public interface TipoParqueoRepository extends JpaRepository<TipoParqueo, Integer> {
    public abstract List<TipoParqueo> findByOrderByNombreTipoParqueoAsc();
}
