package com.shop.utilidades;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MostrarMensaje {
	private static void Construir(Severity tipo, String titulo, String detalle, String id) {
		if (id.equals("")) {
			id = null;
		}
		FacesMessage mensaje = new FacesMessage(tipo, titulo, detalle);
		FacesContext.getCurrentInstance().addMessage(id, mensaje);
	}

	private static void Error(String titulo, String detalle, String id) {
		MostrarMensaje.Construir(FacesMessage.SEVERITY_ERROR, titulo, detalle, id);
	}

	private static void Exito(String titulo, String detalle, String id) {
		MostrarMensaje.Construir(FacesMessage.SEVERITY_INFO, titulo, detalle, id);
	}

	private static void Advertencia(String titulo, String detalle, String id) {
		MostrarMensaje.Construir(FacesMessage.SEVERITY_WARN, titulo, detalle, id);
	}

	private static void Fatal(String titulo, String detalle, String id) {
		MostrarMensaje.Construir(FacesMessage.SEVERITY_FATAL, titulo, detalle, id);
	}

	public static void Pantalla(String archivoPropiedades, String tipo, String titulo, String detalle, String id) {
		String tituloFinal = ResourceBundle.getBundle(archivoPropiedades).getString(titulo);
		String detalleFinal = ResourceBundle.getBundle(archivoPropiedades).getString(detalle);

		switch (tipo) {
		case "exito":
			MostrarMensaje.Exito(tituloFinal, detalleFinal, id);
			break;
		case "error":
			MostrarMensaje.Error(tituloFinal, detalleFinal, id);
			break;
		case "advertencia":
			MostrarMensaje.Advertencia(tituloFinal, detalleFinal, id);
			break;

		default:
			MostrarMensaje.Fatal(tituloFinal, detalleFinal, id);
			break;
		}
	}
}
