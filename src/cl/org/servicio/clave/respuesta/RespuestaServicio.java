package cl.org.servicio.clave.respuesta;

import java.io.Serializable;

//@XmlType(propOrder={"codigo", "mensaje"})
public class RespuestaServicio implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String mensaje;

	public RespuestaServicio(int codigo, String mensaje) {
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public RespuestaServicio() {}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
