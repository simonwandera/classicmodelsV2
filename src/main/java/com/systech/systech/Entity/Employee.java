package com.systech.systech.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "employee" )
public class Employee  extends BaseEntity{

    @Column(nullable = false)
    private Long employeeNumber;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String extension;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String jobTitle;

    @ManyToOne
    @JoinColumn(name = "officeCode")
    private Office office;

    @ManyToOne
    @JoinColumn(name = "reportsTo")
    private Employee manager;

    public String getFullName() {
        String firstEmployeeName = firstName != null ? firstName : "";
        String lastEmployeeName = lastName != null ? lastName : "";

        String fullName = (firstEmployeeName + " " + lastEmployeeName).trim();

        return fullName.isEmpty() ? "Unknown" : fullName;
    }

}
