package it.itmo.first.web;

//import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.itmo.first.dto.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    MockMvc mockMvc; //клиент для тестов

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();
    }

    User user = new User(1,"TestName","test_email@yandex.ru", LocalDate.of(2010,05,11), User.Gender.male);

    @Test
    public void testCreateUser() throws Exception{
        String content = objectMapper.writeValueAsString(user);
        String uri = "/greetings/users";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(content)))
                .andExpect(status().isCreated())
                .andDo(document(uri));
    }

        @Test
    public void testReadUsers() throws Exception{

        String uri = "/greetings/users";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void testReadUserById() throws Exception{
        String uri = "/greetings/cars/3";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

//    @Test
//    public void update() throws Exception{
//        createCar();
//        String content = objectMapper.writeValueAsString(car2);
//        String uri = "/greetings/cars/1";
//        mockMvc.perform(put(uri)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content))
//                .andExpect(status().isOk())
//                .andDo(document(uri));
//    }

    @Test
    public void delete() throws Exception{
        String uri = "/greetings/cars/1";
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }
}