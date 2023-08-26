package it.geymonat.testLTA.controller;

import it.geymonat.testLTA.model.Alimentazione;
import it.geymonat.testLTA.model.Anzianita;
import it.geymonat.testLTA.model.Auto;
import it.geymonat.testLTA.model.FasciaDiPrezzo;
import it.geymonat.testLTA.repository.AutoRepository;
import it.geymonat.testLTA.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private CarService carService;

    /*tutte le auto di anni 1 con cambio automatico
    quelle più vecchie a gasolio
    quelle con anzianità di tre anni, con fascia valore da 5k/10K e cambio automatico
    tutti i filtri hanno un dato opzionale che richiede il 4x4 sì / no (default no)*/

    //tutte le auto di anni 1 con cambio automatico
    @GetMapping(value ={"/anni1/cambioAutomatico","/anni1/cambioAutomatico/{trazione}"})
    public List<Auto> anni1Automatiche(@PathVariable(value = "trazione",required = false) Boolean trazione ) {
        Auto model = new Auto();
        model.setAnzianita(Anzianita.anni1);
        model.setCambioAutomatico(true);
        model.setTrazioneIntegrale(trazione);

        return carService.findByExample(model);
    }

    //    quelle più vecchie a gasolio
    @GetMapping(value ={"/anni5/gasolio","/anni5/gasolio/{trazione}"})
    public List<Auto> vecchieAGasolio(@PathVariable(value = "trazione",required = false) Boolean trazione ) {
        Auto model = new Auto();
        model.setAnzianita(Anzianita.anni5);
        model.setAlimentazione(Alimentazione.benzina);
        model.setTrazioneIntegrale(trazione);

        return carService.findByExample(model);
    }

    //    quelle con anzianità di tre anni, con fascia valore da 5k/10K e cambio automatico
    @GetMapping(value ={"/anni3/5to10k/cambioAutomatico","/anni3/5to10k/cambioAutomatico/{trazione}"})
    public List<Auto> anni3AutomaticheDa5a10k(@PathVariable(value = "trazione",required = false) Boolean trazione ) {
        Auto model = new Auto();
        model.setAnzianita(Anzianita.anni3);
        model.setFasciaDiPrezzo(FasciaDiPrezzo.da5Ka10K);
        model.setCambioAutomatico(true);
        model.setTrazioneIntegrale(trazione);

        return carService.findByExample(model);
    }


}
