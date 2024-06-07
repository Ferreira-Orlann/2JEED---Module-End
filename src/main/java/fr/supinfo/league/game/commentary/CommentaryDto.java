package fr.supinfo.league.game.commentary;

import fr.supinfo.league.game.GameEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalTime;
import java.util.UUID;

public record CommentaryDto(UUID id, UUID game, LocalTime time, String message, String journalistName) {
}
