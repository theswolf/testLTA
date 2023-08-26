package it.geymonat.testLTA;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.geymonat.testLTA.model.Auto;
import it.geymonat.testLTA.repository.AutoRepository;
import it.geymonat.testLTA.service.CsvService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(classes = TestLtaApplication.class)
@WebAppConfiguration
public class TestRest extends TestCommon {

    @Autowired
    CsvService csvService;

    @Autowired
    private AutoRepository autoRepository;



    @Autowired
    private WebApplicationContext webApplicationContext;



    @BeforeEach
    public void setUp() throws IOException {
        autoRepository.deleteAll();
        mockMvc = webAppContextSetup(webApplicationContext).build();
        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.csv");
        MockMultipartFile csv = new MockMultipartFile("file", "data.csv", "text/plain", inputStream);
        csvService.loadCsv(csv);
    }




    @Test
    public void testanni1Automatiche() throws Exception {

        List<Auto> all = getData(MockMvcRequestBuilders.
                get("/anni1/cambioAutomatico"));
        List<Auto> all4x4 = getData(MockMvcRequestBuilders.
                get("/anni1/cambioAutomatico/true"));
        List<Auto> all4x2 = getData(MockMvcRequestBuilders.
                get("/anni1/cambioAutomatico/false"));

        assertEquals(all.size(), all4x4.size() + all4x2.size());

    }

    @Test
    public void testVecchieAGasolio() throws Exception {

        List<Auto> all = getData(MockMvcRequestBuilders.
                get("/anni5/gasolio"));
        List<Auto> all4x4 = getData(MockMvcRequestBuilders.
                get("/anni5/gasolio/true"));
        List<Auto> all4x2 = getData(MockMvcRequestBuilders.
                get("/anni5/gasolio/false"));

        assertEquals(all.size(), all4x4.size() + all4x2.size());

    }

    @Test
    public void testAnni3AutomaticheDa5a10k() throws Exception {

        List<Auto> all = getData(MockMvcRequestBuilders.
                get("/anni3/5to10k/cambioAutomatico"));
        List<Auto> all4x4 = getData(MockMvcRequestBuilders.
                get("/anni3/5to10k/cambioAutomatico/true"));
        List<Auto> all4x2 = getData(MockMvcRequestBuilders.
                get("/anni3/5to10k/cambioAutomatico/false"));

        assertEquals(all.size(), all4x4.size() + all4x2.size());

    }

}
