package cl.org.servicio.clave.dao.modelo;

import java.io.Serializable;
import java.util.Date;

public class ControlClave implements Serializable {

//	ID NUMBER NOT NULL,
//	RUT VARCHAR2(15) NOT NULL,
//	NUMERO_INTENTOS NUMBER(2) NOT NULL,
//	FECHA_ACTUALIZACION DATE DEFAULT SYSDATE NOT NULL,
//	ESTADO VARCHAR2(3) DEFAULT 'ACT' NOT NULL,

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String rut;
	private int numeroIntentos;
	private Date fechaActualizacion;
	private String estado;

	public ControlClave(int id, String rut, int numeroIntentos, Date fechaActualizacion, String estado) {
		super();
		this.id = id;
		this.rut = rut;
		this.numeroIntentos = numeroIntentos;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
	}

	public ControlClave(int id, String rut, int numeroIntentos) {
		super();
		this.id = id;
		this.rut = rut;
		this.numeroIntentos = numeroIntentos;
	}

	public ControlClave() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public int getNumeroIntentos() {
		return numeroIntentos;
	}

	public void setNumeroIntentos(int numeroIntentos) {
		this.numeroIntentos = numeroIntentos;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
