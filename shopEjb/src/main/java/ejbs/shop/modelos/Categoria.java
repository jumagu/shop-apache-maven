package ejbs.shop.modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categorias database table.
 * 
 */
@Entity
@Table(name="categorias")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codcategoria_cat")
	private Integer codcategoriaCat;

	@Column(name="estado_cat")
	private Integer estadoCat;

	@Column(name="nombrecategoria_cat")
	private String nombrecategoriaCat;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="categoria")
	private List<Producto> productos;

	public Categoria() {
	}

	public Integer getCodcategoriaCat() {
		return this.codcategoriaCat;
	}

	public void setCodcategoriaCat(Integer codcategoriaCat) {
		this.codcategoriaCat = codcategoriaCat;
	}

	public Integer getEstadoCat() {
		return this.estadoCat;
	}

	public void setEstadoCat(Integer estadoCat) {
		this.estadoCat = estadoCat;
	}

	public String getNombrecategoriaCat() {
		return this.nombrecategoriaCat;
	}

	public void setNombrecategoriaCat(String nombrecategoriaCat) {
		this.nombrecategoriaCat = nombrecategoriaCat;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setCategoria(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setCategoria(null);

		return producto;
	}

}