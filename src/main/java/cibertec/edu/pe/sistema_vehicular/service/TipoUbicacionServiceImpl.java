package cibertec.edu.pe.sistema_vehicular.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cibertec.edu.pe.sistema_vehicular.entity.TipoUbicacion;
import cibertec.edu.pe.sistema_vehicular.repository.TipoUbicacionRepository;

@Service
public class TipoUbicacionServiceImpl implements TipoUbicacionService {

    @Autowired
    private TipoUbicacionRepository repository;

    @Override
    public List<TipoUbicacion> listaTodos() {
        return repository.findByOrderByNombreTipoUbicacionAsc();
    }
}
