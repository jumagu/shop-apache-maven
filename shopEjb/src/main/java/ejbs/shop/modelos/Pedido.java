package ejbs.shop.modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pedidos database table.
 * 
 */
@Entity
@Table(name="pedidos")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codpedido_ped")
	private Integer codpedidoPed;

	@Column(name="cliente_ped")
	private String clientePed;

	@Column(name="correo_ped")
	private String correoPed;

	@Column(name="despachado_ped")
	private Integer despachadoPed;

	@Column(name="direccion_ped")
	private String direccionPed;

	@Column(name="documento_ped")
	private String documentoPed;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ped")
	private Date fechaPed;

	@Column(name="hora_ped")
	private Time horaPed;

	@Column(name="identficador_ped")
	private String identficadorPed;

	@Column(name="telefono_ped")
	private String telefonoPed;

	//bi-directional many-to-one association to Detallespedido
	@OneToMany(mappedBy="pedido")
	private List<Detallespedido> detallespedidos;

	public Pedido() {
	}

	public Integer getCodpedidoPed() {
		return this.codpedidoPed;
	}

	public void setCodpedidoPed(Integer codpedidoPed) {
		this.codpedidoPed = codpedidoPed;
	}

	public String getClientePed() {
		return this.clientePed;
	}

	public void setClientePed(String clientePed) {
		this.clientePed = clientePed;
	}

	public String getCorreoPed() {
		return this.correoPed;
	}

	public void setCorreoPed(String correoPed) {
		this.correoPed = correoPed;
	}

	public Integer getDespachadoPed() {
		return this.despachadoPed;
	}

	public void setDespachadoPed(Integer despachadoPed) {
		this.despachadoPed = despachadoPed;
	}

	public String getDireccionPed() {
		return this.direccionPed;
	}

	public void setDireccionPed(String direccionPed) {
		this.direccionPed = direccionPed;
	}

	public String getDocumentoPed() {
		return this.documentoPed;
	}

	public void setDocumentoPed(String documentoPed) {
		this.documentoPed = documentoPed;
	}

	public Date getFechaPed() {
		return this.fechaPed;
	}

	public void setFechaPed(Date fechaPed) {
		this.fechaPed = fechaPed;
	}

	public Time getHoraPed() {
		return this.horaPed;
	}

	public void setHoraPed(Time horaPed) {
		this.horaPed = horaPed;
	}

	public String getIdentficadorPed() {
		return this.identficadorPed;
	}

	public void setIdentficadorPed(String identficadorPed) {
		this.identficadorPed = identficadorPed;
	}

	public String getTelefonoPed() {
		return this.telefonoPed;
	}

	public void setTelefonoPed(String telefonoPed) {
		this.telefonoPed = telefonoPed;
	}

	public List<Detallespedido> getDetallespedidos() {
		return this.detallespedidos;
	}

	public void setDetallespedidos(List<Detallespedido> detallespedidos) {
		this.detallespedidos = detallespedidos;
	}

	public Detallespedido addDetallespedido(Detallespedido detallespedido) {
		getDetallespedidos().add(detallespedido);
		detallespedido.setPedido(this);

		return detallespedido;
	}

	public Detallespedido removeDetallespedido(Detallespedido detallespedido) {
		getDetallespedidos().remove(detallespedido);
		detallespedido.setPedido(null);

		return detallespedido;
	}

}