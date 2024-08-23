package org.fyahskuztum.ecommerce.store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "First name cannot be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "Street address cannot be null")
    @Column(nullable = false)
    private String streetAddress;

    @NotEmpty(message = "City cannot be null")
    @Column(nullable = false)
    private String city;

    @NotEmpty(message = "State cannot be null")
    @Size(min = 2, max = 2, message = "State must be exactly 2 letters")
    @Column(nullable = false, length = 2)
    private String state;

    @NotEmpty(message = "Zip code cannot be null")
    @Pattern(regexp = "\\d{5}", message = "Zip code must be exactly 5 digits")
    @Column(nullable = false, length = 5)
    private String zipCode;

    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must contain exactly 10 digits")
    @Column(nullable = false, length = 10)
    private String phoneNumber;
}

