package cibertec.edu.pe.sistema_vehicular.service;

import java.util.List;
import cibertec.edu.pe.sistema_vehicular.entity.Ubicacion;

public interface UbicacionService {

    // Listar todas las ubicaciones
    List<Ubicacion> listarTodos();

    // Buscar ubicación por ID
    Ubicacion buscarPorId(int idUbicacion);

    // Registrar una nueva ubicación
    Ubicacion registrarUbicacion(Ubicacion ubicacion);

    // Actualizar una ubicación existente
    Ubicacion actualizarUbicacion(Ubicacion ubicacion);

    // Eliminar una ubicación por ID
    void eliminarUbicacion(int idUbicacion);

    /* Listar ubicaciones por tipo de ubicación
    List<Ubicacion> listarPorTipoUbicacion(int idTipoUbicacion);*/

    
}
