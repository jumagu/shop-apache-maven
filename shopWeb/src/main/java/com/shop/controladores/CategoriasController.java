package com.shop.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.shop.utilidades.MostrarMensaje;

import ejbs.shop.modelos.Categoria;
import ejbs.shop.servicios.ServiciosBasicos;
import ejbs.shop.servicios.ServiciosGenericos;

@Named("beanCategoria")
@SessionScoped
public class CategoriasController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Categoria objCategoria;
	private List<Categoria> listaCategorias;
	private List<?> listaCategoriaGenerica;

	@EJB
	ServiciosBasicos<Categoria> servicioCategoria;
	@EJB
	ServiciosGenericos servicioGenericoCategoria;

	public void initObject() {
		objCategoria = new Categoria();
	}

	@PostConstruct
	public void init() {
		listadoCategoriasPorJPA();
	}

	public Categoria getObjCategoria() {
		return objCategoria;
	}

	public void setObjCategoria(Categoria objCategoria) {
		this.objCategoria = objCategoria;
	}

	public List<Categoria> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<Categoria> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public List<?> getListaCategoriaGenerica() {
		return listaCategoriaGenerica;
	}

	public void setListaCategoriaGenerica(List<?> listaCategoriaGenerica) {
		this.listaCategoriaGenerica = listaCategoriaGenerica;
	}

	public CategoriasController() {
		super();
		objCategoria = new Categoria();
	}

	public List<Integer> getListaCategoriasCod() {
		int aux;
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < listaCategorias.size(); i++) {
			aux = listaCategorias.get(i).getCodcategoriaCat();
			list.add(aux);
		}
		return list;
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

	public void crearCategoria() {
		try {

			List<Categoria> miLista = new ArrayList<>();
			miLista = servicioCategoria.buscarCampo(Categoria.class, "nombrecategoriaCat",
					objCategoria.getNombrecategoriaCat());
			if (miLista.size() == 0) {
				servicioCategoria.crear(objCategoria);
				objCategoria = new Categoria();
				listadoCategoriasPorJPA();

				MostrarMensaje.Pantalla("categoriastxt", "exito", "exitoCrear", "exitoCrearDetalle", "msgGrowl");
			} else {
				for (Categoria miObjCategoria : miLista) {
					System.out.println(miObjCategoria.getNombrecategoriaCat());
				}
				MostrarMensaje.Pantalla("categoriastxt", "adveretencia", "advertenciaCrear", "advertenciaCrearDetalle",
						"msgGrowl");
			}

		} catch (Exception e) {
			MostrarMensaje.Pantalla("categoriastxt", "fatal", "errorCrear", "errorCrearDetalle", "msgGrowl");
			e.printStackTrace();
		}
	}

	public void actualizarCategoria() {
		try {
			servicioCategoria.actualizar(objCategoria);
			listadoCategoriasPorJPA();
			objCategoria = new Categoria();

			MostrarMensaje.Pantalla("categoriastxt", "exito", "exitoEditar", "exitoEditarDetalle", "msgGrowl");

		} catch (Exception e) {
			MostrarMensaje.Pantalla("categoriastxt", "error", "errorEditar", "errorEditarDetalle", "msgGrowl");
			e.printStackTrace();
		}
	}

	public void borrarCategoria() {
		try {
			servicioCategoria.borrar(objCategoria);
			listadoCategoriasPorJPA();
			objCategoria = new Categoria();

			MostrarMensaje.Pantalla("categoriastxt", "exito", "exitoBorrar", "exitoBorrarDetalle", "msgGrowl");

		} catch (Exception e) {
			MostrarMensaje.Pantalla("categoriastxt", "error", "errorBorrar", "errorBorrarDetalle", "msgGrowl");
			e.printStackTrace();
		}
	}

	public String seleccionarCategoActualizar(Categoria tempo) {
		objCategoria = tempo;

		return "updateCategoria.faces";
	}

	public void seleccionarCategoBorrar(Categoria tempo) {
		objCategoria = tempo;
	}

	public void listadoCategoriasPorJPA() {
		try {
			listaCategorias = servicioCategoria.listadoJPA(Categoria.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<?> listarCategoriasCantidadProductos() throws Exception {
		listaCategoriaGenerica = servicioGenericoCategoria.listadoConSubConsultas("Categoria.CONCANTIDADPRODUCTOS");

		return listaCategoriaGenerica;
	}

	public void resetearObjeto() {
		objCategoria = new Categoria();
	}
}