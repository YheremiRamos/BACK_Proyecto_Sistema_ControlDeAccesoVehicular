package cibertec.edu.pe.sistema_vehicular.service;

import cibertec.edu.pe.sistema_vehicular.entity.Acceso_Vehicular;

import java.util.List;

public interface AccesoVehicularService {

    public abstract Acceso_Vehicular registraAccesoVehicular(Acceso_Vehicular obj);

    public abstract List<Object[]> listarSalidaVehicular();


}
