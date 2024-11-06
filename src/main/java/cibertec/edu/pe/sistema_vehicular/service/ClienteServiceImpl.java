package cibertec.edu.pe.sistema_vehicular.service;

import cibertec.edu.pe.sistema_vehicular.entity.Cliente;
import cibertec.edu.pe.sistema_vehicular.entity.TipoUsuario;
import cibertec.edu.pe.sistema_vehicular.repository.ClienteRepository;
import cibertec.edu.pe.sistema_vehicular.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {


    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes(String identificador) {
        return repository.listarClientes();
    }

    public Optional<Cliente> findById(int id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);  // Guardamos el cliente en la base de datos
    }


}
