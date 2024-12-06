package cibertec.edu.pe.sistema_vehicular.service;

import cibertec.edu.pe.sistema_vehicular.entity.Cliente;
import cibertec.edu.pe.sistema_vehicular.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes(String identificador) {
        return clienteRepository.listarClientes();
    }

    public Optional<Cliente> findById(int id) {
        return clienteRepository.findById(id);
    }


    public Optional<Cliente> findByDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    @Override
    public List<Cliente> buscaClientePorDni(String identificador) {
        return clienteRepository.traerClientePorDni(identificador);
    }

    @Override
    public List<Cliente> ListarTodosClientes() {
        return clienteRepository.findAll();

    }

    @Override
    public List<Cliente> listaCompleja(String nombres, String apellidos, String identificador) {

        return	clienteRepository.listaConsultaCompleja(nombres, apellidos, identificador);
    }

	@Override
	public Optional<Cliente> buscaClienteId(int idCliente) {
		return clienteRepository.findByIdCliente(idCliente);
	}





}
