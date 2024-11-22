package cibertec.edu.pe.sistema_vehicular.controller;

import cibertec.edu.pe.sistema_vehicular.entity.*;
import cibertec.edu.pe.sistema_vehicular.repository.AccesoVehicularRepository;
import cibertec.edu.pe.sistema_vehicular.service.*;
import cibertec.edu.pe.sistema_vehicular.util.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/url/accesoVehicular")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AccesoVehicularController {

    @Autowired
    private AccesoVehicularRepository accesoVehicularRepository;

    @Autowired
    private AccesoVehicularService accesoVehicularService;

    @Autowired
    private EspacioParqueoServiceImpl espacioParqueoServiceImpl;

    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

    @Autowired
    private ParqueoServiceImpl parqueoServiceImpl;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Acceso_Vehicular>> listarAccesoVehicular() {
        List<Acceso_Vehicular> lista = accesoVehicularRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registraAV")
    @ResponseBody
    public ResponseEntity<?> registrarAcceso(@RequestBody Acceso_Vehicular obj) {
        HashMap<String, Object> salida = new HashMap<>();

        try {
            // Asignar valores por defecto
            obj.setEstado(AppSettings.ACTIVO_DESC);
            obj.getEspacio().setEstado(AppSettings.INACTIVO_DESCRIPCION);
            obj.setFechaRegistro(new Date());
            obj.setFechaActualizacion(new Date());

            // Validar Cliente
            Optional<Cliente> optionalCliente = clienteServiceImpl.findById(obj.getCliente().getIdCliente());
            if (!optionalCliente.isPresent()) {
                salida.put("error", "Cliente no encontrado");
                return ResponseEntity.badRequest().body(salida);
            }
            obj.setCliente(optionalCliente.get());

            // Validar Parqueo
            Optional<Parqueo> optionalParqueo = parqueoServiceImpl.findById(obj.getParqueo().getIdParqueo());
            if (!optionalParqueo.isPresent()) {
                salida.put("error", "Parqueo no encontrado");
                return ResponseEntity.badRequest().body(salida);
            }
            obj.setParqueo(optionalParqueo.get());

            // Validar Espacio de Parqueo
            Optional<EspacioParqueo> optionalEspacio = espacioParqueoServiceImpl.findById(obj.getEspacio().getIdEspacio());
            if (!optionalEspacio.isPresent()) {
                salida.put("error", "Espacio de parqueo no encontrado");
                return ResponseEntity.badRequest().body(salida);
            }
            obj.setEspacio(optionalEspacio.get());

            // Validar Usuario
            Optional<Usuario> optionalUsuario = usuarioServiceImpl.findById(obj.getUsuario().getIdUsuario());
            if (!optionalUsuario.isPresent()) {
                salida.put("error", "Usuario no encontrado");
                return ResponseEntity.badRequest().body(salida);
            }
            obj.setUsuario(optionalUsuario.get());

            // Guardar el registro de acceso vehicular
            Acceso_Vehicular objAV = accesoVehicularService.registraAccesoVehicular(obj);
            if (objAV == null) {
                salida.put("mensaje", "Ocurrió un error en el registro");
            } else {
                salida.put("mensaje", "Registro exitoso");
                salida.put("datos", objAV);
            }

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            salida.put("error", "Error de integridad de datos: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            salida.put("error", AppSettings.MENSAJE_REG_ERROR);
        }

        return ResponseEntity.ok(salida);
    }

    @PostMapping("/registrarIncidencia/{idCliente}")
    @ResponseBody
    public ResponseEntity<String> registrarIncidencia(@PathVariable Integer idCliente) {

        try {
            accesoVehicularService.registrarIncidencia(idCliente);
            return ResponseEntity.ok("Incidencia registrada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar incidencia: " + e.getMessage());
        }
    }


    @PostMapping("/registrarSalida/{idAccesoVehicular}")
    @ResponseBody
    public ResponseEntity<?> registraSalida(@PathVariable Integer idAccesoVehicular ){

        try {
            accesoVehicularService.registrarSalida(idAccesoVehicular);
            return ResponseEntity.ok("Salida registrada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar Salida: " + e.getMessage());
        }
    }

    @GetMapping("/cliente/id/{dni}")
    @ResponseBody
    public ResponseEntity<?> obtenerIdCliente(@PathVariable String dni) {
        Optional<Cliente> cliente = clienteServiceImpl.findByDni(dni); // Asume que tienes el método findByDni
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get().getIdCliente());
        } else {
            HashMap<String, Object> salida = new HashMap<>();
            salida.put("error", "Cliente no encontrado");
            return ResponseEntity.badRequest().body(salida);
        }
    }

    @GetMapping("/parqueo/id/{nombre}")
    @ResponseBody
    public ResponseEntity<?> obtenerIdParqueo(@PathVariable String nombre) {
        Optional<Parqueo> parqueo = parqueoServiceImpl.findByTipoVechiculoPermitido(nombre); // Asume que tienes el método findByNombre
        if (parqueo.isPresent()) {
            return ResponseEntity.ok(parqueo.get().getIdParqueo());
        } else {
            HashMap<String, Object> salida = new HashMap<>();
            salida.put("error", "Parqueo no encontrado");
            return ResponseEntity.badRequest().body(salida);
        }
    }

    @GetMapping("/espacio/id/{numeroEspacio}")
    @ResponseBody
    public ResponseEntity<?> obtenerIdEspacio(@PathVariable Integer numeroEspacio) {
        Optional<EspacioParqueo> espacio = espacioParqueoServiceImpl.findByNumeroEspacio(numeroEspacio); // Asume que tienes el método findByNumeroEspacio
        if (espacio.isPresent()) {
            return ResponseEntity.ok(espacio.get().getIdEspacio());
        } else {
            HashMap<String, Object> salida = new HashMap<>();
            salida.put("error", "Espacio de parqueo no encontrado");
            return ResponseEntity.badRequest().body(salida);
        }
    }

    @GetMapping("/listarSalidaVehicular")
    @ResponseBody
    public ResponseEntity<List<Object[]>> listarSalidaVehicular() {
        List<Object[]> lista = accesoVehicularService.listarSalidaVehicular();
        return ResponseEntity.ok(lista);
    }





}
