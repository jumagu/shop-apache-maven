package ejbs.shop.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ejbs.shop.interfaces.FachadaGenerica;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ServiciosGenericos {

	@EJB
	FachadaGenerica logicaGenerica;

	public List<?> listadoConSubConsultas(String namedQuery) throws Exception {
		return logicaGenerica.listadoConSubConsultas(namedQuery);
	}
}
