package cibertec.edu.pe.sistema_vehicular.service;
import cibertec.edu.pe.sistema_vehicular.entity.Parqueo;
import cibertec.edu.pe.sistema_vehicular.repository.ParqueoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParqueoServiceImpl implements  ParqueoService{

    @Autowired
    ParqueoRepository parqueoRepository;

    public Optional<Parqueo> findById(int id) {
        return parqueoRepository.findById(id);
    }
    
    

    public Optional<Parqueo> findByTipoVechiculoPermitido(String tipoVehiculoPermitido) {
        return parqueoRepository.findByTipoVehiculoPermitido(tipoVehiculoPermitido);
    }

}
