package fr.supinfo.league.game.postpone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class PostponeEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private UUID gameId;
    @Column(nullable = false)
    private UUID oldMatchDayId;
    @Column(nullable = false)
    private UUID newMatchDayId;
    @Column(nullable = false)
    private String reason;
}
