package cl.org.servicio.clave.control.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import cl.org.servicio.clave.control.impl.ControlClaveImpl;
import cl.org.servicio.clave.control.impl.ControlRespuesta;

import cl.org.servicio.clave.control.ControlClaveProc;
import cl.org.servicio.clave.respuesta.RespuestaServicio;
import cl.org.servicio.clave.respuesta.Respuestas;

@Path("/controlclave")
public class ControlClaveImpl implements ControlClaveProc {


	private static Logger log = LogManager.getLogger(ControlClaveImpl.class);
	private ControlRespuesta control = new ControlRespuesta();


	@GET
	@Path("/{idDocumento}")
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaServicio verClave(@PathParam("idDocumento") String idDocumento) {
		RespuestaServicio result = new RespuestaServicio();
		result = Respuestas.INTENTOS_NO_DISPONIBLES();
		int numIntentos= control.getNumeroIntentos(idDocumento);
		if(numIntentos>0){
			result = control.updateControlClave(idDocumento);
			log.info("Se actualizaron la cantidad de intentos disponibles y la fecha de actualización correctamente");
		}
		else{
			if(numIntentos==-1){
				result = Respuestas.ERROR_CONSULTA_INTENTOS();
				log.error("Código: "+result.getCodigo()+"; Mensaje "+result.getMensaje());
			}
		}
		return result;
	}

}
