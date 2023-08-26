package it.geymonat.testLTA.service;

import it.geymonat.testLTA.model.Auto;
import it.geymonat.testLTA.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private AutoRepository autoRepository;
    public List<Auto> findByExample(Auto input) {
        Example<Auto> example = Example.of(input);
        return autoRepository.findAll(example);
    }
}
