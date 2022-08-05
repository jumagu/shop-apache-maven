package com.shop.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.shop.utilidades.MostrarMensaje;

import ejbs.shop.modelos.Categoria;
import ejbs.shop.modelos.Producto;
import ejbs.shop.servicios.ServiciosBasicos;

@Named("beanProducto")
@SessionScoped
public class ProductosController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Producto objProducto;
	private List<Producto> listaProductos;

	private Integer codigoTemporal;

	@EJB
	ServiciosBasicos<Producto> servicioProductos;
	@EJB
	ServiciosBasicos<Categoria> servicioCategorias;

	public ProductosController() throws Exception {
		super();
		objProducto = new Producto();
		initObject();
	}

	public void initObject() throws Exception {
		objProducto = new Producto();
		codigoTemporal = null;
	}

	@PostConstruct
	public void init() {
		listadoProductosNamedQuery();
	}

	public Producto getObjProducto() {
		return objProducto;
	}

	public void setObjProducto(Producto objProducto) {
		this.objProducto = objProducto;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public Integer getCodigoTemporal() {
		return codigoTemporal;
	}

	public void setCodigoTemporal(Integer codigoTemporal) {
		this.codigoTemporal = codigoTemporal;
	}

	public void crearProducto() {
		try {
			Categoria tem = new Categoria();
			tem = servicioCategorias.buscar(Categoria.class, codigoTemporal);
			objProducto.setCategoria(tem);

			servicioProductos.crear(objProducto);
			codigoTemporal = null;

			listadoProductosNamedQuery();
			objProducto = new Producto();

			MostrarMensaje.Pantalla("productostxt", "exito", "exitoCrear", "exitoCrearDetalle", "msgGrowl");
		} catch (Exception e) {

			MostrarMensaje.Pantalla("productostxt", "error", "errorCrear", "errorCrearDetalle", "msgGrowl");
			e.printStackTrace();
		}
	}

	public List<SelectItem> getComboCategoria() {

		try {
			List<SelectItem> combo = new ArrayList<>();
			List<Categoria> ListaCategorias = servicioCategorias.listadoNQUERY(Categoria.class, "PORESTADOS",
					"numeroEstado", 1);

			for (Categoria laCatego : ListaCategorias) {
				SelectItem miItem = new SelectItem(laCatego.getCodcategoriaCat(), laCatego.getNombrecategoriaCat());
				combo.add(miItem);
			}
			return combo;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void listadoProductosPorJPA() {
		try {
			listaProductos = servicioProductos.listadoJPA(Producto.class, "categoria");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listadoProductosNamedQuery() {
		try {
			listaProductos = servicioProductos.listadoNQUERY(Producto.class, "DEMEPRODUCTOS", "", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String seleccionarProActualizar(Producto tempo) {
		codigoTemporal = tempo.getCategoria().getCodcategoriaCat();
		objProducto = tempo;

		return "updateProductos.faces";
	}

	public void seleccionarProBorrar(Producto tempo) {
		objProducto = tempo;
	}

	public void btnBorrarProducto() {
		try {
			servicioProductos.borrar(objProducto);
			listadoProductosNamedQuery();
			objProducto = new Producto();

			MostrarMensaje.Pantalla("productostxt", "exito", "exitoBorrar", "exitoBorrarDetalle", "msgGrowl");
		} catch (Exception e) {
			MostrarMensaje.Pantalla("productostxt", "error", "errorBorrar", "errorBorrarDetalle", "msgGrowl");
			e.printStackTrace();
		}
	}

	public void btnActualizarProducto() {
		try {
			Categoria tem = new Categoria();
			tem = servicioCategorias.buscar(Categoria.class, codigoTemporal);
			objProducto.setCategoria(tem);

			servicioProductos.actualizar(objProducto);
			codigoTemporal = null;
			listadoProductosNamedQuery();

			MostrarMensaje.Pantalla("productostxt", "exito", "exitoEditar", "exitoEditarDetalle", "msgGrowl");
		} catch (Exception e) {
			MostrarMensaje.Pantalla("productostxt", "error", "errorEditar", "errorEditarDetalle", "msgGrowl");
			e.printStackTrace();
		}
	}
}