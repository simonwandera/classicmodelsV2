package com.systech.systech.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_line")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductLine extends BaseEntity {

    @Column
    @OneToMany(mappedBy = "product_line", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private String productCode;

    @Column
    private String textDescription;

    @Column
    private String htmlDescription;

    @Column
    private String image;


}
