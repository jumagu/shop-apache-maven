package ejbs.shop.logica;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejbs.shop.interfaces.FachadaGenerica;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanLogicaGenerica implements FachadaGenerica {

	@PersistenceContext(unitName = "shopEjb_pu")
	EntityManager entidad;

	@Override
	public List<?> listadoConSubConsultas(String namedQuery) throws Exception {
		List<?> miLista = new ArrayList<>();

		Query miConsulta = entidad.createNamedQuery(namedQuery);
		miLista = miConsulta.getResultList();

//		for (Object obj : miLista) {
//			ImprimirObjeto.completo(obj);
//		}

		return miLista;
	}
}
