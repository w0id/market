package ru.gb.market.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private String firstName;
    private String midName;
    private String lastName;
    private String email;
    private String country;
    private String region;
    private String district;
    private String city;
    private String address;
    private String phone;

}
