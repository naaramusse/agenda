package com.naara.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView foto;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        nome = (EditText) activity.findViewById(R.id.formulario_nome);
        endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        site = (EditText) activity.findViewById(R.id.formulario_site);
        email = (EditText) activity.findViewById(R.id.formulario_email);
        nota = (RatingBar) activity.findViewById(R.id.formulario_nota);

//        if (aluno.getFoto() != null){
//            carregarFoto(aluno.getFoto());
//        }

        aluno = new Aluno();
    }

//    public void carregarFoto(String localFoto){
//
//        Bitmap foto = BitmapFactory.decodeFile(localFoto);
//
//        Bitmap fotoReduzida = Bitmap.createScaledBitmap(foto, 100, 100, true);
//
//        aluno.setFoto(localFoto);
//
//        this.foto.setImageBitmap(fotoReduzida);
//    }

    public Aluno pegaAluno(){
        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setNota(nota.getRating());

        return aluno;
    }


    public void preencheFormulario(Aluno aluno) {
        this.aluno = aluno;

        this.nome.setText(aluno.getNome());
        this.endereco.setText(aluno.getEndereco());
        this.telefone.setText(aluno.getTelefone());
        this.site.setText(aluno.getSite());
        this.email.setText(aluno.getEmail());
        this.nota.setProgress((int) aluno.getNota());
    }
}
