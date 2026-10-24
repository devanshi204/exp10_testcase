
package com.example.electricity.usage;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.electricity.usage.controller.UserController;
import com.example.electricity.usage.domain.User;
import com.example.electricity.usage.repository.UserRepository;

@WebMvcTest(UserController.class)
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    void testLoginWithCorrectCredentials() throws Exception {

        User user = new User();
        user.setName("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("testpassword");

        when(userRepository.findByEmail("testuser@example.com"))
                .thenReturn(user);

        mockMvc.perform(post("/login")
                .param("email", "testuser@example.com")
                .param("password", "testpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard.html"));
    }

    @Test
    void testLoginWithIncorrectCredentials() throws Exception {

        when(userRepository.findByEmail("wrong@example.com"))
                .thenReturn(null);

        mockMvc.perform(post("/login")
                .param("email", "wrong@example.com")
                .param("password", "wrongpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login.html?error=1"));   
    }
}
