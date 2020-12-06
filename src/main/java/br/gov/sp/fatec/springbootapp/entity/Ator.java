package br.gov.sp.fatec.springbootapp.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.controller.View;

@Entity
@DiscriminatorValue(value = "A")
public class Ator extends Pessoa {

    @JsonView(View.Ator.class)
    @Column(name = "atr_fama")
    private String fama;

    public String getFama() {
        return fama;
    }

    public void setFama(String fama) {
        this.fama = fama;
    }
}