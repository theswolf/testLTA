package it.geymonat.testLTA.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Auto {

    @Id
    private String id;
    private Boolean trazioneIntegrale;
    private FasciaDiPrezzo fasciaDiPrezzo;
    private Anzianita anzianita;
    private Boolean  cambioAutomatico;
    private Alimentazione alimentazione;
}
