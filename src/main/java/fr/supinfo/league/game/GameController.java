package fr.supinfo.league.game;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/games")
@RestController
public class GameController {

    private final GameServices gameServices;

    @GetMapping
    public List<GameDto> getGames(@RequestParam(required = false, name = "date") LocalDate date) {
        return this.gameServices.getGames(date);
    }

    // configure security roles using annotations https://www.baeldung.com/spring-security-method-security
    @RolesAllowed({"ADMIN"})
    @PostMapping
    public GameDto createGame(@RequestBody GameDto game) {
        return this.gameServices.createGame(game);
    }

    @RolesAllowed({"JOURNALIST", "ADMIN"})
    @PostMapping("/{id}")
    public @ResponseBody ResponseEntity<GameDto> startGame(@PathVariable UUID id, @RequestBody GameDto gameDto) {
        if(Objects.isNull(gameDto.id())) {
            return ResponseEntity.badRequest().build();
        }
        Optional<GameDto> optUpdatedGameDto = this.gameServices.updateTimes(gameDto, gameDto.startTime(), gameDto.endTime());
        return optUpdatedGameDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
