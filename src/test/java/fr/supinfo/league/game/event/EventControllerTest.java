package fr.supinfo.league.game.event;

import fr.supinfo.league.game.GameEntity;
import fr.supinfo.league.game.GameRepository;
import fr.supinfo.league.season.matchday.MatchDayEntity;
import fr.supinfo.league.season.matchday.MatchDayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private MatchDayRepository matchDayRepository;
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    public void whenCreateEventWithAdmin() throws Exception {
        // Given
        MatchDayEntity matchDay = new MatchDayEntity();
        matchDay.setId(UUID.fromString("ac05477e-60e0-4c07-9455-6929c1b4c169"));
        matchDay.setDate(LocalDate.now().plusDays(5));
        this.matchDayRepository.save(matchDay);

        GameEntity game = new GameEntity();
        game.setDescription("Good game");
        game.setMatchDayId(matchDay.getId());
        game.setHomeTeamId(UUID.fromString("22f8841b-c1c3-49e2-9e08-8884ca1ff9c0"));
        game.setVisitorTeamId(UUID.fromString("5b6bbd96-3b0c-4b34-aeaf-e001d0e1f0da"));
        game.setStartTime(LocalTime.of(15, 15, 15));
        game.setId(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"));
        this.gameRepository.save(game);

        Path input = Path.of("src", "test", "resources", "inputs", "event-creation.json");
        String body = Files.readString(input);

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/games/" + "3fa85f64-5717-4562-b3fc-2c963f66afa6" + "/events").content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        String expected = Files.readString(Path.of("src", "test", "resources", "expectations", "created-event.json"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expected));
    }

    @WithMockUser(roles = {"JOURNALIST"})
    @Test
    public void whenCreateEventWithJournalist() throws Exception {
        // Given
        MatchDayEntity matchDay = new MatchDayEntity();
        matchDay.setId(UUID.fromString("ac05477e-60e0-4c07-9455-6929c1b4c169"));
        matchDay.setDate(LocalDate.now().plusDays(5));
        this.matchDayRepository.save(matchDay);

        GameEntity game = new GameEntity();
        game.setDescription("Good game");
        game.setMatchDayId(matchDay.getId());
        game.setHomeTeamId(UUID.fromString("22f8841b-c1c3-49e2-9e08-8884ca1ff9c0"));
        game.setVisitorTeamId(UUID.fromString("5b6bbd96-3b0c-4b34-aeaf-e001d0e1f0da"));
        game.setStartTime(LocalTime.of(15, 15, 15));
        game.setId(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"));
        this.gameRepository.save(game);

        Path input = Path.of("src", "test", "resources", "inputs", "event-creation.json");
        String body = Files.readString(input);

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/games/" + "3fa85f64-5717-4562-b3fc-2c963f66afa6" + "/events").content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        String expected = Files.readString(Path.of("src", "test", "resources", "expectations", "created-event.json"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expected));
    }

    @Test
    public void whenCreateEventWithUser() throws Exception {
        // Given
        MatchDayEntity matchDay = new MatchDayEntity();
        matchDay.setId(UUID.fromString("ac05477e-60e0-4c07-9455-6929c1b4c169"));
        matchDay.setDate(LocalDate.now().plusDays(5));
        this.matchDayRepository.save(matchDay);

        GameEntity game = new GameEntity();
        game.setDescription("Good game");
        game.setMatchDayId(matchDay.getId());
        game.setHomeTeamId(UUID.fromString("22f8841b-c1c3-49e2-9e08-8884ca1ff9c0"));
        game.setVisitorTeamId(UUID.fromString("5b6bbd96-3b0c-4b34-aeaf-e001d0e1f0da"));
        game.setStartTime(LocalTime.of(15, 15, 15));
        game.setId(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"));
        this.gameRepository.save(game);

        Path input = Path.of("src", "test", "resources", "inputs", "event-creation.json");
        String body = Files.readString(input);

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/games/" + "3fa85f64-5717-4562-b3fc-2c963f66afa6" + "/events").content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        String expected = Files.readString(Path.of("src", "test", "resources", "expectations", "created-event.json"));
        resultActions.andExpect(MockMvcResultMatchers.status().isForbidden());
    }


    @WithMockUser
    @Test
    public void whenRetreived() throws Exception {
        // Given
        MatchDayEntity matchDay = new MatchDayEntity();
        matchDay.setId(UUID.fromString("ac05477e-60e0-4c07-9455-6929c1b4c169"));
        matchDay.setDate(LocalDate.now().plusDays(5));
        matchDay = this.matchDayRepository.save(matchDay);

        GameEntity game = new GameEntity();
        game.setDescription("Good game");
        game.setMatchDayId(matchDay.getId());
        game.setHomeTeamId(UUID.fromString("22f8841b-c1c3-49e2-9e08-8884ca1ff9c0"));
        game.setVisitorTeamId(UUID.fromString("5b6bbd96-3b0c-4b34-aeaf-e001d0e1f0da"));
        game.setStartTime(LocalTime.of(15, 15, 15));
        game.setId(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"));
        game = this.gameRepository.save(game);

        EventEntity event = new EventEntity();
        event.setGameId(game.getId());
        event.setTime(LocalTime.of(10,10,10,10));
        event.setPlayerName("Justin Bridoux");
        event.setEventType(EventType.BUT);
        event = this.eventRepository.save(event);

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/games/" + "3fa85f64-5717-4562-b3fc-2c963f66afa6" + "/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        String expected = Files.readString(Path.of("src", "test", "resources", "expectations", "retreived-event.json"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expected));
    }

}
