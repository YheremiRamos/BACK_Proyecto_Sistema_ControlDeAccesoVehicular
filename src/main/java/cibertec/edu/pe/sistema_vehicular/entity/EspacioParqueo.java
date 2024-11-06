package cibertec.edu.pe.sistema_vehicular.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "espacios_parqueo")
public class EspacioParqueo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEspacio;

    private int idParqueo;

    private String tipoEspacio; // general, discapacitado, gerencia
    private int numeroEspacio;
    private String estado; // disponible, no disponible, ocupado
    private java.sql.Timestamp fechaCreacion;
    private java.sql.Timestamp fechaActualizacion;
}
