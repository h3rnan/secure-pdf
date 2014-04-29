package cl.org.servicio.clave.pdf.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDeveloperExtension;
import com.itextpdf.text.pdf.PdfFileSpecification;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfIndirectReference;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PushbuttonField;
import com.itextpdf.text.pdf.richmedia.RichMediaActivation;
import com.itextpdf.text.pdf.richmedia.RichMediaAnnotation;
import com.itextpdf.text.pdf.richmedia.RichMediaCommand;
import com.itextpdf.text.pdf.richmedia.RichMediaConfiguration;
import com.itextpdf.text.pdf.richmedia.RichMediaExecuteAction;
import com.itextpdf.text.pdf.richmedia.RichMediaInstance;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;
import com.itextpdf.text.pdf.security.PrivateKeySignature;

public class GeneradorPdfItext {

	static {
		if (java.security.Security.getProvider("BC") == null)
		 {
			Security.addProvider(new BouncyCastleProvider());
		 }
	}

	private String pdfSign = null;

	public GeneradorPdfItext() {
	}

	public void generatePdf(String dest, String pathSwf, String url, String clave,
			String pathImg, String passLectura, String passEscritura, String producto, String nombre,
			boolean security, PropertiesPreferencesPdf prop) throws IOException, DocumentException {
		Document document = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
		if (security) addSecurity(writer, passLectura, passEscritura, prop);
		configureDocument(document, writer);
		document.open();
		addBackGround(pathImg, document);
		placeChunck(writer, "Envío de clave "+ producto,
					PdfContentByte.ALIGN_LEFT, 18, new BaseColor(85,85,85), -350, 90);
		placeChunck(writer, returnDateString(),
					PdfContentByte.ALIGN_RIGHT, 18, new BaseColor(85,85,85), 350, 90);
		placeChunck(writer, "Te recomendamos ingresar a Banco en Línea y cambiar la clave por una personal",
					PdfContentByte.ALIGN_CENTER, 10, new BaseColor(0, 132, 65), 0, -65);
		placeChunck(writer, "Recuerda que este documento sólo se puede visualizar UNA VEZ.",
					PdfContentByte.ALIGN_CENTER, 11, new BaseColor(0, 132, 65), 0, -92);
		placeChunck(writer, "Para mayor información llámanos a nuestra Banca Telefónica al 600 390 6200.",
					PdfContentByte.ALIGN_CENTER, 11, new BaseColor(0, 132, 65), 0, -105);
		placeChunck(writer, "Atentamente,",
					PdfContentByte.ALIGN_LEFT, 12, new BaseColor(85,85,85), -350, -170);
		placeChunck(writer, "Banco",
					PdfContentByte.ALIGN_LEFT, 12, new BaseColor(85,85,85), -350, -183);
		placeChunck(writer, "Informese sobre la garantía estatal de los depósitos en su banco o en sbif.cl",
					PdfContentByte.ALIGN_CENTER, 10, new BaseColor(85,85,85), 0, -250);
		core(pathSwf, url, clave, nombre, document, writer);
		document.close();
	}

	private void configureDocument(Document document, PdfWriter writer) {
		document.addTitle("Documento de Clave Segura");
        document.addAuthor("Banco");
        document.addSubject("Contiene la clave de su producto");
        document.addKeywords("Metadata, PDF");
        document.addCreator("Banco -- Distribucion de claves");
		writer.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
		writer.addDeveloperExtension(PdfDeveloperExtension.ADOBE_1_7_EXTENSIONLEVEL3);
	}

	private String returnDateString(){
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("EEEE, d MMMM yyyy HH:mm:ss", new Locale("es", "ES"));
        return format.format(now);
	}

	private void addBackGround(String pathImg, Document document)
			throws IOException, BadElementException, MalformedURLException,
			DocumentException {
		InputStream streamImagen = this.getClass().getResourceAsStream(pathImg);
		byte[] imagen = new byte[streamImagen.available()];
		streamImagen.read(imagen);

		Image img = Image.getInstance(imagen);
		img.scalePercent(68);
		img.setAbsolutePosition(
				(PageSize.A4.rotate().getWidth() - img.getScaledWidth()) / 2,
				(PageSize.A4.rotate().getHeight() - img.getScaledHeight()) / 2);
		document.add(img);
	}

	private void placeChunck(PdfWriter writer, String text, int align, int tam, BaseColor bsColor, int x, int y) throws DocumentException, IOException {
		float xPos = (writer.getPageSize().getWidth()/2);
		float yPos = (writer.getPageSize().getHeight()/2);
        PdfContentByte cb = writer.getDirectContent();
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        cb.saveState();
        cb.beginText();
        cb.setColorFill(bsColor);
        cb.moveText(xPos + x, yPos + y);
        cb.setFontAndSize(bf, tam);
        cb.showTextAligned(align, text, xPos + x, yPos + y, 0);
        cb.endText();
        cb.restoreState();
    }

