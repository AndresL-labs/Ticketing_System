package com.example.ticketing_system.infrastructure.spec;

import com.example.ticketing_system.domain.model.Event;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class EventSpecification {
    public static Specification<Event> nameLike(String name) {
        // Ya no validamos null aquí
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("eventName")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Event> dateFrom(LocalDateTime start) {
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("eventDate"), start);
    }

    public static Specification<Event> dateTo(LocalDateTime end) {
        return (root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("eventDate"), end);
    }

    public static Specification<Event> locationEquals(String location) {
        return (root, query, cb) -> {
            // Usamos LEFT JOIN para evitar que el evento desaparezca si no tiene Venue (opcional)
            // Si Venue es obligatorio en tu BD, puedes dejarlo como root.join("venue")
            return cb.equal(root.join("venue", JoinType.LEFT).get("location"), location);
        };
    }
}