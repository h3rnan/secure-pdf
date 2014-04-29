package cl.org.servicio.clave.control.impl;

import java.util.Date;

import cl.org.servicio.clave.dao.ControlClaveDao;
import cl.org.servicio.clave.dao.modelo.ControlClave;
import cl.org.servicio.clave.respuesta.RespuestaServicio;
import cl.org.servicio.clave.respuesta.Respuestas;


public class ControlRespuesta {
	ControlClaveDao dao = new ControlClaveDao();

	public ControlRespuesta() {}

	public int getNumeroIntentos(String idDocumento) {
		int intentos;
		try {
			int id = Integer.parseInt(idDocumento);
			ControlClave obj = dao.selectById(id);
			intentos = obj.getNumeroIntentos();
			return intentos;
		} catch (Exception e) {
			intentos = -1;
		}
		return intentos;
	}

	public RespuestaServicio updateControlClave(String idDocumento) {
		int id = Integer.parseInt(idDocumento);
		RespuestaServicio respuesta = new RespuestaServicio();
		ControlClave obj = dao.selectById(id);
		if (obj == null) {
			respuesta = Respuestas.ERROR_CONSULTA_INTENTOS();
		} else {
			int res = dao.updateById(id, new Date());
			switch (res) {
			case -1:
				respuesta = Respuestas.ERROR_ACTUALIZA_TABLA();
				break;
			case 0:
				respuesta = Respuestas.ERROR_ACTUALIZA_TABLA_ID();
				break;
			default:
				respuesta = Respuestas.VER_CLAVE();
				break;
			}
		}

		return respuesta;
	}
}
