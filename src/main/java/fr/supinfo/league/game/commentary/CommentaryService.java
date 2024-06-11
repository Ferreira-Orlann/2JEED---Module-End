package fr.supinfo.league.game.commentary;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommentaryService {
    private final CommentaryMapper mapper;
    private final CommentaryRepository repository;

    public List<CommentaryDto> getCommentaries() {
        return this.mapper.entityToDto(this.repository.findAll());
    }

    public Optional<CommentaryDto> getCommentary(UUID id) {
        return this.repository.findById(id).map(entity -> this.mapper.entityToDto(entity));
    }

    public CommentaryDto createAndSaveCommentary(CommentaryDto dto) {
        return this.mapper.entityToDto(this.repository.save(this.mapper.dtoToEntity(dto)));
    }

}
