package ejbs.shop.modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codusuario_usu")
	private Integer codusuarioUsu;

	@Column(name="clave_usu")
	private String claveUsu;

	@Column(name="correo_usu")
	private String correoUsu;

	@Column(name="direccion_usu")
	private String direccionUsu;

	@Column(name="documento_usu")
	private String documentoUsu;

	@Temporal(TemporalType.DATE)
	@Column(name="fechanac_usu")
	private Date fechanacUsu;

	@Column(name="genero_usu")
	private Integer generoUsu;

	@Column(name="nombres_usu")
	private String nombresUsu;

	@Column(name="primerapellido_usu")
	private String primerapellidoUsu;

	@Column(name="segundoapellido_usu")
	private String segundoapellidoUsu;

	@Column(name="telefono_usu")
	private String telefonoUsu;

	@Column(name="tipodocumento_usu")
	private String tipodocumentoUsu;

	@Column(name="whatsapp_usu")
	private String whatsappUsu;

	//bi-directional many-to-one association to Listasprecio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codlista_lp")
	private Listasprecio listasprecio;

	public Usuario() {
	}

	public Integer getCodusuarioUsu() {
		return this.codusuarioUsu;
	}

	public void setCodusuarioUsu(Integer codusuarioUsu) {
		this.codusuarioUsu = codusuarioUsu;
	}

	public String getClaveUsu() {
		return this.claveUsu;
	}

	public void setClaveUsu(String claveUsu) {
		this.claveUsu = claveUsu;
	}

	public String getCorreoUsu() {
		return this.correoUsu;
	}

	public void setCorreoUsu(String correoUsu) {
		this.correoUsu = correoUsu;
	}

	public String getDireccionUsu() {
		return this.direccionUsu;
	}

	public void setDireccionUsu(String direccionUsu) {
		this.direccionUsu = direccionUsu;
	}

	public String getDocumentoUsu() {
		return this.documentoUsu;
	}

	public void setDocumentoUsu(String documentoUsu) {
		this.documentoUsu = documentoUsu;
	}

	public Date getFechanacUsu() {
		return this.fechanacUsu;
	}

	public void setFechanacUsu(Date fechanacUsu) {
		this.fechanacUsu = fechanacUsu;
	}

	public Integer getGeneroUsu() {
		return this.generoUsu;
	}

	public void setGeneroUsu(Integer generoUsu) {
		this.generoUsu = generoUsu;
	}

	public String getNombresUsu() {
		return this.nombresUsu;
	}

	public void setNombresUsu(String nombresUsu) {
		this.nombresUsu = nombresUsu;
	}

	public String getPrimerapellidoUsu() {
		return this.primerapellidoUsu;
	}

	public void setPrimerapellidoUsu(String primerapellidoUsu) {
		this.primerapellidoUsu = primerapellidoUsu;
	}

	public String getSegundoapellidoUsu() {
		return this.segundoapellidoUsu;
	}

	public void setSegundoapellidoUsu(String segundoapellidoUsu) {
		this.segundoapellidoUsu = segundoapellidoUsu;
	}

	public String getTelefonoUsu() {
		return this.telefonoUsu;
	}

	public void setTelefonoUsu(String telefonoUsu) {
		this.telefonoUsu = telefonoUsu;
	}

	public String getTipodocumentoUsu() {
		return this.tipodocumentoUsu;
	}

	public void setTipodocumentoUsu(String tipodocumentoUsu) {
		this.tipodocumentoUsu = tipodocumentoUsu;
	}

	public String getWhatsappUsu() {
		return this.whatsappUsu;
	}

	public void setWhatsappUsu(String whatsappUsu) {
		this.whatsappUsu = whatsappUsu;
	}

	public Listasprecio getListasprecio() {
		return this.listasprecio;
	}

	public void setListasprecio(Listasprecio listasprecio) {
		this.listasprecio = listasprecio;
	}

}