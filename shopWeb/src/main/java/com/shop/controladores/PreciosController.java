package com.shop.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import com.shop.utilidades.Formulario;
import com.shop.utilidades.MostrarMensaje;
import com.shop.utilidades.Propiedades;

import ejbs.shop.modelos.Listasprecio;
import ejbs.shop.modelos.Precio;
import ejbs.shop.modelos.Producto;
import ejbs.shop.servicios.ServiciosBasicos;

@Named("beanPrecios")
@SessionScoped
public class PreciosController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Precio objPrecio;
	private Integer idComboProducto;
	private Integer idComboListasPrecio;
	private List<SelectItem> listaPreciosCargada;

	@Inject
	ProductosController productoController;
	@EJB
	ServiciosBasicos<Precio> servicioPrecio;
	@EJB
	ServiciosBasicos<Producto> servicioProducto;
	@EJB
	ServiciosBasicos<Listasprecio> ServicioLp;

	public PreciosController() throws Exception {
		super();
		objPrecio = new Precio();
		initObject();
	}

	public void initObject() throws Exception {
		objPrecio = new Precio();
		idComboProducto = null;
		idComboListasPrecio = null;
		getComboLpVacia();
	}

	@PostConstruct
	public void init() throws Exception {
		getComboLpVacia();
	}

	public Precio getObjPrecio() {
		return objPrecio;
	}

	public void setObjPrecio(Precio objPrecio) {
		this.objPrecio = objPrecio;
	}

	public Integer getIdComboProducto() {
		return idComboProducto;
	}

	public void setIdComboProducto(Integer idComboProducto) {
		this.idComboProducto = idComboProducto;
	}

	public Integer getIdComboListasPrecio() {
		return idComboListasPrecio;
	}

	public void setIdComboListasPrecio(Integer idComboListasPrecio) {
		this.idComboListasPrecio = idComboListasPrecio;
	}

	public List<SelectItem> getListaPreciosCargada() {
		return listaPreciosCargada;
	}

	public void setListaPreciosCargada(List<SelectItem> listaPreciosCargada) {
		this.listaPreciosCargada = listaPreciosCargada;
	}

	public List<SelectItem> getComboProducto() throws Exception {
		String mensajeVacio = Propiedades.obtener("/preciostxt", "nombreProductoVacio");

		List<Producto> listaPro = servicioProducto.listadoJPA(Producto.class);

		return Formulario.armarCombo(listaPro, mensajeVacio, "getCodproductoPro", "getNombreproductoPro");
	}

	public void getComboLp() throws Exception {
		String mensajeVacio = Propiedades.obtener("/preciostxt", "nombrePrecioVacio");

		List<Listasprecio> listaPrecio = ServicioLp.listadoNQUERY(Listasprecio.class, "DISPONIBLE_POR_PRODUCTO",
				"codPro", idComboProducto);

		listaPreciosCargada = Formulario.armarCombo(listaPrecio, mensajeVacio, "getCodlistaLp", "getNombrelistaLp");
	}

	public List<SelectItem> getComboLpDos() throws Exception {
		String mensajeVacio = Propiedades.obtener("/preciostxt", "nombrePrecioVacio");

		List<Listasprecio> listaPrecio = ServicioLp.listadoNQUERY(Listasprecio.class, "DISPONIBLE_POR_PRODUCTO",
				"codPro", productoController.getObjProducto().getCodproductoPro());

		return Formulario.armarCombo(listaPrecio, mensajeVacio, "getCodlistaLp", "getNombrelistaLp");
	}

	public void getComboLpVacia() throws Exception {
		String mensajeVacio = Propiedades.obtener("/preciostxt", "nombrePrecioVacio");
		listaPreciosCargada = Formulario.armarCombo(null, mensajeVacio, "getCodlistaLp", "getNombrelistaLp");
	}

	public void crearPrecio() {
		try {

			Listasprecio lpTempo = new Listasprecio();
			lpTempo = ServicioLp.buscar(Listasprecio.class, idComboListasPrecio);

			objPrecio.setProducto(productoController.getObjProducto());
			objPrecio.setListasprecio(lpTempo);

			objPrecio.generarPK();

			servicioPrecio.crear(objPrecio);

			idComboProducto = null;
			idComboListasPrecio = null;
			objPrecio = new Precio();

			MostrarMensaje.Pantalla("preciostxt", "exito", "exitoCrear", "exitoCrearDetalle", "msgGrowl");
		} catch (Exception e) {
			MostrarMensaje.Pantalla("preciostxt", "error", "errorCrear", "errorCrearDetalle", "msgGrowl");
			e.printStackTrace();
		}
	}

	public List<Precio> getListaPreciosPorProducto() throws Exception {

		List<Precio> miListaPre = new ArrayList<>();
		miListaPre = servicioPrecio.buscarCampo(Precio.class, "producto",
				productoController.getObjProducto().getCodproductoPro(), "listasprecio");

		return miListaPre;
	}

}
