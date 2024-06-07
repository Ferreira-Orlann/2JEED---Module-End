package fr.supinfo.league.game.commentary;

import java.time.LocalTime;
import java.util.UUID;

public record CommentaryDto(UUID id, UUID gameId, LocalTime time, String message, String journalistName) {
}
