package com.shop.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.shop.utilidades.MostrarMensaje;

import ejbs.shop.modelos.Listasprecio;
import ejbs.shop.servicios.ServiciosBasicos;
import ejbs.shop.servicios.ServiciosGenericos;

@Named("beanListasprecios")
@SessionScoped
public class ListaspreciosController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Listasprecio objListasprecios;
	private List<Listasprecio> listaListasprecios;
	private List<?> listasPrecioGenerica;

	@EJB
	ServiciosBasicos<Listasprecio> servicioListasprecios;

	@EJB
	ServiciosGenericos servicioListasPrecioGenerico;

	public ListaspreciosController() {
		super();
		objListasprecios = new Listasprecio();
		initObject();
	}

	public void initObject() {
		objListasprecios = new Listasprecio();
	}

	@PostConstruct
	public void init() {
		listadoListaspreciosPorJPA();
	}

	public Listasprecio getObjListasprecios() {
		return objListasprecios;
	}

	public void setObjListasprecios(Listasprecio objListasprecios) {
		this.objListasprecios = objListasprecios;
	}

	public List<Listasprecio> getListaListasprecios() {
		return listaListasprecios;
	}

	public void setListaListasprecios(List<Listasprecio> listaListasprecios) {
		this.listaListasprecios = listaListasprecios;
	}

	public void listadoListaspreciosPorJPA() {
		try {
			listaListasprecios = servicioListasprecios.listadoJPA(Listasprecio.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getEstadoNombre(Integer id) {
		String nombre = "";

		switch (id) {
		case 1:
			nombre = "Activo";
			break;

		case 2:
			nombre = "Inactivo";
			break;
		}
		return nombre;
	}

	public void crearListaprecios() {
		try {
			servicioListasprecios.crear(objListasprecios);
			listadoListaspreciosPorJPA();
			objListasprecios = new Listasprecio();

			MostrarMensaje.Pantalla("listaspreciostxt", "exito", "exitoCrear", "exitoCrearDetalle", "msgGrowl");
		} catch (Exception e) {
			MostrarMensaje.Pantalla("listaspreciostxt", "fatal", "errorCrear", "errorCrearDetalle", "msgGrowl");
			e.printStackTrace();
		}
	}

	public void actualizarListaprecios() {
		try {
			servicioListasprecios.actualizar(objListasprecios);
			listadoListaspreciosPorJPA();
			objListasprecios = new Listasprecio();

			MostrarMensaje.Pantalla("listaspreciostxt", "exito", "exitoEditar", "exitoEditarDetalle", "msgGrowl");
		} catch (Exception e) {
			MostrarMensaje.Pantalla("listaspreciostxt", "error", "errorEditar", "errorEditarDetalle", "msgGrowl");
			e.printStackTrace();
		}
	}

	public void borrarListaprecios() {
		try {
			servicioListasprecios.borrar(objListasprecios);
			listadoListaspreciosPorJPA();
			objListasprecios = new Listasprecio();

			MostrarMensaje.Pantalla("listaspreciostxt", "exito", "exitoBorrar", "exitoBorrarDetalle", "msgGrowl");
		} catch (Exception e) {
			MostrarMensaje.Pantalla("listaspreciostxt", "error", "errorBorrar", "errorBorrarDetalle", "msgGrowl");
			e.printStackTrace();
		}
	}

	public String selectListaprecioUpdate(Listasprecio tempo) {
		objListasprecios = tempo;

		return "updateListaprecios.faces";
	}

	public void selectListaprecioDelete(Listasprecio tempo) {
		objListasprecios = tempo;
	}

	public List<?> listarLpCantidadUsuarios() throws Exception {
		listasPrecioGenerica = servicioListasPrecioGenerico.listadoConSubConsultas("Listasprecio.CONCANTIDADUSUARIOS");

		return listasPrecioGenerica;
	}
}
