package ejbs.shop.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface FachadaGenerica {

	public List<?> listadoConSubConsultas(String namedQuery) throws Exception;

}
