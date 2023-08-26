package it.geymonat.testLTA.service;

import com.opencsv.bean.CsvToBeanBuilder;
import it.geymonat.testLTA.model.Auto;
import it.geymonat.testLTA.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CsvService {

    @Autowired
    private AutoRepository autoRepository;

    public List<Auto> loadCsv(MultipartFile csvFile) throws IOException {

        List<Auto> beans = new CsvToBeanBuilder(new InputStreamReader(csvFile.getInputStream()))
                .withType(Auto.class)
                .build()
                .parse();

       return autoRepository.saveAll(beans);
    }
}
