package ejbs.shop.utilidades;

public class ImprimirObjeto {

	public static void completo(Object miObjeto) {
		if (miObjeto == null) {
			System.out.print("NULL");
		} else {
			if (miObjeto instanceof Object[]) {
				int i, limite;
				Object[] fila = (Object[]) miObjeto;
				limite = fila.length;

				System.out.println("arreglo[");
				for (i = 0; i < limite; i++) {
					System.out.print(i + "=> ");
					completo(fila[i]);
					System.out.print(", ");
				}
				System.out.println("]");
			} else {
				System.out.print(miObjeto.getClass().getName() + " = " + miObjeto);
			}
		}
	}
}
