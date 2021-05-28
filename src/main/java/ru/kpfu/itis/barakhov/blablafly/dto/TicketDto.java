package ru.kpfu.itis.barakhov.blablafly.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.barakhov.blablafly.models.Ticket;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TicketDto {

    private Float price;

    private String currency;

    private Long flightId;

    private Long userId;

    public static TicketDto from(Ticket ticket) {
        return TicketDto.builder()
                .price(ticket.getFlight().getTicketPrice())
                .currency(ticket.getFlight().getTicketCurrency().getDisplayName())
                .flightId(ticket.getFlight().getId())
                .userId(ticket.getHolder().getId())
                .build();
    }

    public static List<TicketDto> from(List<Ticket> tickets) {
        return tickets.stream()
                .map(TicketDto::from)
                .collect(Collectors.toList());
    }
}
