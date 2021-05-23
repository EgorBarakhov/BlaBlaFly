package ru.kpfu.itis.barakhov.blablafly.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long shiftFromUtc;

    @OneToMany(mappedBy = "arrivalCity", fetch = FetchType.LAZY)
    private List<Flight> arrivals;

    @OneToMany(mappedBy = "departureCity", fetch = FetchType.LAZY)
    private List<Flight> departures;

}