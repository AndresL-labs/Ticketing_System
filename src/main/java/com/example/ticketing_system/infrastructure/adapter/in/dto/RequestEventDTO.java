package com.example.ticketing_system.infrastructure.adapter.in.dto;

import com.example.shared.Create;
import com.example.shared.Update;
import com.example.ticketing_system.infrastructure.adapter.in.validation.ValidDateRange;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ValidDateRange(groups = {Create.class, Update.class})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEventDTO {
    @NotBlank(groups = {Create.class, Update.class}, message = "El nombre del evento no puede estar vacío")
    @Size(min = 1, max = 100, groups = {Create.class, Update.class})
    private String eventName;

    @NotNull(message = "La fecha de inicio es obligatoria", groups = {Create.class})
    @Future(message = "{event.start.future}", groups =  {Create.class, Update.class})
    private LocalDateTime eventDate;

    @NotNull(message = "La fecha final es obligatoria", groups = {Create.class})
    @Future(message = "{event.end.future}", groups =  {Create.class, Update.class})
    private LocalDateTime eventEndDate;

    @NotNull(groups = Create.class, message = "El ID del lugar es obligatorio")
    private Long venueId;
}