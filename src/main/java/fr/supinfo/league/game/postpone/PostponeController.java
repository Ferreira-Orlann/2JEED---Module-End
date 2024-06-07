package fr.supinfo.league.game.postpone;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/games/{id}/postpones")
@RestController
public class PostponeController {
    private final PostponeService service;

    @GetMapping
    public @ResponseBody ResponseEntity<List<PostponeDto>> getPostpone(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(this.service.getPostpones(id));
    }

    // configure security roles using annotations https://www.baeldung.com/spring-security-method-security
    @RolesAllowed({"MEMBER_LEAGUE", "ADMIN"})
    @PostMapping
    public @ResponseBody ResponseEntity<PostponeDto> createPostpone(@PathVariable(name = "id") UUID id, @RequestBody PostponeDto dto) {
        return ResponseEntity.ok(this.service.createPostpone(id, dto));
    }
}
