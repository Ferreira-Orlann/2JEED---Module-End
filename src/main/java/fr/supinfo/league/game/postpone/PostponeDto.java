package fr.supinfo.league.game.postpone;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PostponeDto(UUID id, UUID gameId, @NotNull UUID oldMatchDayId, @NotNull UUID newMatchDayId, String reason) {
}
