package cibertec.edu.pe.sistema_vehicular.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cibertec.edu.pe.sistema_vehicular.entity.Ubicacion;
import cibertec.edu.pe.sistema_vehicular.service.UbicacionService;
import cibertec.edu.pe.sistema_vehicular.util.AppSettings;

@RestController
@RequestMapping("/url/ubicacion")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    // Obtener todas las ubicaciones
    @GetMapping
    public ResponseEntity<List<Ubicacion>> listarTodos() {
        List<Ubicacion> lista = ubicacionService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    // Obtener ubicación por ID
    @GetMapping("/{idUbicacion}")
    public ResponseEntity<Ubicacion> buscarPorId(@PathVariable("idUbicacion") int idUbicacion) {
        Ubicacion ubicacion = ubicacionService.buscarPorId(idUbicacion);
        if (ubicacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ubicacion);
    }

    // Registrar nueva ubicación
    @PostMapping("/registraUbicacion")
    public ResponseEntity<Map<String, Object>> registrarUbicacion(@RequestBody Ubicacion ubicacion) {
        Map<String, Object> response = new HashMap<>();
        
       
        try {
        	 //VALIDACION
        	 List<Ubicacion> lstUbicacions = ubicacionService.listaPorNombreIgualRegistra(ubicacion.getNombreUbicacion());
        	 if (!lstUbicacions.isEmpty()) {
        		 response.put("mensaje", "La ubicación '" + ubicacion.getNombreUbicacion() + "' ya existe");
                 return ResponseEntity.ok(response);
			}
        	 //
            Ubicacion ubicacionGuardada = ubicacionService.registrarUbicacion(ubicacion);
            response.put("mensaje", "Ubicación registrada exitosamente");
            response.put("ubicacion", ubicacionGuardada);
        } catch (Exception e) {
            response.put("mensaje", "Error al registrar ubicación");
            e.printStackTrace();
        }
        
        return ResponseEntity.ok(response);
    }

 // Actualizar ubicación existente
    @PutMapping("/actualizaUbicacion/{idUbicacion}")
    public ResponseEntity<Map<String, Object>> actualizarUbicacion(@PathVariable("idUbicacion") int idUbicacion, 
                                                                   @RequestBody Ubicacion ubicacion) {
        Map<String, Object> response = new HashMap<>();
        try {
        	 List<Ubicacion> lstUbicacions = ubicacionService.listaPorNombreIgualRegistra(ubicacion.getNombreUbicacion());
        	 // Buscar la ubicación existente
             Optional<Ubicacion> ubicacionExistente = Optional.ofNullable(ubicacionService.buscarPorId(idUbicacion));
        	 if (!lstUbicacions.isEmpty()) {
        		 response.put("error", "La ubicación '" + ubicacion.getNombreUbicacion() + "' ya existe");
                 return ResponseEntity.ok(response);
			}
           

        	 else if (!ubicacionExistente.isPresent()) {
                response.put("error", "La ubicación con ID " + idUbicacion + " no existe");
                return ResponseEntity.ok(response);
            }

            // Asignar el ID al objeto recibido
            ubicacion.setIdUbicacion(idUbicacion);

            // Actualizar ubicación
            Ubicacion ubicacionActualizada = ubicacionService.actualizarUbicacion(ubicacion);
            response.put("mensaje", "Ubicación actualizada exitosamente");
            response.put("ubicacion", ubicacionActualizada);
        } catch (Exception e) {
            response.put("error", "Error al actualizar ubicación");
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }


    // Eliminar ubicación por ID
    @DeleteMapping("/eliminaUbicacion/{idUbicacion}")
    public ResponseEntity<Map<String, Object>> eliminarUbicacion(@PathVariable("idUbicacion") int idUbicacion) {
        Map<String, Object> response = new HashMap<>();
        try {
            ubicacionService.eliminarUbicacion(idUbicacion);
            response.put("mensaje", "Ubicación eliminada exitosamente");
        } catch (Exception e) {
            response.put("mensaje", "Error al eliminar ubicación");
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }
    
   
    
    /*--------------------SEM 12 - VALIDACION/FILTRACIONES EXTRA------------------*/

    /* Listar ubicaciones por tipo
    @GetMapping("/tipo/{idTipoUbicacion}")
    public ResponseEntity<List<Ubicacion>> listarPorTipo(@PathVariable("idTipoUbicacion") int idTipoUbicacion) {
        List<Ubicacion> ubicaciones = ubicacionService.listarPorTipoUbicacion(idTipoUbicacion);
        return ResponseEntity.ok(ubicaciones);
    }
*/
    
}