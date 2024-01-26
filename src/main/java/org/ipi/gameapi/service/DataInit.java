package org.ipi.gameapi.service;

import org.ipi.gameapi.models.Jeu;
import org.ipi.gameapi.repositories.JeuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@Configuration
public class DataInit {

    private static final Logger log = LoggerFactory.getLogger(DataInit.class);

    @Bean
    CommandLineRunner initJeuTable(JeuRepository jeuRepository) throws ParseException {
        log.info("Launch Preloading");

        if(jeuRepository.count() == 0){

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse("08/12/2023");

            Jeu jeu = new Jeu("THE FINALS","Rejoignez THE FINALS, le célèbre jeu gratuit axé sur le combat !" +
                    " Combattez aux côtés de vos coéquipiers dans des arènes virtuelles que vous pouvez altérer, " +
                    "exploiter et même détruire.", date);

            return args -> {
                log.info("Preloading" + jeuRepository.save(jeu));
            };
        }else {
            return args -> {
                log.info("Already initialized");
            };
        }
    }

}
