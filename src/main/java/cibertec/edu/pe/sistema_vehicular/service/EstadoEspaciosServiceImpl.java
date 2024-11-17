package cibertec.edu.pe.sistema_vehicular.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cibertec.edu.pe.sistema_vehicular.entity.EstadoEspacios;
import cibertec.edu.pe.sistema_vehicular.repository.EstadoEspaciosRepository;

@Service
public class EstadoEspaciosServiceImpl implements EstadoEspaciosService {

    @Autowired
    private EstadoEspaciosRepository repository;

    @Override
    public List<EstadoEspacios> listaTodos() {
        return repository.findByOrderByNombreEstadoEspaciosAsc();
    }
}
