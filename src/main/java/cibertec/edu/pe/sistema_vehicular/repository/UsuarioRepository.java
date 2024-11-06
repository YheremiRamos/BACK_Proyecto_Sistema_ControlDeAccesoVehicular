package cibertec.edu.pe.sistema_vehicular.repository;


import cibertec.edu.pe.sistema_vehicular.entity.Opcion;
import cibertec.edu.pe.sistema_vehicular.entity.Rol;
import cibertec.edu.pe.sistema_vehicular.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("Select x from Usuario x where x.login = :#{#usu.login} and x.password = :#{#usu.password}")
    public abstract Usuario login(@Param(value = "usu") Usuario bean);

    @Query("Select p from Opcion p, RolHasOpcion pr, Rol r, UsuarioHasRol u where  p.idOpcion = pr.opcion.idOpcion and pr.rol.idRol = r.idRol and r.idRol = u.rol.idRol and u.usuario.idUsuario = :var_idUsuario")
    public abstract List<Opcion> traerEnlacesDeUsuario(@Param("var_idUsuario") int idUsuario);

    @Query("Select r from Rol r, UsuarioHasRol u where r.idRol = u.rol.idRol and u.usuario.idUsuario = :var_idUsuario")
    public abstract List<Rol> traerRolesDeUsuario(@Param("var_idUsuario")int idUsuario);


    public abstract Usuario findByLogin(String login);

    //Búsqueda por DNI
    @Query("select u from Usuario u where u.dni like ?1")
	public abstract List<Usuario> traerUsuarioPorDni(String dni);
}
