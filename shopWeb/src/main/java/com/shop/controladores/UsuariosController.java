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

import ejbs.shop.modelos.Listasprecio;
import ejbs.shop.modelos.Usuario;
import ejbs.shop.servicios.ServiciosBasicos;

@Named("beanUsuarios")
@SessionScoped
public class UsuariosController implements Serializable {
	private static final long serialVersionUID = 1L;
	private Usuario objUsuario;
	private List<Usuario> listaUsuarios;

	private Integer codigoTemporal;

	@EJB
	ServiciosBasicos<Listasprecio> servicioListasprecios;
	@EJB
	ServiciosBasicos<Usuario> servicioUsuario;

	public UsuariosController() {
		super();
		objUsuario = new Usuario();
		initObject();
	}

	public void initObject() {
		objUsuario = new Usuario();
		codigoTemporal = null;
	}

	@PostConstruct
	public void init() {
		listadoUsuariosPorJPA();
	}

	public Usuario getObjUsuario() {
		return objUsuario;
	}

	public void setObjUsuario(Usuario objUsuario) {
		this.objUsuario = objUsuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Integer getCodigoTemporal() {
		return codigoTemporal;
	}

	public void setCodigoTemporal(Integer codigoTemporal) {
		this.codigoTemporal = codigoTemporal;
	}

	public void listadoUsuariosPorJPA() {
		try {
			listaUsuarios = servicioUsuario.listadoJPA(Usuario.class, "listasprecio");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crearUsuario() {
		try {
			Listasprecio tem = new Listasprecio();
			tem = servicioListasprecios.buscar(Listasprecio.class, codigoTemporal);
			objUsuario.setListasprecio(tem);

			servicioUsuario.crear(objUsuario);
			codigoTemporal = null;
			listadoUsuariosPorJPA();
			objUsuario = new Usuario();

			MostrarMensaje.Pantalla("usuariostxt", "exito", "exitoCrear", "exitoCrearDetalle", "msgGrowl");
		} catch (Exception e) {
			MostrarMensaje.Pantalla("usuariostxt", "error", "errorCrear", "errorCrearDetalle", "msgGrowl");
			e.printStackTrace();
		}
	}

	public List<SelectItem> getComboListaprecio() {
		try {
			List<SelectItem> combo = new ArrayList<>();
			List<Listasprecio> ListaListaprecio = servicioListasprecios.listadoNQUERY(Listasprecio.class, "PORESTADOS",
					"numeroEstado", 1);

			for (Listasprecio laListaPre : ListaListaprecio) {
				SelectItem miItem = new SelectItem(laListaPre.getCodlistaLp(), laListaPre.getNombrelistaLp());
				combo.add(miItem);
			}
			return combo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void actualizarUsuario() {
		try {
			servicioUsuario.actualizar(objUsuario);
			listadoUsuariosPorJPA();

			MostrarMensaje.Pantalla("usuariostxt", "exito", "exitoEditar", "exitoEditarDetalle", "msgGrowl");
		} catch (Exception e) {
			MostrarMensaje.Pantalla("usuariostxt", "error", "errorEditar", "errorEditarDetalle", "msgGrowl");
			e.printStackTrace();
		}
	}

	public void borrarUsuario() {
		try {
			servicioUsuario.borrar(objUsuario);
			listadoUsuariosPorJPA();
			objUsuario = new Usuario();

			MostrarMensaje.Pantalla("usuariostxt", "exito", "exitoBorrar", "exitoBorrarDetalle", "msgGrowl");
		} catch (Exception e) {
			MostrarMensaje.Pantalla("usuariostxt", "error", "errorBorrar", "errorBorrarDetalle", "msgGrowl");
			e.printStackTrace();
		}
	}

	public String selectUsuarioUpdate(Usuario tempo) {
		objUsuario = tempo;

		return "updateUsuario.faces";
	}

	public void selectUsuarioDelete(Usuario tempo) {
		objUsuario = tempo;
	}
}
