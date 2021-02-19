package it.itmo.first.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.itmo.first.dto.Gender;
import it.itmo.first.dto.Representation;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.time.LocalDate;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MyControllerTest {


    MockMvc mockMvc;
    Representation user = new Representation();
    {
        user.setId(666);
        user.setName("Paul");
        user.setGender(Gender.MALE);
        user.setBirthdate(LocalDate.of(1988, 11, 20));
        user.setEmail("paul@gmail.com");
    }

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


    @Test
    public void sayBye() throws Exception {
        String uri = "/greetings/goodbye";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void sayHello() throws Exception {
        String urlTemplate = "/greetings/hello";
        mockMvc.perform(get(urlTemplate)
                .param("name", "Ivan"))
                .andExpect(status().isOk())
                .andDo(document(urlTemplate));
    }

    @Test
    public void postUser() throws Exception {
        Representation representation = new Representation();
        representation.setName("Ivan");
        String content = objectMapper.writeValueAsString(representation);
        System.out.println(content);
        String uri = "/greetings/returnWithId";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void create() throws Exception {
        String content = objectMapper.writeValueAsString(user);
        System.out.println(content);
        String uri = "/greetings/users/add";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document(uri));

    }

    @Test
    public void update() throws Exception {
        String content = objectMapper.writeValueAsString(user);
        System.out.println(content);
        String uri = "/greetings/users/666/edit";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void delete() throws Exception {
        String uri = "/greetings/users/666/delete";
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void test() throws JsonProcessingException {

        Representation representation = new Representation();
        representation.setName("John");
        System.out.println(objectMapper.writeValueAsString(representation));
    }

}