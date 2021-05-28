package ru.kpfu.itis.barakhov.blablafly.models;

import lombok.*;

import javax.persistence.*;

import java.util.Currency;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long departureTimeUtc;

    private Long arrivalTimeUtc;

    private Integer availablePlacesCount;

    private Float ticketPrice;

    private String ticketCurrency;

    @OneToMany(mappedBy = "flight")
    private Set<Ticket> tickets;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_city_id", nullable = false)
    private City departureCity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrival_city_id", nullable = false)
    private City arrivalCity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    public void setTicketCurrency(Currency currency) {
        this.ticketCurrency = currency.getCurrencyCode();
    }

    public Currency getTicketCurrency() {
        return Currency.getInstance(ticketCurrency);
    }

}
