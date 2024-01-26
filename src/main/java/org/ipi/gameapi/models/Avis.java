package org.ipi.gameapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "avis")
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ note ne peut être null")
    @NotBlank(message = "Le champ note ne peut être vide")
    private String note;

    @NotNull(message = "Le champ description ne peut être null")
    @NotBlank(message = "Le champ description ne peut être vide")
    private String description;

    @NotNull(message = "Le champ dateEnvoie ne peut être null")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateEnvoie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @NotNull(message = "Le champ dateEnvoie ne peut être null")
    private Jeu jeu;

    public Avis(String note, String description, Date dateEnvoie, Jeu jeu) {
        this.note = note;
        this.description = description;
        this.dateEnvoie = dateEnvoie;
        this.jeu = jeu;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public Avis() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateEnvoie() {
        return dateEnvoie;
    }

    public void setDateEnvoie(Date dateEnvoie) {
        this.dateEnvoie = dateEnvoie;
    }
}
