package ejbs.shop.logica;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ejbs.shop.interfaces.FachadaJPA;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanLogica<T> implements FachadaJPA<T> {

	@PersistenceContext(unitName = "shopEjb_pu")
	EntityManager entidad;

	@Override
	public void crear(T tipoEntidad) throws Exception {
		entidad.persist(tipoEntidad);

	}

	@Override
	public boolean actualizar(T tipoEntidad) throws Exception {
		boolean respuesta;
		try {
			entidad.merge(tipoEntidad);
			respuesta = true;
		} catch (Exception e) {
			respuesta = false;
		}
		return respuesta;
	}

	@Override
	public void borrar(T tipoEntidad) throws Exception {
		entidad.remove(entidad.contains(tipoEntidad) ? tipoEntidad : entidad.merge(tipoEntidad));
	}

	@Override
	public List<T> listadoJPA(Class<T> tipoEntidad) throws Exception {
		CriteriaBuilder cb = entidad.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(tipoEntidad);

		cq.select(cq.from(tipoEntidad));

		return entidad.createQuery(cq).getResultList();
	}

	@Override
	public List<T> listadoJPA(Class<T> tipoEntidad, String propiedad) throws Exception {
		CriteriaBuilder cb = entidad.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(tipoEntidad);

		Root<T> obj = cq.from(tipoEntidad);
		obj.fetch(propiedad, JoinType.INNER);

		cq.select(obj);

		return entidad.createQuery(cq).getResultList();
	}

	@Override
	public List<T> listadoNQUERY(Class<T> tipoEntidad) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T buscar(Class<T> tipoEntidad, Integer identificador) throws Exception {
		T temporal = entidad.find(tipoEntidad, identificador);
		return temporal;
	}

	@Override
	public List<T> listadoNQUERY(Class<T> tipoEntidad, String namedQuery, String columna, Integer valor)
			throws Exception {
		List<T> miLista = new ArrayList<>();
		String myNamedQuery = tipoEntidad.getSimpleName().concat(".").concat(namedQuery);

		TypedQuery<T> consulta = entidad.createNamedQuery(myNamedQuery, tipoEntidad);
		if (!columna.equals("")) {
			consulta.setParameter(columna, valor);
		}

		miLista = consulta.getResultList();

		return miLista;
	}

	@Override
	public List<T> buscarCampo(Class<T> tipoEntidad, String nombrePropiedad, String texto) throws Exception {
		List<T> miLista = new ArrayList<>();
		CriteriaBuilder cb = entidad.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(tipoEntidad);

		Root<T> obj = cq.from(tipoEntidad);

		if (!texto.equals("")) {
			// String cadena = "%" + texto.toLowerCase() + "%";
			String cadena = texto.toLowerCase().trim();
			Expression<String> campo = obj.get(nombrePropiedad);
			campo = cb.lower(campo);
			// Predicate laCondicion = cb.like(campo, cadena);
			Predicate laCondicion = cb.equal(campo, cadena);
			cq.where(laCondicion);
		}

		TypedQuery<T> consulta = entidad.createQuery(cq);
		miLista = consulta.getResultList();

		return miLista;
	}

	@Override
	public List<T> buscarCampo(Class<T> tipoEntidad, String nombrePropiedad, Integer cod, String propiedad)
			throws Exception {

		List<T> miLista = new ArrayList<>();
		CriteriaBuilder cb = entidad.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(tipoEntidad);

		Root<T> obj = cq.from(tipoEntidad);

		Expression<Integer> campo = obj.get(nombrePropiedad);
		Predicate laCondicion = cb.equal(campo, cod);
		cq.where(laCondicion);

		obj.fetch(propiedad, JoinType.INNER);

		TypedQuery<T> consulta = entidad.createQuery(cq);
		miLista = consulta.getResultList();

		return miLista;
	}
}
