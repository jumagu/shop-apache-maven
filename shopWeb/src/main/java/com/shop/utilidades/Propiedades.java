package com.shop.utilidades;

import java.util.ResourceBundle;

public class Propiedades {
	public static String obtener(String archivo, String llave) {
		return ResourceBundle.getBundle(archivo).getString(llave);
	}
}
