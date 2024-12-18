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
            obj.setIdAccesoVehicular(nuevoId);

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

            // Validar Parqueos
            if (obj.getParqueos() == null) {
                salida.put("error", "El parqueo no está asignado");
                return ResponseEntity.badRequest().body(salida);
            }
            Optional<Parqueos> optionalParqueos = parqueosServiceImpl.findById(obj.getParqueos().getIdParqueos());
            if (!optionalParqueos.isPresent()) {
                salida.put("error", "Parqueo no encontrado");
                return ResponseEntity.badRequest().body(salida);
            }

            Parqueos parqueo = optionalParqueos.get();

            // Cambiar el estado del parqueo a "No disponible"
            EstadoEspacios estadoNoDisponible = new EstadoEspacios();
            estadoNoDisponible.setIdEstadoEspacios(4);  // Asumiendo que el ID para "No disponible" es 4
            parqueo.setEstadoEspacios(estadoNoDisponible);
            parqueosServiceImpl.registrarParqueo(parqueo); // Guardar el parqueo con el nuevo estado

            obj.setParqueos(parqueo); // Asignar el parqueo al objeto Acceso_Vehicular

            // Validar Usuario
            if (obj.getUsuario() == null || obj.getUsuario().getIdUsuario() == 0) {
                salida.put("error", "Usuario no proporcionado o inválido");
                return ResponseEntity.badRequest().body(salida);
            }
            Optional<Usuario> optionalUsuario = usuarioServiceImpl.findById(obj.getUsuario().getIdUsuario());
            if (!optionalUsuario.isPresent()) {
                salida.put("error", "Usuario no encontrado");
                return ResponseEntity.badRequest().body(salida);
            }
            obj.setUsuario(optionalUsuario.get());

            // Validar Ubicacion
            if (obj.getUbicacion() == null || obj.getUbicacion().getIdUbicacion() == 0) {
                salida.put("error", "Ubicación no proporcionada o inválida");
                return ResponseEntity.badRequest().body(salida);
            }
            Optional<Ubicacion> optionalUbicacion = ubicacionServiceImpl.findById(obj.getUbicacion().getIdUbicacion());
            if (!optionalUbicacion.isPresent()) {
                salida.put("error", "Ubicación no encontrada");
                return ResponseEntity.badRequest().body(salida);
            }
            obj.setUbicacion(optionalUbicacion.get());

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


   /* @PostMapping("/registrarSalida/{idAccesoVehicular}")
    @ResponseBody
    public ResponseEntity<?> registraSalida(@PathVariable Integer idAccesoVehicular ){

        try {
            accesoVehicularService.registrarSalida(idAccesoVehicular);
            return ResponseEntity.ok("Salida registrada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar Salida: " + e.getMessage());
        }
    }*/
    @PostMapping("/registrarSalida/{idAccesoVehicular}")
    @ResponseBody
    public ResponseEntity<?> registraSalida(@PathVariable Integer idAccesoVehicular) {
        HashMap<String, Object> salida = new HashMap<>();
        try {
            // Recuperar el acceso vehicular por ID
            Optional<Acceso_Vehicular> accesoVehicularOpt = accesoVehicularRepository.findById(idAccesoVehicular);
            if (accesoVehicularOpt.isEmpty()) {
                salida.put("error", "Acceso vehicular no encontrado");
                return ResponseEntity.badRequest().body(salida);
            }

            Acceso_Vehicular accesoVehicular = accesoVehicularOpt.get();
            
            // Obtener el parqueo asociado
            Parqueos parqueo = accesoVehicular.getParqueos();
            if (parqueo == null) {
                salida.put("error", "Parqueo no asignado en el acceso vehicular");
                return ResponseEntity.badRequest().body(salida);
            }

            // Cambiar el estado del parqueo a "Disponible" (suponiendo que el ID para "Disponible" es 3)
            EstadoEspacios estadoDisponible = new EstadoEspacios();
            estadoDisponible.setIdEstadoEspacios(3);  // Asumiendo que el ID para "Disponible" es 3
            parqueo.setEstadoEspacios(estadoDisponible);

            // Guardar el parqueo con el nuevo estado
            parqueosServiceImpl.registrarParqueo(parqueo);

            // Registrar la salida del acceso vehicular
            accesoVehicular.setEstado(AppSettings.INACTIVO_DESC);  // Cambiar el estado de acceso vehicular, si es necesario
            accesoVehicularService.registraAccesoVehicular(accesoVehicular);

            salida.put("mensaje", "Salida registrada correctamente.");
            return ResponseEntity.ok(salida);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar la salida: " + e.getMessage());
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

    //ASIGANCION DE AV A PARQUEO
    @GetMapping("/filtrarPorParqueo/{idParqueos}")
    @ResponseBody
    public ResponseEntity<List<Acceso_Vehicular>> filtrarPorParqueo(@PathVariable Integer idParqueos) {
        List<Acceso_Vehicular> accesos = accesoVehicularService.findByParqueoId(idParqueos);
        return ResponseEntity.ok(accesos);
    }

    @PostMapping("/registrarCliente")
    @ResponseBody
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente) {
        HashMap<String, Object> salida = new HashMap<>();

        try {
            // Buscar si el cliente ya existe basado en su DNI
            Optional<Cliente> clienteExistente = clienteServiceImpl.findByDni(cliente.getIdentificador());

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





}
