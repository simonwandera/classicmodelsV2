package com.systech.systech.Entity;

//I tried listing every specific import but everytime I write another entity this import with asterik replaces all the imports
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;



@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString(exclude = {"customer", "orderDetails"}) // Exclude to avoid circular references
public class Orders extends BaseEntity {

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private LocalDate requiredDate;

    @Column
    private LocalDate shippedDate;

    @Column(nullable = false)
    private String status;

    @Column
    private String comments;
    // Many-to-One relationship with Customer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_number", nullable = false)
    @JsonIgnore
    private Customers customer;
    // One-to-Many relationship with OrderDetails


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //when I do something to the parent, also do it to the children.
    @JsonIgnore
    private List <OrderDetails> orderDetails;

}
