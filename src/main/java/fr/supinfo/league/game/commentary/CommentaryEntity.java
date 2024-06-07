package fr.supinfo.league.game.commentary;

import fr.supinfo.league.game.GameEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;

import java.time.LocalTime;
import java.util.UUID;

@Setter
@Getter
@Entity
public class CommentaryEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID gameId;
    @Column(nullable = false)
    private LocalTime time;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private String journalistName;
}
