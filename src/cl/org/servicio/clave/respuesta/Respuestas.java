package cl.org.servicio.clave.respuesta;

import cl.org.servicio.clave.respuesta.RespuestaServicio;

public class Respuestas {

	public static RespuestaServicio ERROR_CONTROL_CLAVE(String msg) {
		return new RespuestaServicio(-5, msg);
	}

	//Respuestas REST -- Ver Calve
	public static RespuestaServicio VER_CLAVE()				 	{ return new RespuestaServicio(1,	"Intento validado"); }
	public static RespuestaServicio INTENTOS_NO_DISPONIBLES()	{ return new RespuestaServicio(0,	"No quedan intentos disponibles"); }
	public static RespuestaServicio ERROR_CONSULTA_INTENTOS() 	{ return new RespuestaServicio(-1,	"--Error-- Busqueda de id invalida"); }
	public static RespuestaServicio ERROR_ACTUALIZA_TABLA_ID() 	{ return new RespuestaServicio(-2,	"--Error-- No existe Id"); }
	public static RespuestaServicio ERROR_ACTUALIZA_TABLA() 	{ return new RespuestaServicio(-3,	"--Error-- Actualizacion invalida"); }

	//Respuestas SOAP -- Generar PDF
	public static RespuestaServicio PDF_GENERADO()				{ return new RespuestaServicio(-11,	"PDF Generado Exitosamente!!"); }
	public static RespuestaServicio PDF_NO_GENERADO()			{ return new RespuestaServicio(-10,	"--Error-- No se genero el archivo PDF"); }

}
