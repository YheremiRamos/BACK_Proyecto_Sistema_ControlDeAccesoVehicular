package cibertec.edu.pe.sistema_vehicular.service;

import cibertec.edu.pe.sistema_vehicular.entity.Acceso_Vehicular;
import cibertec.edu.pe.sistema_vehicular.repository.AccesoVehicularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccesoVehicularServiceImpl implements  AccesoVehicularService{

    @Autowired
    private AccesoVehicularRepository repository;

    @Override
    public Acceso_Vehicular registraAccesoVehicular(Acceso_Vehicular obj){
        return repository.save(obj);
    }

    @Override
    public List<Object[]> listarSalidaVehicular() {
        return repository.listarAccesoVehicularConDetalles();
    }

}
