package fr.supinfo.league.game.postpone;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostponeMapper {
    List<PostponeDto> entityToDto(List<PostponeEntity> entities);
    PostponeDto entityToDto(PostponeEntity entity);
    PostponeEntity dtoToEntity(PostponeDto dto);
}
