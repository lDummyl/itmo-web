package it.itmo.first.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.itmo.first.dto.Car;
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
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)

public class CarControllerTest {

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


    @Test
    public void create() throws Exception{
        Car car = new Car();
        car.setId(1);
        car.setBrend("AUDI");
        car.setModel("Q7");
        car.setYearOfRelease(2010);
        car.setOwner(1);
        String content = objectMapper.writeValueAsString(car);
        System.out.println(content);
        String uri = "/greetings/cars";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated())
                .andDo(document(uri));
    }

    @Test
    public void read() throws Exception{
        /*Car car = new Car();
        car.setId(1);
        car.setBrend("AUDI");
        car.setModel("Q7");
        car.setYearOfRelease(2010);
        car.setOwner(1);
        String content = objectMapper.writeValueAsString(car);*/
        String uri = "/greetings/cars";
        /*mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));*/

        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void testRead() throws Exception{
        String uri = "/greetings/cars/1";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void update() throws Exception{
        Car car = new Car();
        car.setId(1);
        car.setBrend("AUDI");
        car.setModel("Q7");
        car.setYearOfRelease(2010);
        car.setOwner(2);
        String content = objectMapper.writeValueAsString(car);
        System.out.println(content);
        String uri = "/greetings/cars/1";
        mockMvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void delete() throws Exception{
        String uri = "/greetings/cars/1";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }
}