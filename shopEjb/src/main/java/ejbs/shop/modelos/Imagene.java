package ejbs.shop.modelos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the imagenes database table.
 * 
 */
@Entity
@Table(name="imagenes")
public class Imagene implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codimagen_img")
	private Integer codimagenImg;

	@Column(name="mime_img")
	private String mimeImg;

	@Column(name="nombrecodificado_img")
	private String nombrecodificadoImg;

	@Column(name="nombrereal_img")
	private String nombrerealImg;

	@Column(name="tamano_img")
	private String tamanoImg;

	//bi-directional many-to-one association to Producto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codproducto_pro")
	private Producto producto;

	public Imagene() {
	}

	public Integer getCodimagenImg() {
		return this.codimagenImg;
	}

	public void setCodimagenImg(Integer codimagenImg) {
		this.codimagenImg = codimagenImg;
	}

	public String getMimeImg() {
		return this.mimeImg;
	}

	public void setMimeImg(String mimeImg) {
		this.mimeImg = mimeImg;
	}

	public String getNombrecodificadoImg() {
		return this.nombrecodificadoImg;
	}

	public void setNombrecodificadoImg(String nombrecodificadoImg) {
		this.nombrecodificadoImg = nombrecodificadoImg;
	}

	public String getNombrerealImg() {
		return this.nombrerealImg;
	}

	public void setNombrerealImg(String nombrerealImg) {
		this.nombrerealImg = nombrerealImg;
	}

	public String getTamanoImg() {
		return this.tamanoImg;
	}

	public void setTamanoImg(String tamanoImg) {
		this.tamanoImg = tamanoImg;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}