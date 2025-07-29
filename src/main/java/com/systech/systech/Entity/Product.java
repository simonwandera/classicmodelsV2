package com.systech.systech.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "products")//I think the table name here is supposed to be products
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity{


    @Column(name = "product_code")
    private String productCode;

    @Column
    private String productName;

    @Column(name="msrp")
    private String msrp;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false )
    @JoinColumn(name = "product_line_id")
    private ProductLine productLine;

    @JsonProperty("productLine")
    public void setProductLineFormId(long id){
        ProductLine line = new ProductLine();
        line.setId(id);
        this.productLine = line;

    }

    @Column
    private String productScale;

    @Column
    private String productVendor;

    @Column
    private String productDescription;

    @Column
    private String quantityInStock;

    @Column
    private String buyPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <OrderDetail> orderDetails;
}

