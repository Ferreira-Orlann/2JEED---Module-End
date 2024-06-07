package fr.supinfo.league.game.event;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Setter
@Getter
@Entity
public class EventEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private UUID gameId;
    @Column(nullable = false)
    private LocalTime time;
    @Column(nullable = false)
    private String playerName;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
}
