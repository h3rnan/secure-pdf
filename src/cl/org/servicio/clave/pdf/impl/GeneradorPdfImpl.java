package cl.org.servicio.clave.pdf.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import cl.org.servicio.clave.pdf.GeneradorPdf;
import cl.org.servicio.clave.respuesta.RespuestaServicio;
import cl.org.servicio.clave.respuesta.Respuestas;

import com.itextpdf.text.DocumentException;

public class GeneradorPdfImpl implements GeneradorPdf {

	private static Logger log = LogManager.getLogger(GeneradorPdfItext.class);

	private String background    = "/resources/fondo_reporte.jpg";
	private String pathSwf       = "/resources/ValidatorPass.swf";
	private String keystore      = "/resources/ks-pdf.jce";
	private String passKS        = null;
	private String salidaPdf     = null;
	private String urlRestJs 	 = null;
	private String passLectura   = null;
	private String passEscritura = null;

	private PropertiesPreferencesPdf prop = null;

	public GeneradorPdfImpl(PropertiesPreferencesPdf prop) {
		this.prop = prop;
	}

	public RespuestaServicio generarPdf(String producto, String idDocumento, String nombreCliente, String clave) throws Exception{
		RespuestaServicio respuesta = new RespuestaServicio();
		String id = Utils.encodeId(idDocumento);
		try {
			GeneradorPdfItext gen = new GeneradorPdfItext();
			if (prop.isSignPdf()) {
				gen.generatePdf(salidaPdf, pathSwf, urlRestJs+id, clave, background,
						passLectura, passEscritura, producto, nombreCliente, false, null);
				gen.signPdf(keystore, passKS, salidaPdf, passLectura, passEscritura, prop);
				tratamientoFichero(gen);
			} else {
				gen.generatePdf(salidaPdf, pathSwf, urlRestJs+id, clave, background,
						passLectura, passEscritura, producto, nombreCliente, true, prop);
			}
			respuesta = Respuestas.PDF_GENERADO();
		} catch (IOException e) {
			respuesta = Respuestas.PDF_NO_GENERADO();
			log.error("-- Ha ocurrido un ERROR --", e);
			e.printStackTrace();
		} catch (DocumentException e) {
			respuesta = Respuestas.PDF_NO_GENERADO();
			log.error("-- Ha ocurrido un ERROR --", e);
		}
		return respuesta;
	}

	private boolean tratamientoFichero(GeneradorPdfItext gen) throws IOException {
		File fEntrada = new File(salidaPdf);
		File fSign = new File(gen.getPdfSign());
		if (!fEntrada.delete()) {
			FileUtils.forceDelete(fEntrada);
		}
		if (fSign.renameTo(new File(salidaPdf))) {
			log.info("Documento Generado Exitosamente!!");
		} else {
			log.info("-- WARNING -- No se renombró el PDF");
		}
		return true;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getPathSwf() {
		return pathSwf;
	}

	public void setPathSwf(String pathSwf) {
		this.pathSwf = pathSwf;
	}

	public String getKeystore() {
		return keystore;
	}

	public void setKeystore(String keystore) {
		this.keystore = keystore;
	}

	public String getPassKS() {
		return passKS;
	}

	public void setPassKS(String passKS) {
		this.passKS = passKS;
	}

	public String getSalidaPdf() {
		return salidaPdf;
	}

	public void setSalidaPdf(String salidaPdf) {
		this.salidaPdf = salidaPdf;
	}

	public String getUrlRestJs() {
		return urlRestJs;
	}

	public void setUrlRestJs(String urlRestJs) {
		this.urlRestJs = urlRestJs;
	}

	public String getPassLectura() {
		return passLectura;
	}

	public void setPassLectura(String passLectura) {
		this.passLectura = passLectura;
	}

	public String getPassEscritura() {
		return passEscritura;
	}

	public void setPassEscritura(String passEscritura) {
		this.passEscritura = passEscritura;
	}

	public PropertiesPreferencesPdf getProp() {
		return prop;
	}

	public void setProp(PropertiesPreferencesPdf prop) {
		this.prop = prop;
	}

}
