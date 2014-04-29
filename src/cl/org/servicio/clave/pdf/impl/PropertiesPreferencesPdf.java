package cl.org.servicio.clave.pdf.impl;

import com.itextpdf.text.pdf.PdfWriter;

public class PropertiesPreferencesPdf {

	private boolean allowAssembly;
	private boolean allowCopy;
	private boolean allowDegradedPrinting;
	private boolean allowFillIn;
	private boolean allowModifyAnnotations;
	private boolean allowModifyContents;
	private boolean allowPrinting;
	private boolean allowScreenreaders;
	private boolean hideToolbar;
	private boolean hideMenuBar;
	private boolean signPdf;

	public PropertiesPreferencesPdf() {
		// TODO Auto-generated constructor stub
	}

	public  int configViewerPreferences() {
		int pref = 0;
		if (hideMenuBar) pref = pref | PdfWriter.HideMenubar;
		if (hideToolbar) pref = pref | PdfWriter.HideToolbar;
		return pref;
	}

	public  int configPermission() {
		int pref = 0;
		if (allowAssembly) 			pref = pref | PdfWriter.ALLOW_ASSEMBLY;
		if (allowCopy) 				pref = pref | PdfWriter.ALLOW_COPY;
		if (allowDegradedPrinting)	pref = pref | PdfWriter.ALLOW_DEGRADED_PRINTING;
		if (allowFillIn) 			pref = pref | PdfWriter.ALLOW_FILL_IN;
		if (allowModifyAnnotations) pref = pref | PdfWriter.ALLOW_MODIFY_ANNOTATIONS;
		if (allowModifyContents) 	pref = pref | PdfWriter.ALLOW_MODIFY_CONTENTS;
		if (allowPrinting) 			pref = pref | PdfWriter.ALLOW_PRINTING;
		if (allowScreenreaders) 	pref = pref | PdfWriter.ALLOW_SCREENREADERS;
		return ~pref;
	}

	public boolean isAllowAssembly() {
		return allowAssembly;
	}

	public void setAllowAssembly(boolean allowAssembly) {
		this.allowAssembly = allowAssembly;
	}

	public boolean isAllowCopy() {
		return allowCopy;
	}

	public void setAllowCopy(boolean allowCopy) {
		this.allowCopy = allowCopy;
	}

	public boolean isAllowDegradedPrinting() {
		return allowDegradedPrinting;
	}

	public void setAllowDegradedPrinting(boolean allowDegradedPrinting) {
		this.allowDegradedPrinting = allowDegradedPrinting;
	}

	public boolean isAllowFillIn() {
		return allowFillIn;
	}

	public void setAllowFillIn(boolean allowFillIn) {
		this.allowFillIn = allowFillIn;
	}

	public boolean isAllowModifyAnnotations() {
		return allowModifyAnnotations;
	}

	public void setAllowModifyAnnotations(boolean allowModifyAnnotations) {
		this.allowModifyAnnotations = allowModifyAnnotations;
	}

	public boolean isAllowModifyContents() {
		return allowModifyContents;
	}

	public void setAllowModifyContents(boolean allowModifyContents) {
		this.allowModifyContents = allowModifyContents;
	}

	public boolean isAllowPrinting() {
		return allowPrinting;
	}

	public void setAllowPrinting(boolean allowPrinting) {
		this.allowPrinting = allowPrinting;
	}

	public boolean isAllowScreenreaders() {
		return allowScreenreaders;
	}

	public void setAllowScreenreaders(boolean allowScreenreaders) {
		this.allowScreenreaders = allowScreenreaders;
	}

	public boolean isHideToolbar() {
		return hideToolbar;
	}

	public void setHideToolbar(boolean hideToolbar) {
		this.hideToolbar = hideToolbar;
	}

	public boolean isHideMenuBar() {
		return hideMenuBar;
	}

	public void setHideMenuBar(boolean hideMenuBar) {
		this.hideMenuBar = hideMenuBar;
	}

	public boolean isSignPdf() {
		return signPdf;
	}

	public void setSignPdf(boolean signPdf) {
		this.signPdf = signPdf;
	}

}
