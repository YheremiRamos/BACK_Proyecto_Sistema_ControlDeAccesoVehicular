package cibertec.edu.pe.sistema_vehicular.service;

import cibertec.edu.pe.sistema_vehicular.entity.Acceso_Vehicular;

import java.util.List;

import org.springframework.data.repository.query.Param;

public interface AccesoVehicularService {

    public abstract Acceso_Vehicular registraAccesoVehicular(Acceso_Vehicular obj);

    public abstract List<Object[]> listarSalidaVehicular();

    public abstract void registrarIncidencia(Integer idCliente);

    public abstract void registrarSalida(Integer idAccesoVehicular);
   
    
    public abstract List<Acceso_Vehicular> findByParqueoId(@Param("idParqueos") Integer idParqueos);

}
