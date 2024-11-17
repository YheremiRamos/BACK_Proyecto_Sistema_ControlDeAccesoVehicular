package cibertec.edu.pe.sistema_vehicular.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cibertec.edu.pe.sistema_vehicular.entity.Parqueos;

import cibertec.edu.pe.sistema_vehicular.service.ParqueosService;
import cibertec.edu.pe.sistema_vehicular.util.AppSettings;

@RestController
@RequestMapping("/url/parqueos")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ParqueosController {

    @Autowired
    private ParqueosService parqueosService;

    // Obtener todos los parqueos
    @GetMapping
    public ResponseEntity<List<Parqueos>> listarTodos() {
        List<Parqueos> lista = parqueosService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    // Obtener parqueo por ID
    @GetMapping("/{idParqueo}")
    public ResponseEntity<Parqueos> buscarPorId(@PathVariable("idParqueo") int idParqueo) {
        Parqueos parqueo = parqueosService.buscarPorId(idParqueo);
        if (parqueo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(parqueo);
    }

    // Registrar nuevo parqueo
    @PostMapping("/registraParqueo")
    public ResponseEntity<Map<String, Object>> registrarParqueo(@RequestBody Parqueos parqueo) {
        Map<String, Object> response = new HashMap<>();
        try {
            Parqueos parqueoGuardado = parqueosService.registrarParqueo(parqueo);
            response.put("mensaje", "Parqueo registrado exitosamente");
            response.put("parqueo", parqueoGuardado);
        } catch (Exception e) {
            response.put("mensaje", "Error al registrar parqueo");
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    // Actualizar parqueo existente
    @PutMapping("/actualizaParqueo/{idParqueo}")
    public ResponseEntity<Map<String, Object>> actualizarParqueo(@RequestBody Parqueos parqueo) {
        Map<String, Object> response = new HashMap<>();
        try {
            Parqueos parqueoActualizado = parqueosService.actualizarParqueo(parqueo);
            response.put("mensaje", "Parqueo actualizado exitosamente");
            response.put("parqueo", parqueoActualizado);
        } catch (Exception e) {
            response.put("mensaje", "Error al actualizar parqueo");
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    // Eliminar parqueo por ID
    @DeleteMapping("/eliminaParqueo/{idParqueo}") 
    public ResponseEntity<Map<String, Object>> eliminarParqueo(@PathVariable("idParqueo") int idParqueo) {
        Map<String, Object> response = new HashMap<>();
        try {
            parqueosService.eliminarParqueo(idParqueo);
            response.put("mensaje", "Parqueo eliminado exitosamente");
        } catch (Exception e) {
            response.put("mensaje", "Error al eliminar parqueo");
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    // Listar parqueos por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Parqueos>> listarPorEstado(@PathVariable("estado") String estado) {
        List<Parqueos> parqueos = parqueosService.listarPorEstado(estado);
        return ResponseEntity.ok(parqueos);
    }

    // Listar parqueos por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Parqueos>> listarPorTipo(@PathVariable("tipo") String tipo) {
        List<Parqueos> parqueos = parqueosService.listarPorTipo(tipo);
        return ResponseEntity.ok(parqueos);
    }
    
    /*--------------------LISTADO Y AGRUPACION DE PARQUEOS EN UBICACIONES------------------*/
 // Obtener parqueos agrupados por ubicación
    @GetMapping("/agrupadosPorUbicacion")
    public ResponseEntity<List<Map<String, Object>>> listarAgrupadosPorUbicacion() {
        // Obtener todos los parqueos
        List<Parqueos> listaParqueos = parqueosService.listarTodos();

        // Agrupar los parqueos por ubicación
        Map<Integer, List<Parqueos>> parqueosAgrupadosPorUbicacion = listaParqueos.stream()
            .collect(Collectors.groupingBy(p -> p.getUbicacion().getIdUbicacion()));

        // Transformar el mapa en el formato esperado
        List<Map<String, Object>> resultado = parqueosAgrupadosPorUbicacion.entrySet().stream()
            .map(entry -> {
                Map<String, Object> agrupado = new HashMap<>();
                agrupado.put("UbicacionId", entry.getKey()); // Cambié 'ubicacionId' a 'UbicacionId'

                // Transformar los parqueos para incluir solo id
                List<Map<String, Object>> parqueos = entry.getValue().stream()
                    .map(p -> {
                        Map<String, Object> parqueo = new HashMap<>();
                        parqueo.put("Id", p.getIdParqueo()); // Cambié 'id' a 'Id' (para que sea mayúscula)
                        return parqueo;
                    })
                    .collect(Collectors.toList());

                agrupado.put("Parqueos", parqueos); // Cambié 'parqueos' a 'Parqueos'
                return agrupado;
            })
            .collect(Collectors.toList());

        // Retornar la respuesta agrupada
        return ResponseEntity.ok(resultado);
    }





}
