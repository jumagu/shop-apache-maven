package ejbs.shop.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ejbs.shop.interfaces.FachadaJPA;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ServiciosBasicos<T> {

	@EJB
	FachadaJPA<T> logica;

	public void crear(T tipoEntidad) throws Exception {
		logica.crear(tipoEntidad);
	}

	public boolean actualizar(T tipoEntidad) throws Exception {
		return logica.actualizar(tipoEntidad);
	}

	public void borrar(T tipoEntidad) throws Exception {
		logica.borrar(tipoEntidad);
	}

	public List<T> listadoJPA(Class<T> tipoEntidad) throws Exception {
		return logica.listadoJPA(tipoEntidad);
	}

	public List<T> listadoNQUERY(Class<T> tipoEntidad) throws Exception {
		return logica.listadoNQUERY(tipoEntidad);
	}

	public T buscar(Class<T> tipoEntidad, Integer identificador) throws Exception {
		return logica.buscar(tipoEntidad, identificador);
	}

	public List<T> listadoJPA(Class<T> tipoEntidad, String propiedad) throws Exception {
		return logica.listadoJPA(tipoEntidad, propiedad);
	}

	public List<T> listadoNQUERY(Class<T> tipoEntidad, String namedQuery, String columna, Integer valor)
			throws Exception {
		return logica.listadoNQUERY(tipoEntidad, namedQuery, columna, valor);
	}

	public List<T> buscarCampo(Class<T> tipoEntidad, String nombrePropiedad, String texto) throws Exception {
		return logica.buscarCampo(tipoEntidad, nombrePropiedad, texto);
	}

	public List<T> buscarCampo(Class<T> tipoEntidad, String nombrePropiedad, Integer cod, String propiedad)
			throws Exception {
		return logica.buscarCampo(tipoEntidad, nombrePropiedad, cod, propiedad);
	}
}
