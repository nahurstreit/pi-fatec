package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Weights")
public class Weight {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWeight")
	public Integer idWeight;

	@Column(name = "idCustomer")
	public Integer idCustomer;

	@Column(name = "wgt_value")
	public Double wgtValue;
	
	@Column(name = "wgt_dateRegister")
	public Double wgtDateRegister;

    public Weight(Integer idWeight, Integer idCustomer, Double wgtValue, Double wgtDateRegister) {
        this.idWeight = idWeight;
        this.idCustomer = idCustomer;
        this.wgtValue = wgtValue;
        this.wgtDateRegister = wgtDateRegister;
    }
    
    public Weight() {
    	this(null, null, null, null);
    }
}