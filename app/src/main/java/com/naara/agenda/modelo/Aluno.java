package com.naara.agenda.modelo;

import android.media.Image;

import java.io.Serializable;

/**
 * Created by naara on 07/02/17.
 */

public class Aluno implements Serializable {

    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String site;
    private String email;
    private double nota;
 //   private String foto;

//    public String getFoto() {
//        return foto;
//    }
//
//    public void setFoto(String foto) {
//        this.foto = foto;
//    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }


    public String toString(){
        return nome;
    }




}
