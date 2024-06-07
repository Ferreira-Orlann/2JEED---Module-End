package fr.supinfo.league.game.commentary;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/games/{id]/commentary")
@RestController
public class CommentaryController {
    private CommentaryService service;

    @GetMapping
    public @ResponseBody ResponseEntity<CommentaryDto> getCommentaries(@RequestParam(name = "id", required = true) UUID id) {
        return this.service.getCommentary(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<CommentaryDto>> getCommentary() {
        return ResponseEntity.of(Optional.ofNullable(this.service.getCommentaries()));
    }

    @RolesAllowed({"JOURNALIST"})
    @PostMapping
    public @ResponseBody ResponseEntity<CommentaryDto> createCommentary(@RequestBody CommentaryDto dto) {
        return ResponseEntity.ok(this.service.createAndSaveCommentary(dto));
    }
}
