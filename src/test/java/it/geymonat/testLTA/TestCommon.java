package it.geymonat.testLTA;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.geymonat.testLTA.model.Auto;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class TestCommon {

    protected MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    protected List<Auto> getData(RequestBuilder requestBuilder) throws Exception {
        MvcResult data = mockMvc.perform(
                        requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        return mapper.reader().readValue(data.getResponse().getContentAsString(),List.class);
    }
}
