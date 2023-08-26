package it.geymonat.testLTA.repository;


import it.geymonat.testLTA.model.Auto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AutoRepository extends MongoRepository<Auto, String> {


}
