package cibertec.edu.pe.sistema_vehicular.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
   
    
    @Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "America/Lima")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaCreacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fechaActualizacion;
	
   
}