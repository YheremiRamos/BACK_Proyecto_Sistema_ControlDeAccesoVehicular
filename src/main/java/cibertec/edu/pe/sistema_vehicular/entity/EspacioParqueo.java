package cibertec.edu.pe.sistema_vehicular.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String estado; // disponible, ocupado, reservado
    private java.sql.Timestamp fechaCreacion;
    private java.sql.Timestamp fechaActualizacion;
}
