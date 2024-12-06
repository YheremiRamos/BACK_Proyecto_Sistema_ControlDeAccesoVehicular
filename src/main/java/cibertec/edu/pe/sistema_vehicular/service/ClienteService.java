package cibertec.edu.pe.sistema_vehicular.service;

import cibertec.edu.pe.sistema_vehicular.entity.Cliente;


import java.util.List;
import java.util.Optional;

public interface ClienteService {

    public abstract List<Cliente> listarClientes(String identificador);

    //Búsqueda por Identificador
    public abstract List<Cliente> buscaClientePorDni(String dni);

    List<Cliente> ListarTodosClientes();

    public abstract List<Cliente> listaCompleja(String nombres, String apellidos, String identificador);

    public abstract Optional<Cliente> buscaClienteId(int idCliente);
}
