package ejbs.shop.modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the precios database table.
 * 
 */
@Entity
@Table(name = "precios")
public class Precio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PrecioPK id;

	@Column(name = "valor_pre")
	private BigDecimal valorPre;

	// bi-directional many-to-one association to Listasprecio
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codlista_lp", insertable = false, updatable = false)
	private Listasprecio listasprecio;

	// bi-directional many-to-one association to Producto
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codproducto_pro", insertable = false, updatable = false)
	private Producto producto;

	public Precio() {
	}

	public PrecioPK getId() {
		return this.id;
	}

	public void setId(PrecioPK id) {
		this.id = id;
	}

	public BigDecimal getValorPre() {
		return this.valorPre;
	}

	public void setValorPre(BigDecimal valorPre) {
		this.valorPre = valorPre;
	}

	public Listasprecio getListasprecio() {
		return this.listasprecio;
	}

	public void setListasprecio(Listasprecio listasprecio) {
		this.listasprecio = listasprecio;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void generarPK() {
		this.id = new PrecioPK();
		this.id.setCodproductoPro(this.producto.getCodproductoPro());
		this.id.setCodlistaLp(this.listasprecio.getCodlistaLp());
	}
}