package org.ipi.gameapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "jeux")
public class Jeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ nom ne peut être null")
    @NotBlank(message = "Le champ nom ne peut être vide")
    private String name;

    @NotNull(message = "Le champ description ne peut être null")
    @NotBlank(message = "Le champ description ne peut être vide")
    private String description;

    @NotNull(message = "Le champ dateSortir ne peut être null")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateSortir;

    @OneToMany(mappedBy="jeu", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Avis> avis;

    public Jeu(String name, String description, Date dateSortir) {
        this.name = name;
        this.description = description;
        this.dateSortir = dateSortir;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }

    public Jeu() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateSortir() {
        return dateSortir;
    }

    public void setDateSortir(Date dateSortir) {
        this.dateSortir = dateSortir;
    }
}
