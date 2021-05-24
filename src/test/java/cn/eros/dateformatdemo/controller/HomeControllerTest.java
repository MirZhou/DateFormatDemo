package cn.eros.dateformatdemo.controller;

import cn.eros.dateformatdemo.config.ControllerAdviceInitBinder;
import cn.eros.dateformatdemo.config.DateFormatConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@ContextConfiguration(classes = {
    HomeController.class
})
@WebMvcTest
@Import({DateFormatConfig.class, ControllerAdviceInitBinder.class})
class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    void getDate() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/date")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andReturn();

        String resultStr = result.getResponse().getContentAsString();
        Assertions.assertFalse(resultStr.contains("T"));

        DateVO resultDate = this.objectMapper.readValue(resultStr, DateVO.class);
        Assertions.assertNotNull(resultDate.getCol1());
        Assertions.assertNotNull(resultDate.getCol2());
        Assertions.assertNotNull(resultDate.getCol3());
    }

    @Test
    void queryDate() throws Exception {
        DateQuery dateQuery = new DateQuery();
        dateQuery.setLocalDate(LocalDate.now().minusDays(1L));
        dateQuery.setLocalDateTime(LocalDateTime.now().plusDays(1L));

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/query/date")
            .param("localDate", dateQuery.getLocalDate().toString())
            .param("localDateTime", dateQuery.getLocalDateTime().format(dateTimeFormatter))
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andReturn();

        String resultStr = result.getResponse().getContentAsString();

        Assertions.assertTrue(resultStr.contains(dateQuery.getLocalDate().toString()));
        Assertions.assertTrue(resultStr.contains(dateQuery.getLocalDateTime().format(dateTimeFormatter)));
        Assertions.assertNotNull(this.objectMapper.readValue(resultStr, DateQuery.class));
    }

    @Test
    void testQueryDate() throws Exception {
        DateTimeQuery dateQuery = new DateTimeQuery();
        dateQuery.setOldDate(new Date());
        dateQuery.setLocalDate(LocalDate.now().minusDays(1L));
        dateQuery.setLocalDateTime(LocalDateTime.now().plusDays(1L));
        dateQuery.setLocalTime(LocalTime.now().minusHours(3L));

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/query/date1")
            .param("oldDate", this.simpleDateFormat.format(dateQuery.getOldDate()))
            .param("localDate", dateQuery.getLocalDate().toString())
            .param("localDateTime", dateQuery.getLocalDateTime().format(dateTimeFormatter))
            .param("localTime", dateQuery.getLocalTime().format(timeFormatter))
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andReturn();

        String resultStr = result.getResponse().getContentAsString();

        Assertions.assertTrue(resultStr.contains(this.simpleDateFormat.format(dateQuery.getOldDate())));
        Assertions.assertTrue(resultStr.contains(dateQuery.getLocalDate().toString()));
        Assertions.assertTrue(resultStr.contains(dateQuery.getLocalDateTime().format(dateTimeFormatter)));
        Assertions.assertTrue(resultStr.contains(dateQuery.getLocalTime().format(timeFormatter)));
        Assertions.assertNotNull(this.objectMapper.readValue(resultStr, DateTimeQuery.class));
    }

    @Test
    void postQueryDate() throws Exception {
        DateTimeQuery dateQuery = new DateTimeQuery();
        dateQuery.setOldDate(new Date());
        dateQuery.setLocalDate(LocalDate.now().minusDays(1L));
        dateQuery.setLocalDateTime(LocalDateTime.now().plusDays(1L));
        dateQuery.setLocalTime(LocalTime.now().minusHours(3L));

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/query/date1")
            .content(this.objectMapper.writeValueAsString(dateQuery))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andReturn();

        String resultStr = result.getResponse().getContentAsString();

        Assertions.assertTrue(resultStr.contains(this.simpleDateFormat.format(dateQuery.getOldDate())));
        Assertions.assertTrue(resultStr.contains(dateQuery.getLocalDate().toString()));
        Assertions.assertTrue(resultStr.contains(dateQuery.getLocalDateTime().format(dateTimeFormatter)));
        Assertions.assertTrue(resultStr.contains(dateQuery.getLocalTime().format(timeFormatter)));
        Assertions.assertNotNull(this.objectMapper.readValue(resultStr, DateTimeQuery.class));
    }
}