package cibertec.edu.pe.sistema_vehicular.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cibertec.edu.pe.sistema_vehicular.entity.Cliente;
import cibertec.edu.pe.sistema_vehicular.service.ClienteService;
import cibertec.edu.pe.sistema_vehicular.util.AppSettings;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import java.util.Collections;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping("/url/cliente")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ClienteController {

	  @Autowired
	    private ClienteService clienteService;
	  
		@GetMapping
		@ResponseBody
		public ResponseEntity<List<Cliente>> listaTodosClientes(){
			List<Cliente> lista = clienteService.ListarTodosClientes();
			return ResponseEntity.ok(lista);
		}
	    @GetMapping("/consultaClientePorParametros") //http://localhost:8090/url/consultaAutor/consultaAutorPorParametros?nombres=&apellidos&fecNacDesde=1900-01-01&fecNacHasta=2020-01-01&telefono=&celular=&orcid=&estado=1&idPais=-1&idGrado=-1

		 public ResponseEntity<?> consultaClientePorParametros(
		            @RequestParam(name = "nombres", required=true, defaultValue = "") String nombres,
		            @RequestParam(name = "apellidos", required=true, defaultValue = "") String apellidos,
		            @RequestParam(name = "identificador", required=true, defaultValue = "") String identificador)
		           {

		        List<Cliente> lstSalida = clienteService.listaCompleja(
		        		"%"+nombres+"%", "%"+apellidos+"%", "%"+identificador+"%");
		        return ResponseEntity.ok(lstSalida);
		    }

		  @GetMapping("/buscarClientePorId/{idCliente}")
		  public Optional<Cliente> buscarClienteId(@PathVariable int idCliente){
			  return clienteService.buscaClienteId(idCliente);
		  }
		  
		//_______________________ INFORME PDF _______________________
		@PostMapping("/informeLimiteIncidenciasPDF")
		public void clienteInformePDF(
				@RequestParam(name="idCliente", required=true, defaultValue="-1") int idCliente,
				HttpServletRequest request, HttpServletResponse response
		) 
		{
			try {
				// PASO 1 Fuente de datos
				Optional<Cliente> optionalCliente = clienteService.buscaClienteId(idCliente);
				// Verificar si el cliente está presente
		        if (!optionalCliente.isPresent()) {
		            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cliente no encontrado");
		            return;
		        }

		        // Convertir el cliente a una lista
		        Cliente cliente = optionalCliente.get();
		        List<Cliente> listaCliente = Collections.singletonList(cliente);
		        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaCliente);

				
				// PASO 2 Diseño de reporte
				String fileReporte = request.getServletContext().getRealPath("/informe.jasper");
				FileInputStream stream = new FileInputStream(new File(fileReporte));
				
				// PASO3 parámetros adicionales
				String fileLogo = request.getServletContext().getRealPath("/logo.jpg");
		        Map<String, Object> params = new HashMap<>();
		        params.put("RUTA_LOGO", fileLogo);

				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);


				// PASO 5 parametros en el Header del mensajes HTTP
				response.setContentType("application/pdf");
				response.addHeader("Content-disposition", "attachment; filename=InformeLimiteIncidencias.pdf");

				// PASO 6 Se envía el pdf
				OutputStream outStream = response.getOutputStream();
				JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		  
}
