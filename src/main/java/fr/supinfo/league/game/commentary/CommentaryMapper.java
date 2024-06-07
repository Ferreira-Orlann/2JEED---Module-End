package fr.supinfo.league.game.commentary;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentaryMapper {
    List<CommentaryDto> entityToDto(List<CommentaryEntity> entities);
    CommentaryDto entityToDto(CommentaryEntity entity);
    CommentaryEntity dtoToEntity(CommentaryDto dto);
}
