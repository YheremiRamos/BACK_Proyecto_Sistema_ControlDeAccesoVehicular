package cibertec.edu.pe.sistema_vehicular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cibertec.edu.pe.sistema_vehicular.entity.EstadoEspacios;
import java.util.List;

public interface EstadoEspaciosRepository extends JpaRepository<EstadoEspacios, Integer> {
    public abstract List<EstadoEspacios> findByOrderByNombreEstadoEspaciosAsc();
}
