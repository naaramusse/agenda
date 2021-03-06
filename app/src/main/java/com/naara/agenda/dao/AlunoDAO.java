package com.naara.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.naara.agenda.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by naara on 08/02/17.
 */

public class AlunoDAO extends SQLiteOpenHelper {

    public AlunoDAO(Context context) {
        super(context, "listaDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String ddl = "CREATE TABLE " + "Alunos" + "( id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, site TEXT, email TEXT, telefone TEXT, nota REAL)";
        db.execSQL(ddl);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS Alunos" ;

        db.execSQL(sql);

        onCreate(db);

    }

    public ContentValues pegaDadosAluno(Aluno aluno){
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("site", aluno.getSite());
        dados.put("email", aluno.getEmail());
        dados.put("nota", aluno.getNota());

        return dados;
    }

    public void insere(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("site", aluno.getSite());
        dados.put("email", aluno.getEmail());
        dados.put("nota", aluno.getNota());

        db.insert("Alunos", null, dados);

    }

    public List<Aluno> buscaAlunos(){
        SQLiteDatabase db = getWritableDatabase();

        String sql = "select * from Alunos";

        Cursor c = getReadableDatabase().rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<Aluno>();

        while (c.moveToNext()){
            Aluno aluno = new Aluno();

            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setEmail(c.getString(c.getColumnIndex("email")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));

            alunos.add(aluno);
        }
        c.close();
        return alunos;
    }

    public void deleta(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();

        String[] args = {aluno.getId().toString()};

        db.delete("Alunos", "id = ?", args);

    }

    public void alteraAluno(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosAluno(aluno);

        String[] args = {aluno.getId().toString()};

        db.update("Alunos", dados, "id = ?", args);
    }
}
