package fr.supinfo.league.game.event;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.UUID;

public record EventDto(UUID id, UUID gameId, @NotNull LocalTime time, @NotNull String playerName, @NotNull EventType eventType) {
}
