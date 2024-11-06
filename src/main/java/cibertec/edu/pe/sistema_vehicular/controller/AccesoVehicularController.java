package cibertec.edu.pe.sistema_vehicular.controller;


import cibertec.edu.pe.sistema_vehicular.entity.*;
import cibertec.edu.pe.sistema_vehicular.repository.AccesoVehicularRepository;
import cibertec.edu.pe.sistema_vehicular.service.*;
import cibertec.edu.pe.sistema_vehicular.util.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
            obj.setFechaRegistro(new Date());
            obj.setFechaActualizacion(new Date());


            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName(); // Obtener el nombre del usuario autenticado
            Usuario usuarioActual = usuarioServiceImpl.buscaPorLogin(username); // Método para buscar el usuario
            obj.setUsuario(usuarioActual);

            Optional<Cliente> optionalCliente = clienteServiceImpl.findById(obj.getCliente().getIdCliente());
            if (!optionalCliente.isPresent()) {
                salida.put("error", "Cliente no encontrado");
                return ResponseEntity.badRequest().body(salida);
            }
            obj.setCliente(optionalCliente.get());

            Optional<EspacioParqueo> optionalEspacio = espacioParqueoServiceImpl.findById(obj.getEspacio().getIdEspacio());
            if (!optionalEspacio.isPresent()) {
                salida.put("error", "Espacio de parqueo no encontrado");
                return ResponseEntity.badRequest().body(salida);
            }
            obj.setEspacio(optionalEspacio.get());

            if (usuarioActual == null) {
                salida.put("error", "Usuario autenticado no encontrado");
                return ResponseEntity.badRequest().body(salida);
            }

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


}
