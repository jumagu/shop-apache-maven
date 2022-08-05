package com.shop.utilidades;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class Formulario {

	private static List<SelectItem> comboFinal;

	private static void opcionPorDefecto(String titulo) {
		if (titulo != null && !titulo.isEmpty()) {
			comboFinal.add(new SelectItem(null, titulo));
		}
	}

	private static void todasLasOpciones(List<?> miLista, String cod, String texto) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (miLista != null) {
			for (Object obj : miLista) {
				Class<?> classObj = obj.getClass();
				Method metodoCod = classObj.getDeclaredMethod(cod);
				Method metodoVal = classObj.getDeclaredMethod(texto);

				Integer codigo = (Integer) metodoCod.invoke(obj);
				String mostrar = (String) metodoVal.invoke(obj);

				comboFinal.add(new SelectItem(codigo, mostrar));
			}
		}
	}

	public static List<SelectItem> armarCombo(List<?> miLista, String titulo, String metodoCodigo, String metodoTexto)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		comboFinal = new ArrayList<>();
		opcionPorDefecto(titulo);
		todasLasOpciones(miLista, metodoCodigo, metodoTexto);
		return comboFinal;
	}
}
