package it.itmo.first.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.itmo.first.dto.Car;
import it.itmo.first.dto.CarType;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CarControllerTest {

    MockMvc mockMvc;
    Car car = new Car();

    {
        car.setId(5);
        car.setBrandName("BMW");
        car.setBrandModelName("525");
        car.setType(CarType.SEDAN);
        car.setColor("Black");
        car.setProductionDate(LocalDate.ofYearDay(2020, 150));

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
    public void create() throws Exception {
       String content = objectMapper.writeValueAsString(car);
       System.out.println(content);
       String uri = "/greetings/cars/add";
       mockMvc.perform(put(uri)
               .contentType(MediaType.APPLICATION_JSON)
               .content(content))
               .andExpect(status().isOk())
               .andDo(document(uri));

    }

    @Test
    public void update() throws Exception {
        String content = objectMapper.writeValueAsString(car);
        System.out.println(content);
        String uri = "/greetings/cars/5/edit";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void delete() throws Exception {
        String uri = "/greetings/cars/5/delete";
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

}