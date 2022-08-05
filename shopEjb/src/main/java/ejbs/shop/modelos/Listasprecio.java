package ejbs.shop.modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the listasprecios database table.
 * 
 */
@Entity
@Table(name="listasprecios")
public class Listasprecio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codlista_lp")
	private Integer codlistaLp;

	@Column(name="estado_lp")
	private Integer estadoLp;

	@Column(name="nombrelista_lp")
	private String nombrelistaLp;

	//bi-directional many-to-one association to Precio
	@OneToMany(mappedBy="listasprecio")
	private List<Precio> precios;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="listasprecio")
	private List<Usuario> usuarios;

	public Listasprecio() {
	}

	public Integer getCodlistaLp() {
		return this.codlistaLp;
	}

	public void setCodlistaLp(Integer codlistaLp) {
		this.codlistaLp = codlistaLp;
	}

	public Integer getEstadoLp() {
		return this.estadoLp;
	}

	public void setEstadoLp(Integer estadoLp) {
		this.estadoLp = estadoLp;
	}

	public String getNombrelistaLp() {
		return this.nombrelistaLp;
	}

	public void setNombrelistaLp(String nombrelistaLp) {
		this.nombrelistaLp = nombrelistaLp;
	}

	public List<Precio> getPrecios() {
		return this.precios;
	}

	public void setPrecios(List<Precio> precios) {
		this.precios = precios;
	}

	public Precio addPrecio(Precio precio) {
		getPrecios().add(precio);
		precio.setListasprecio(this);

		return precio;
	}

	public Precio removePrecio(Precio precio) {
		getPrecios().remove(precio);
		precio.setListasprecio(null);

		return precio;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setListasprecio(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setListasprecio(null);

		return usuario;
	}

}