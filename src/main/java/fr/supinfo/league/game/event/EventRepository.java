package fr.supinfo.league.game.event;

import fr.supinfo.league.game.postpone.PostponeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<EventEntity, UUID> {
    List<EventEntity> findByGameId(UUID gameId);
}
