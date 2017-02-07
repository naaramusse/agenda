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

        aluno = new Aluno();
    }

    public Aluno getAluno(){
        aluno.setNome(nome.getText().toString());

        return aluno;
    }


}
