package fr.supinfo.league.game.event;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    List<EventDto> entityToDto(List<EventEntity> entities);
    EventDto entityToDto(EventEntity entity);
    EventEntity dtoToEntity(EventDto dto);
}
