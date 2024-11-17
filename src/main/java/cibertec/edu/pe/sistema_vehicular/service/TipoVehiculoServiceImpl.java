package cibertec.edu.pe.sistema_vehicular.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cibertec.edu.pe.sistema_vehicular.entity.TipoVehiculo;
import cibertec.edu.pe.sistema_vehicular.repository.TipoVehiculoRepository;

@Service
public class TipoVehiculoServiceImpl implements TipoVehiculoService {

    @Autowired
    private TipoVehiculoRepository repository;

    @Override
    public List<TipoVehiculo> listaTodos() {
        return repository.findByOrderByNombreTipoVehiculoAsc();
    }
}
