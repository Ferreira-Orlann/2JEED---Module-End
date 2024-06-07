package fr.supinfo.league.game.commentary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentaryRepository extends JpaRepository<CommentaryEntity, UUID> {

}
