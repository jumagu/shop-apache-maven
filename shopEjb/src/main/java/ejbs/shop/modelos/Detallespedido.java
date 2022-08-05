package ejbs.shop.modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the detallespedidos database table.
 * 
 */
@Entity
@Table(name="detallespedidos")
public class Detallespedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="coddetallepedido_dp")
	private Integer coddetallepedidoDp;

	@Column(name="cantidad_dp")
	private Integer cantidadDp;

	@Column(name="impuesto_dp")
	private BigDecimal impuestoDp;

	@Column(name="totalparcial_dp")
	private BigDecimal totalparcialDp;

	@Column(name="valor_dp")
	private BigDecimal valorDp;

	//bi-directional many-to-one association to Pedido
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codpedido_ped")
	private Pedido pedido;

	//bi-directional many-to-one association to Producto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codproducto_pro")
	private Producto producto;

	public Detallespedido() {
	}

	public Integer getCoddetallepedidoDp() {
		return this.coddetallepedidoDp;
	}

	public void setCoddetallepedidoDp(Integer coddetallepedidoDp) {
		this.coddetallepedidoDp = coddetallepedidoDp;
	}

	public Integer getCantidadDp() {
		return this.cantidadDp;
	}

	public void setCantidadDp(Integer cantidadDp) {
		this.cantidadDp = cantidadDp;
	}

	public BigDecimal getImpuestoDp() {
		return this.impuestoDp;
	}

	public void setImpuestoDp(BigDecimal impuestoDp) {
		this.impuestoDp = impuestoDp;
	}

	public BigDecimal getTotalparcialDp() {
		return this.totalparcialDp;
	}

	public void setTotalparcialDp(BigDecimal totalparcialDp) {
		this.totalparcialDp = totalparcialDp;
	}

	public BigDecimal getValorDp() {
		return this.valorDp;
	}

	public void setValorDp(BigDecimal valorDp) {
		this.valorDp = valorDp;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}