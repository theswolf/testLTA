package it.geymonat.testLTA;

import it.geymonat.testLTA.model.Auto;
import it.geymonat.testLTA.repository.AutoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(classes = TestLtaApplication.class)
@WebAppConfiguration
public class TestUpload extends TestCommon{

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        autoRepository.deleteAll();
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testUpload() throws Exception {
        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.csv");
        final   MockMultipartFile csv = new MockMultipartFile("file", "data.csv", "text/plain", inputStream);

        long start = autoRepository.count();
        assertEquals(start, 0);

        List<Auto> data = getData(fileUpload("/upload").file(csv));

        long result = autoRepository.count();
        assertEquals(data.size(), result);
    }

}
