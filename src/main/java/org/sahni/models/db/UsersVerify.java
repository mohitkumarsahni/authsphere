package org.sahni.models.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users_verify")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersVerify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email_id")
    private String emailID;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "otp")
    private String otp;
}
