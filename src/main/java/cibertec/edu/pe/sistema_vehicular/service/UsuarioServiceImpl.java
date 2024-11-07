package cibertec.edu.pe.sistema_vehicular.service;

import cibertec.edu.pe.sistema_vehicular.entity.Opcion;
import cibertec.edu.pe.sistema_vehicular.entity.Rol;
import cibertec.edu.pe.sistema_vehicular.entity.Usuario;
import cibertec.edu.pe.sistema_vehicular.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Opcion> traerEnlacesDeUsuario(int idUsuario) {
        return repository.traerEnlacesDeUsuario(idUsuario);
    }

    @Override
    public List<Rol> traerRolesDeUsuario(int idUsuario) {
        return repository.traerRolesDeUsuario(idUsuario);
    }

    @Override
    public Usuario buscaPorLogin(String login) {
        return repository.findByLogin(login);
    }

	@Override
	public List<Usuario> buscaUsuarioPorDni(String dni) {
		return repository.traerUsuarioPorDni(dni);
	}


    public Optional<Usuario> findById(int id){
        return repository.findById(id);
    }
}
