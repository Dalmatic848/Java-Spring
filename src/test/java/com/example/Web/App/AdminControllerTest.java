package com.example.Web.App;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AdminController.class)
@Import(TestSecurityConfig.class) // Импортируем тестовую конфигурацию безопасности
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"}) // Добавляем mock пользователя
    void shouldDisplayDatasPageWithUsers() throws Exception {
        given(userService.getAllUsers()).willReturn(Arrays.asList("Alice", "Bob"));

        mockMvc.perform(get("/datas"))
                .andExpect(status().isOk())
                .andExpect(view().name("datas-page"))
                .andExpect(model().attribute("users", Arrays.asList("Alice", "Bob")));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void shouldAddUserViaAjax() throws Exception {
        Map<String, String> payload = Map.of("username", "David");
        String json = objectMapper.writeValueAsString(payload);

        mockMvc.perform(post("/datas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        then(userService).should().addUser("David");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void shouldHandleEmptyUsername() throws Exception {
        String json = objectMapper.writeValueAsString(Map.of("username", ""));

        mockMvc.perform(post("/datas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        then(userService).should().addUser("");
    }
}