package com.systech.systech.Entity;

import jakarta.persistence.*;//I tried listing every specific import but everytime I write another entity this import with asterik replaces all the imports
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@ToString(exclude = {"customer", "orderDetails"}) // Exclude to avoid circular references
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Customers customer;
    // One-to-Many relationship with OrderDetails
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //when I do something to the parent, also do it to the children.
    private List<OrderDetails> orderDetails;

}
