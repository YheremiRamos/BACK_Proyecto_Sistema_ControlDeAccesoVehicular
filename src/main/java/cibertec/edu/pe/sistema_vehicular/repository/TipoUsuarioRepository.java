package cibertec.edu.pe.sistema_vehicular.repository;

import cibertec.edu.pe.sistema_vehicular.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {

    @Query("SELECT t FROM TipoUsuario  t")
    public abstract List<TipoUsuario> listarTipoUsuario();
}
