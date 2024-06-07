package fr.supinfo.league.game.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EventService {
    private final EventMapper mapper;
    private final EventRepository repository;

    public List<EventDto> getEvents(UUID gameId) {
        return this.mapper.entityToDto(this.repository.findByGameId(gameId));
    }

    public EventDto createEvent(UUID gameId, EventDto eventDto) {
        EventEntity entity = this.mapper.dtoToEntity(eventDto);
        entity.setGameId(gameId);
        return this.mapper.entityToDto(this.repository.save(entity));
    }
}
