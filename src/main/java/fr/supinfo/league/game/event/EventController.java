package fr.supinfo.league.game.event;

import fr.supinfo.league.game.postpone.PostponeDto;
import fr.supinfo.league.game.postpone.PostponeService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/games/{id}/events")
@RestController
public class EventController {
    private final EventService service;

    @GetMapping
    public @ResponseBody ResponseEntity<List<EventDto>> getEvents(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(this.service.getEvent(id));
    }

    // configure security roles using annotations https://www.baeldung.com/spring-security-method-security
    @RolesAllowed({"ROLE_LEAGUE_MEMBER", "ROLE_ADMIN"})
    @PostMapping
    public @ResponseBody ResponseEntity<EventDto> createPostpone(@PathVariable(name = "id") UUID id, @RequestBody EventDto dto) {
        return ResponseEntity.ok(this.service.createEvent(id, dto));
    }
}
