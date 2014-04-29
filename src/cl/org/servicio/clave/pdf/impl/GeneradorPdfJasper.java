package cl.org.servicio.clave.pdf.impl;

import java.io.InputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class GeneradorPdfJasper {

	public void generateReportPdf(InputStream jrxml, String rutaSalida, Map<String, Object> parametros) {
		JasperReport report;
		JasperPrint print;
		try {
			report = JasperCompileManager.compileReport(jrxml);
			print = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(print, rutaSalida);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

//	public static void main(String[] args) {
//		GeneradorPdfJasper gen = new GeneradorPdfJasper();
//		String plantilla = "resources/clave-segura.jrxml";
//		String salida = "/home/haja/compartida/clave-segura.pdf";
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("NAME_PRODUCT", "Banca Personal");
//		map.put("BACKGROUND_PDF", "resources/fondo_reporte.jpg");
//
//		//gen.generateReportPdf(plantilla, salida, map);
//	}

}
