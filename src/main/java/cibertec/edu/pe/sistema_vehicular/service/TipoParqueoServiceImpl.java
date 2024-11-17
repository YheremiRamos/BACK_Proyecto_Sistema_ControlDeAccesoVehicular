package cibertec.edu.pe.sistema_vehicular.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cibertec.edu.pe.sistema_vehicular.entity.TipoParqueo;
import cibertec.edu.pe.sistema_vehicular.repository.TipoParqueoRepository;

@Service
public class TipoParqueoServiceImpl implements TipoParqueoService {

    @Autowired
    private TipoParqueoRepository repository;

    @Override
    public List<TipoParqueo> listaTodos() {
        return repository.findByOrderByNombreTipoParqueoAsc();
    }
}
