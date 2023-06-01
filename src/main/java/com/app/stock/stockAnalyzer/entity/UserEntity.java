package com.app.stock.stockAnalyzer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private LocalDateTime createdAt;
    @Column
    private Integer providerId;

    @ToString.Include(name = "password")
    private String maskPassword() {
        return "********";
    }
}
