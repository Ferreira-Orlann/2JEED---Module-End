package fr.supinfo.league.game;

import fr.supinfo.league.SecurityUtils;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
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
    private final SecurityUtils securityUtils;

    @GetMapping
    public List<GameDto> getGames(@RequestParam(required = false, name = "date") LocalDate date) {
        return this.gameServices.getGames(date);
    }

    // configure security roles using annotations https://www.baeldung.com/spring-security-method-security
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping
    public GameDto createGame(@RequestBody GameDto game) {
        return this.gameServices.createGame(game);
    }

    @RolesAllowed({"ROLE_JOURNALIST", "ROLE_ADMIN", "ROLE_LEAGUE_MEMBER"})
    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<GameDto> startGame(@PathVariable UUID id, @RequestBody GameDto gameDto, @AuthenticationPrincipal Authentication authentication) {
        if(Objects.isNull(gameDto.id())) {
            return ResponseEntity.badRequest().build();
        }
        Optional<GameDto> optSavedGameDto = this.gameServices.getGame(id);
        if (optSavedGameDto.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        GameDto savedGameDto = optSavedGameDto.get();
        if(gameDto.suspended() != savedGameDto.suspended() && this.securityUtils.hasRole(authentication, "ROLE_LEAGUE_MEMBER")) {
            this.gameServices.updateSuspended(gameDto, gameDto.suspended());
        }
        if (this.securityUtils.hasRole(authentication, "ROLE_JOURNALIST")) {
            Optional<GameDto> optUpdatedGameDto = this.gameServices.updateTimes(gameDto, gameDto.startTime(), gameDto.endTime());
            return optUpdatedGameDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.badRequest().build();
    }
}
