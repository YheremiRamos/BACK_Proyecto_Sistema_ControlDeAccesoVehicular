package cibertec.edu.pe.sistema_vehicular.service;

import cibertec.edu.pe.sistema_vehicular.entity.TipoUsuario;
import cibertec.edu.pe.sistema_vehicular.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService{

    @Autowired
    private TipoUsuarioRepository repository;

    @Override
    public List<TipoUsuario> listarTipoUsuario() {
        return repository.listarTipoUsuario();

    }

}
