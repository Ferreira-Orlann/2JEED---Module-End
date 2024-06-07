package fr.supinfo.league.game.postpone;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostponeService {
    private final PostponeRepository repository;
    private final PostponeMapper mapper;

    public List<PostponeDto> getPostpones(UUID gameId) {
        return this.mapper.entityToDto(this.repository.findByGameId(gameId));
    }

    public PostponeDto createPostpone(UUID gameId, PostponeDto postponeDto) {
        PostponeEntity entity = this.mapper.dtoToEntity(postponeDto);
        entity.setGameId(gameId);
        return this.mapper.entityToDto(this.repository.save(entity));
    }
}
