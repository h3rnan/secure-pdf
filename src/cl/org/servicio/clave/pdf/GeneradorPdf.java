package cl.org.servicio.clave.pdf;

import cl.org.servicio.clave.respuesta.RespuestaServicio;

public interface GeneradorPdf {

	public RespuestaServicio generarPdf(String producto, String idDocumento, String nombre, String clave) throws Exception;

}
