package com.example.domain.domain.model.customer;

import com.example.domain.domain.dto.SignUpForm;
import com.example.domain.domain.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.Locale;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private String phone;
    private LocalDate birth;
    private Integer balance;

    public static Customer from(SignUpForm form){
        return Customer.builder()
            .email(form.getEmail().toLowerCase(Locale.ROOT))
            .name(form.getName())
            .password(form.getPassword())
            .birth(form.getBirth())
            .phone(form.getPhone())
            .build();
    }
}
