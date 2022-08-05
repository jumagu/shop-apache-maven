package ejbs.shop.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface FachadaJPA<T> {
	public void crear(T tipoEntidad) throws Exception;

	public boolean actualizar(T tipoEntidad) throws Exception;

	public void borrar(T tipoEntidad) throws Exception;

	public List<T> listadoJPA(Class<T> tipoEntidad) throws Exception;

	public List<T> listadoJPA(Class<T> tipoEntidad, String propiedad) throws Exception;

	public List<T> listadoNQUERY(Class<T> tipoEntidad) throws Exception;

	public List<T> listadoNQUERY(Class<T> tipoEntidad, String namedQuery, String columna, Integer valor)
			throws Exception;

	public T buscar(Class<T> tipoEntidad, Integer identificador) throws Exception;

	public List<T> buscarCampo(Class<T> tipoEntidad, String nombrePropiedad, String texto) throws Exception;

	public List<T> buscarCampo(Class<T> tipoEntidad, String nombrePropiedad, Integer cod, String propiedad) throws Exception;
}
