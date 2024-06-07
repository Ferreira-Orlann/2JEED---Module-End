package fr.supinfo.league.game.postpone;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostponeRepository extends JpaRepository<PostponeEntity, UUID> {
    List<PostponeEntity> findByGameId(UUID gameId);
}
