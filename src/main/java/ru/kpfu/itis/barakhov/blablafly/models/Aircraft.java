package ru.kpfu.itis.barakhov.blablafly.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aircraft")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer capacity;

    private String legacySerialNumber;

    @OneToMany(mappedBy = "aircraft")
    private Set<Flight> flights;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
