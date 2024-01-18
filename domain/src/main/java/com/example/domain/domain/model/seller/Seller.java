package com.example.domain.domain.model.seller;

import com.example.domain.domain.dto.SignUpForm;
import com.example.domain.domain.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Seller extends BaseEntity{

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

    public static Seller from(SignUpForm form){
        return Seller.builder()
            .email(form.getEmail().toLowerCase(Locale.ROOT))
            .name(form.getName())
            .password(form.getPassword())
            .birth(form.getBirth())
            .phone(form.getPhone())
            .build();
    }


}
