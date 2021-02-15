package it.itmo.first.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.itmo.first.dto.Car;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)

public class CarControllerTest {

    MockMvc mockMvc; //клиент для тестов
    Car car = new Car(1,"AUDI","Q-7",2010,1);
    Car car2 = new Car(2,"BMW","760",1998,2);
//    Car car = null;

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
    public void createCar() throws Exception{
        String content = objectMapper.writeValueAsString(car);
        String uri = "/greetings/cars";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));
    }


    @Test
    public void create() throws Exception{
        String content = objectMapper.writeValueAsString(car);
        String uri = "/greetings/cars";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated())
                .andDo(document(uri));
    }

    @Test
    public void read() throws Exception{
        createCar();
        String uri = "/greetings/cars";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void testRead() throws Exception{
        createCar();
        String uri = "/greetings/cars/1";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void update() throws Exception{
        createCar();
        String content = objectMapper.writeValueAsString(car2);
        String uri = "/greetings/cars/1";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void delete() throws Exception{
        createCar();
        String uri = "/greetings/cars/1";
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

//    *******************************************Null_Parameter Test****************************************************

    Car carNullParameter = new Car();

    @Test
    public void createNullParameter() throws Exception{
        String content = objectMapper.writeValueAsString(carNullParameter);
        String uri = "/greetings/cars";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isIAmATeapot())
                .andDo(document(uri));
    }

//    TODO не знаю как здесь поступить
    @Test
    public void testReadNullParameter() throws Exception{

        String uri = "/greetings/cars/{id}";
        Integer id = null;
        mockMvc.perform(get(uri, id))
                .andExpect(status().isNotFound())
                .andDo(document(uri));
    }

    @Test
    public void updateNullParameter() throws Exception{
        createCar();
        String content = objectMapper.writeValueAsString(carNullParameter);
        String uri = "/greetings/cars/1";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isNotModified())
                .andDo(document(uri));
    }

//    *******************************************Null Test**************************************************************

    Car carNull = null;

    @Test
    public void createNull() throws Exception{
        String content = objectMapper.writeValueAsString(carNull);
        String uri = "/greetings/cars";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isBadRequest())
                .andDo(document(uri));
    }

    @Test
    public void updateNull() throws Exception{
        createCar();
        String content = objectMapper.writeValueAsString(carNull);
        String uri = "/greetings/cars/1";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isBadRequest())
                .andDo(document(uri));
    }


}