package com.compassuol.sp.challenge.msuser.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "userAddress", schema = "users")
@NoArgsConstructor
public class UserAddress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_id")
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public UserAddress(Long addressId, User user) {
        this.addressId = addressId;
        this.user = user;
    }
}
