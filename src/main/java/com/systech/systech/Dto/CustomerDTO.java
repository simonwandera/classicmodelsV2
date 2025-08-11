package com.systech.systech.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public CustomerDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
