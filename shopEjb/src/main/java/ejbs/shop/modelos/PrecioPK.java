package ejbs.shop.modelos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the precios database table.
 * 
 */
@Embeddable
public class PrecioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codlista_lp", insertable=false, updatable=false)
	private Integer codlistaLp;

	@Column(name="codproducto_pro", insertable=false, updatable=false)
	private Integer codproductoPro;

	public PrecioPK() {
	}
	public Integer getCodlistaLp() {
		return this.codlistaLp;
	}
	public void setCodlistaLp(Integer codlistaLp) {
		this.codlistaLp = codlistaLp;
	}
	public Integer getCodproductoPro() {
		return this.codproductoPro;
	}
	public void setCodproductoPro(Integer codproductoPro) {
		this.codproductoPro = codproductoPro;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PrecioPK)) {
			return false;
		}
		PrecioPK castOther = (PrecioPK)other;
		return 
			this.codlistaLp.equals(castOther.codlistaLp)
			&& this.codproductoPro.equals(castOther.codproductoPro);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codlistaLp.hashCode();
		hash = hash * prime + this.codproductoPro.hashCode();
		
		return hash;
	}
}