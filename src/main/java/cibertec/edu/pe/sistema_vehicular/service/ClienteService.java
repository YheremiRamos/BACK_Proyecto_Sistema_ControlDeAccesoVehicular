package cibertec.edu.pe.sistema_vehicular.service;

import cibertec.edu.pe.sistema_vehicular.entity.Cliente;
import cibertec.edu.pe.sistema_vehicular.entity.TipoUsuario;
import cibertec.edu.pe.sistema_vehicular.entity.Usuario;

import java.util.List;

public interface ClienteService {

    public abstract List<Cliente> listarClientes(String identificador);
}
