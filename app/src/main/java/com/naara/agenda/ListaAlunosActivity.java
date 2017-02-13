package com.naara.agenda;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.naara.agenda.dao.AlunoDAO;
import com.naara.agenda.modelo.Aluno;

import java.lang.Override;
import java.util.List;


public class ListaAlunosActivity extends AppCompatActivity {

    ListView listaAlunos;

    public int PERMISSION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        Button novoAluno = (Button) findViewById(R.id.novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });


    }


    public void carregaLista(){
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);

        listaAlunos.setAdapter(adapter);

        registerForContextMenu(listaAlunos);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);

                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
                //Toast.makeText(ListaAlunosActivity.this, aluno.getNome().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        carregaLista();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuItem del = menu.add("Deletar");
        MenuItem visitarSite = menu.add("Visitar site");
        MenuItem ligar = menu.add("Ligar para aluno");
        MenuItem sms = menu.add("Mandar sms");
        MenuItem mapa = menu.add("Achar no mapa");


        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        int index = info.position;

        final Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(index);

        final String nome = aluno.getNome();


        del.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deleta(aluno);
                dao.close();
                carregaLista();
                return true;
            }
        });

        visitarSite.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                if (aluno.getSite() != null){
                    intent.setData(Uri.parse(aluno.getSite()));
                }

                startActivity(intent);

                return true;
            }
        });

        sms.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                if (aluno.getTelefone() != null){
                    intent.setData(Uri.parse("sms:"+ aluno.getTelefone()));
                }

                startActivity(intent);

                return true;
            }
        });

        mapa.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                if (aluno.getEndereco() != null){
                    intent.setData(Uri.parse("geo:0,0?q="+ aluno.getEndereco()));
                }

                startActivity(intent);

                return true;
            }
        });

        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(Intent.ACTION_CALL);

                if (ContextCompat.checkSelfPermission(ListaAlunosActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(ListaAlunosActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                }else{

                    if (aluno.getTelefone() != null){
                        intent.setData(Uri.parse("tel:"+ aluno.getTelefone()));
                    }

                    startActivity(intent);
                }


                return true;
            }
        });

    }

    @Override
    public void onRequestPermissionsResult (int requestCode,
                                            String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(ListaAlunosActivity.this, "Aluno ", Toast.LENGTH_SHORT).show();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
