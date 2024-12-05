package cibertec.edu.pe.sistema_vehicular.service;

import java.util.List;
import java.util.Optional;

import cibertec.edu.pe.sistema_vehicular.entity.Ubicacion;

public interface UbicacionService {

    // Listar todas las ubicaciones
    List<Ubicacion> listarTodos();

    // Buscar ubicación por ID
    Optional<Ubicacion> findById(int idUbicacion);
    Ubicacion buscarPorId(int idUbicacion);

    // Registrar una nueva ubicación
    Ubicacion registrarUbicacion(Ubicacion ubicacion);

    // Actualizar una ubicación existente
    Ubicacion actualizarUbicacion(Ubicacion ubicacion);

    // Eliminar una ubicación por ID
    void eliminarUbicacion(int idUbicacion);
    
   Optional<Ubicacion> findByNombreUbicacion(String nombreUbicacion);
    

    /* Listar ubicaciones por tipo de ubicación
    List<Ubicacion> listarPorTipoUbicacion(int idTipoUbicacion);*/
    
    /*------------VALIDAICONES DE REPITICION DE NOMBRE----------------*/
    
    //reg
    List<Ubicacion> listaPorNombreIgualRegistra(String nombreUbicacion);
    
    //act
    List<Ubicacion> listaPorNombreIgualActualiza(String nombreUbicacion, int idUbicacion);

    
}