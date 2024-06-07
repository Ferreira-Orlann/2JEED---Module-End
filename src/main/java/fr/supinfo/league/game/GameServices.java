package fr.supinfo.league.game;

import fr.supinfo.league.season.matchday.MatchDayDto;
import fr.supinfo.league.season.matchday.MatchDayServices;
import fr.supinfo.league.team.TeamServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

@RequiredArgsConstructor
@Service
public class GameServices {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    private final MatchDayServices matchDayServices;
    private final TeamServices teamServices;

    public Optional<GameDto> getGame(UUID id) {
        return  this.gameRepository.findById(id).map(this.gameMapper::entityToDto);
    }

    public boolean gameExitById(UUID id) {
        return this.gameRepository.existsById(id);
    }

    public List<GameDto> getGames(LocalDate date) {
        List<GameDto> result;
        if (Objects.isNull(date)) {
            result = this.gameMapper.entityToDto(this.gameRepository.findAll());
        } else {
            Optional<UUID> matchDayId = this.matchDayServices.retrieveMatchDayId(date);
            if (matchDayId.isPresent()) {
                result = this.gameMapper.entityToDto(this.gameRepository.findByMatchDayId(matchDayId.get()));
            } else {
                result = Collections.emptyList();
            }
        }
        return result;
    }

    public GameDto createGame(GameDto game) {
        // checks
        this.matchDayServices.checkMatchDayToCreateGame(game.matchDayId());
        this.teamServices.checkTeams(game.homeTeamId(), game.visitorTeamId());

        GameEntity gameEntity = this.gameMapper.dtoToEntity(game);
        GameEntity saved = this.gameRepository.save(gameEntity);
        return this.gameMapper.entityToDto(saved);
    }

    public Optional<GameDto> updateGame(GameDto gameDto) {
        if (this.gameRepository.existsById(gameDto.id())) {
            return Optional.empty();
        }
        return Optional.of(this.gameMapper.entityToDto(this.gameRepository.save(this.gameMapper.dtoToEntity(gameDto))));
    }

    public Optional<GameDto> updateTimes(GameDto gameDto, LocalTime startTime, LocalTime endTime) {
        Optional<GameEntity> optGame = this.gameRepository.findById(gameDto.id());
        if (optGame.isEmpty()) {
            return Optional.empty();
        }
        GameEntity gameEnt = optGame.get();
        if (Objects.nonNull(startTime)) {
            gameEnt.setStartTime(startTime);
        }
        if (Objects.nonNull(endTime)) {
            gameEnt.setEndTime(endTime);
        }
        return Optional.of(this.gameMapper.entityToDto(this.gameRepository.save(gameEnt)));
    }

    public GameDto updateSuspended(GameDto gameDto, boolean suspended) {
        Optional<MatchDayDto> optMatchDayDto = this.matchDayServices.getMatchById(gameDto.matchDayId());
        if(optMatchDayDto.isEmpty()) {
            return  gameDto;
        }
        MatchDayDto matchDayDto = optMatchDayDto.get();
        LocalDate now = LocalDate.now();
        if (now.isBefore(matchDayDto.date()) || now.isBefore(ChronoLocalDate.from(gameDto.startTime()))) {
            return gameDto;
        }
        Optional<GameEntity> optGame = this.gameRepository.findById(gameDto.id());
        if (optGame.isEmpty()) {
            return gameDto;
        }
        GameEntity gameEnt = optGame.get();
        gameEnt.setSuspended(suspended);
        return this.gameMapper.entityToDto(this.gameRepository.save(gameEnt));
    }
}
