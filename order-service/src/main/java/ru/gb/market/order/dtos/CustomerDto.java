package ru.gb.market.order.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.market.order.data.Customer;

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

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.midName = customer.getMidName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.country = customer.getCountry();
        this.region = customer.getRegion();
        this.district = customer.getDistrict();
        this.city = customer.getCity();
        this.address = customer.getAddress();
        this.phone = customer.getPhone();
    }
}
