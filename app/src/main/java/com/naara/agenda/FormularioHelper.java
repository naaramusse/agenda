package com.naara.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import com.naara.agenda.modelo.Aluno;

/**
 * Created by naara on 07/02/17.
 */

public class FormularioHelper {

    private EditText nome;
    private EditText email;
    private EditText endereco;
    private EditText site;
    private EditText telefone;
    private RatingBar nota;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        nome = (EditText) activity.findViewById(R.id.formulario_nome);
        email = (EditText) activity.findViewById(R.id.formulario_email);
        endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        site = (EditText) activity.findViewById(R.id.formulario_site);
        telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        nota = (RatingBar) activity.findViewById(R.id.formulario_nota);

        aluno = new Aluno();
    }

    public Aluno pegaAluno(){
        aluno.setNome(nome.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setNota(nota.getRating());

        return aluno;
    }


}
