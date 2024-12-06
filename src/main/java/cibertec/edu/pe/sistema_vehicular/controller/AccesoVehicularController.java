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
    private UbicacionServiceImpl ubicacionServiceImpl;
    @Autowired
    private UbicacionService ubicacionService;

    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

    @Autowired
    private ParqueosServiceImpl parqueosServiceImpl;
    @Autowired
    private ParqueosService parqueosService;
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
           
            obj.setFechaRegistro(new Date());

            // Obtener el último ID
            Integer ultimoId = accesoVehicularRepository.findMaxId();
            int nuevoId = (ultimoId != null) ? ultimoId + 1 : 1;
            obj.setIdAccesoVehicular(nuevoId); // Establecer el nuevo ID


         // Validar Cliente
            if (obj.getCliente() == null || obj.getCliente().getIdCliente() == 0) {
                salida.put("error", "Cliente no proporcionado o inválido");
                return ResponseEntity.badRequest().body(salida);
            }
            Optional<Cliente> optionalCliente = clienteServiceImpl.findById(obj.getCliente().getIdCliente());
            if (!optionalCliente.isPresent()) {
                salida.put("error", "Cliente no encontrado");
                return ResponseEntity.badRequest().body(salida);
            }
            obj.setCliente(optionalCliente.get());

         // Validar si el objeto Parqueos está presente en el objeto Acceso_Vehicular
            if (obj.getParqueos() == null) {
                salida.put("error", "El parqueo no está asignado");
                return ResponseEntity.badRequest().body(salida);
            }

            // Ahora buscar el parqueo por su ID
            Optional<Parqueos> optionalParqueos = parqueosServiceImpl.findById(obj.getParqueos().getIdParqueos());
            if (!optionalParqueos.isPresent()) {
                salida.put("error", "Parqueo no encontrado");
                return ResponseEntity.badRequest().body(salida);
            }

            // Asignar el parqueo al objeto Acceso_Vehicular
            obj.setParqueos(optionalParqueos.get());


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

    //buscar por nombre de ubicacion
    @GetMapping("/ubicacion/idUbicacion/{nombreUbicacion}")
    @ResponseBody
    public ResponseEntity<?> obtenerIdUbicacion(@PathVariable String nombreUbicacion) {
        Optional<Ubicacion> ubicacion = ubicacionServiceImpl.findByNombreUbicacion(nombreUbicacion); // Asume que tienes el método findByNombre
        if (ubicacion.isPresent()) {
            return ResponseEntity.ok(ubicacion.get().getIdUbicacion());
        } else {
            HashMap<String, Object> salida = new HashMap<>();
            salida.put("error", "Ubicacion no encontrada");
            return ResponseEntity.badRequest().body(salida);
        }
    }

    // Busca por ID de parqueo
    @GetMapping("/parqueos/id/{idParqueos}")
    @ResponseBody
    public ResponseEntity<?> obtenerIdParqueos(@PathVariable Integer idParqueos) {
        Optional<Parqueos> parqueos = parqueosServiceImpl.findById(idParqueos); // Usa el servicio inyectado
        if (parqueos.isPresent()) {
            return ResponseEntity.ok(parqueos.get().getIdParqueos());
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


/*
    @PostMapping("/registrarCliente")
    @ResponseBody
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente) {
        HashMap<String, Object> salida = new HashMap<>();

        try {
            // Buscar si el cliente ya existe basado en su identificador (DNI o ID similar)
            Optional<Cliente> clienteExistente = clienteServiceImpl.findById(cliente.getIdCliente());

            if (clienteExistente.isPresent()) {
                // Si el cliente existe, se actualizan los datos
                Cliente clienteActualizado = clienteExistente.get();
                clienteActualizado.setNombres(cliente.getNombres());
                clienteActualizado.setApellidos(cliente.getApellidos());
                clienteActualizado.setTelefono(cliente.getTelefono());
                // Agrega otros campos a actualizar aquí
                clienteServiceImpl.registraCliente(clienteActualizado); // Guardar actualización
                salida.put("mensaje", "Cliente actualizado exitosamente.");
                salida.put("cliente", clienteActualizado);
                return ResponseEntity.ok(salida);
            } else {
                // Si el cliente no existe, se registra uno nuevo
                cliente.setNumIncidencias(0); // Inicializar el número de incidencias a 0
                Cliente nuevoCliente = clienteServiceImpl.registraCliente(cliente); // Guardar nuevo cliente
                salida.put("mensaje", "Cliente registrado exitosamente.");
                salida.put("cliente", nuevoCliente);
                return ResponseEntity.ok(salida);
            }

        } catch (Exception e) {
            e.printStackTrace();
            salida.put("error", "Ocurrió un error al registrar o actualizar el cliente: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(salida);
        }
    }
*/



}
