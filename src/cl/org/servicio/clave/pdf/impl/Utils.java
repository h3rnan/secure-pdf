package cl.org.servicio.clave.pdf.impl;


public class Utils {

	public static String encodeId(String id){
		String temp = id;
		while (id.length() < 4) {
			id = id+id;
		}
		String hash =  String.valueOf(id.hashCode());
		if (hash.contains("-")) {
			hash = hash.replace("-", "");
		}
		String pref = hash.substring(0, 3);
		String suf = hash.substring(hash.length() - 3, hash.length());
		return temp.equals(id) ? pref+id+suf : pref+temp+suf;
	}

	public static String decodeId(String enc){
		enc = enc.substring(3, enc.length());
		enc = enc.substring(0, enc.length() - 3);
		return enc;
	}

	public static boolean validateId(String enc) {
		String id = decodeId(enc);
		return enc.equals(encodeId(id));
	}

//	public static void main(String[] args) {
//		String id = "7";
//		System.out.println("id :" + id);
//		//String enc = encodeId(id);
//		String enc = "1237890";
//		System.out.println("resultado encode: " + enc);
//		System.out.println("resultado decode: " + decodeId(enc));
//		System.out.println(validateId(enc) ? "Si ese es":"No ese no es");
//	}

}
