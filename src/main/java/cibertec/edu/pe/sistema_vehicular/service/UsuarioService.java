package cibertec.edu.pe.sistema_vehicular.service;

import cibertec.edu.pe.sistema_vehicular.entity.Opcion;
import cibertec.edu.pe.sistema_vehicular.entity.Rol;
import cibertec.edu.pe.sistema_vehicular.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    public abstract List<Opcion> traerEnlacesDeUsuario(int idUsuario);

    public abstract List<Rol> traerRolesDeUsuario(int idUsuario);

    public abstract Usuario buscaPorLogin(String login);
    
    //Búsqueda por DNI
    public abstract List<Usuario> buscaUsuarioPorDni(String dni);
    // Método para buscar usuario por nombre de usuario

}
