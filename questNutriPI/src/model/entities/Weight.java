package model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import model.dao.WeightDAO;

@Entity
@Table(name = "Weights")
public class Weight extends WeightDAO{
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWeight")
	public Integer idWeight;

	@Column(name = "idCustomer")
	public Integer idCustomer;

	@Column(name = "wgt_value")
	public Double wgtValue;
	
	@Column(name = "wgt_dateRegister")
	private Double wgtDateRegister;

    public Weight(Integer idWeight, Integer idCustomer, Double wgtValue, Double wgtDateRegister) {
        super();
    	this.idWeight = idWeight;
        this.idCustomer = idCustomer;
        this.wgtValue = wgtValue;
        this.wgtDateRegister = wgtDateRegister;
    }
    
    public Weight() {
    	this(null, null, null, null);
    }

	@Override
	public String toString() {
		return "Weight: {"
	+ "\n    idWeight:" + idWeight 
	+ "\n    idCustomer:" + idCustomer
	+ "\n    wgtValue:" + wgtValue
	+ "\n    wgtDateRegister:" + wgtDateRegister
	+ "\n}";
	}
    
	/**
	 * Método para retornar uma visão pequena do objeto.
	 * @return String contendo apenas alguns dos atributos mais importantes
	 */
	public String smallInfo() {
		return "{id: "+idWeight+", value: "+wgtValue+"}";

	}
}