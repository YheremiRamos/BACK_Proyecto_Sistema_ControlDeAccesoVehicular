package cibertec.edu.pe.sistema_vehicular.service;

import java.util.List;
import java.util.Optional;

import cibertec.edu.pe.sistema_vehicular.entity.Parqueos;


public interface ParqueosService {

    // Listar todos los parqueos
    List<Parqueos> listarTodos();

    // Buscar parqueo por ID
    Optional<Parqueos> findById(int idParqueos);
    Parqueos buscarPorId(int idParqueos);

    // Registrar un nuevo parqueo
    Parqueos registrarParqueo(Parqueos parqueos);

    // Actualizar un parqueo existente
    Parqueos actualizarParqueo(Parqueos parqueos);

    // Eliminar un parqueo por ID
    void eliminarParqueo(int idParqueos);

    // Listar parqueos por estado
    List<Parqueos> listarPorEstado(String estado_espacios);

    // Listar parqueos por tipo
    List<Parqueos> listarPorTipo(String tipoParqueo);

    /*
    List<Parqueos> listarParqueosPorUbicacionYEstado(int idUbicacion, String estado);*/

    /*--------------------FILTRACION COMPLEJA------------------*/
    List<Parqueos> listaCompleja(int idTipoVehiculo, int idEstadoEspacio, int idTipoParqueo);
    
    /*VALIDACION LIMITES
    int countByUbicacion_IdUbicacion(int idUbicacion);

	*/

    
}