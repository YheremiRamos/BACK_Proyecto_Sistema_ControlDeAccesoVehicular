package cibertec.edu.pe.sistema_vehicular.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "parqueo")
public class Parqueo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idParqueo;
	
    private String ubicacion;//cbo //Agregar tabla
    private int cantEspaciosGenerales;
    private int cantEspaciosDiscapacitados;
    private int capacidadTotal;
    private String tipoVehiculoPermitido;
}