	private void core(String pathSwf, String url, String clave, String nombre,
			Document document, PdfWriter writer) throws IOException,
			DocumentException {

		float xPos = (writer.getPageSize().getWidth()/2);
		float yPos = (writer.getPageSize().getHeight()/2);
		/*
			boton =>  ancho 100, alto 20
			swf   =>  ancho 100, alto 20
		*/
        //XXX step 4
		writer.addJavaScript("function hiddenButton() {this.getField('buttonVerClave').hidden = true;}");
		RichMediaAnnotation richMedia = new RichMediaAnnotation(writer, new Rectangle(xPos-185, yPos-50, xPos+185, yPos+30));
		PdfFileSpecification fs	= PdfFileSpecification.fileEmbedded(writer, pathSwf, "ValidatorPass.swf", null);
		PdfIndirectReference asset = richMedia.addAsset("ValidatorPass.swf", fs);
		RichMediaConfiguration configuration = new RichMediaConfiguration(PdfName.FLASH);
		RichMediaInstance instance = new RichMediaInstance(PdfName.FLASH);
		instance.setAsset(asset);
		configuration.addInstance(instance);
		PdfIndirectReference configurationRef = richMedia.addConfiguration(configuration);
		RichMediaActivation activation = new RichMediaActivation();
		activation.setConfiguration(configurationRef);
		richMedia.setActivation(activation);
		PdfAnnotation richMediaAnnotation = richMedia.createAnnotation();
		richMediaAnnotation.setFlags(PdfAnnotation.FLAGS_PRINT);
		writer.addAnnotation(richMediaAnnotation);
		Rectangle rect = new Rectangle(xPos-50, yPos+33, xPos+50, yPos+53);
		PushbuttonField button = new PushbuttonField(writer, rect, "buttonVerClave");
		button.setTextColor(new BaseColor(85,85,85));;
		button.setFontSize(12);
		button.setText("Ver Clave");
		button.setRotation(document.getPageSize().getRotation());
		button.setLayout(PushbuttonField.LAYOUT_ICON_LEFT_LABEL_RIGHT);
		button.setAlignment(Element.ALIGN_CENTER);
		PdfFormField field = button.getField();
		RichMediaCommand command = new RichMediaCommand(new PdfString("getServiceInvoke"));
		PdfArray params = new PdfArray();
		params.add(0, new PdfString(url));
		params.add(1, new PdfString(clave));
		params.add(2, new PdfString(nombre));
		command.setArguments(params);
		RichMediaExecuteAction action = new RichMediaExecuteAction(richMediaAnnotation.getIndirectReference(), command);
		field.setAction(action);
		writer.addAnnotation(field);
	}

	public void signPdf(String resourceKS, String passKS, String pdfIn, String lectura, String escritura, PropertiesPreferencesPdf prop) throws GeneralSecurityException, IOException, DocumentException {
		KeyStore ks = KeyStore.getInstance("JCEKS");
		InputStream streamKS = this.getClass().getResourceAsStream(resourceKS);
		ks.load(streamKS, passKS.toCharArray());
		String alias = (String) ks.aliases().nextElement();
		PrivateKey key = (PrivateKey) ks.getKey(alias, passKS.toCharArray());
		Certificate[] chain = ks.getCertificateChain(alias);

		PdfReader reader = new PdfReader(pdfIn);
		pdfSign = StringUtils.replace(pdfIn, ".pdf", ".sign.pdf");
		FileOutputStream fout = new FileOutputStream(pdfSign);
		PdfStamper stp = PdfStamper.createSignature(reader, fout, '\0');

		addSecurity(stp.getWriter(), lectura, escritura, prop);

		PdfSignatureAppearance sap = stp.getSignatureAppearance();
		sap.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);
		sap.setReason("Entrga de Clave Segura Con Firma Digital Del Banco");
		sap.setLocation("Santiago, Chile");
		sap.setContact("Banco");

		// signature
        ExternalSignature es = new PrivateKeySignature(key, "SHA-1", "BC");
        ExternalDigest digest = new BouncyCastleDigest();
        MakeSignature.signDetached(sap, digest, es, chain, null, null, null, 0, CryptoStandard.CMS);
    }

	private void addSecurity(PdfWriter writer, String passReader, String passPermission, PropertiesPreferencesPdf prop) throws DocumentException {
		writer.setViewerPreferences(prop.configViewerPreferences() | PdfWriter.CenterWindow);
		writer.setEncryption(passReader.getBytes(), passPermission.getBytes(),
							 prop.configPermission(),
							 PdfWriter.STANDARD_ENCRYPTION_128);
	}

	public String getPdfSign() {
		return pdfSign;
	}


/******** CODIGO PARA INVOCAR FLASH DESDE OPEN DOCUMENT, CON CAMPOS OCULTOS *********/
//	addTextField(writer, "url", url);
//	addTextField(writer, "clave", clave);
//	addTextField(writer, "nombre", nombre);
//
//	PdfAction act = PdfAction.javaScript("var annot = this.getAnnotsRichMedia(0)[0];" +
//										 "if (annot.activated) {" +
//										 "	app.alert('Si');" +
//										 "	annot.callAS('getServiceInvoke');" +
//										 "} else  {" +
//										 "	app.alert('No se que pasa else');" +
//										 "	annot.activated = true;" +
//										 "	annot.callAS('getServiceInvoke');" +
//										 "}", writer);
//
////	writer.setOpenAction(act);
//	writer.setPageAction(PdfWriter.PAGE_OPEN, act);
//
//	private void addTextField(PdfWriter writer, String name, String value) throws IOException, DocumentException {
//		TextField textField = new TextField(writer, new Rectangle(0, 0), name);
//		textField.setVisibility(TextField.HIDDEN);
//		textField.setText(value);
//		PdfFormField formField = textField.getTextField();
//		writer.addAnnotation(formField);
//	}

}