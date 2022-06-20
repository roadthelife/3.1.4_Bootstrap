package com.example.demo.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
