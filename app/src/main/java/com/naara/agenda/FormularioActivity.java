package com.naara.agenda;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.naara.agenda.dao.AlunoDAO;
import com.naara.agenda.modelo.Aluno;

import java.io.File;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        final Intent intent = getIntent();

        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if (aluno != null){
            helper.preencheFormulario(aluno);
        }

        ImageButton novaFoto = (ImageButton) findViewById(R.id.nova_foto);
        novaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File imagePath = new File(FormularioActivity.this.getFilesDir(), "images");
                File novaFoto = new File(imagePath, "teste.jpg");
                Uri contentUri = FileProvider.getUriForFile(FormularioActivity.this, "com.naara.agenda.fileprovider", novaFoto);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 564);
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 564) {
            File path = new File(getFilesDir(), "images");
            if (!path.exists()) path.mkdirs();
            File imageFile = new File(path, "image" + System.currentTimeMillis() + ".jpg");

            Bundle extras = data.getExtras();

            Bitmap foto = (Bitmap) extras.get("data");
            ImageView view = (ImageView) findViewById(R.id.formulario_foto);
            view.setImageBitmap(foto);

//
//            File path = new File(getFilesDir(), "/images");
//            if (!path.exists())path.mkdirs();
//            File imageFile = new File(path, "image" + System.currentTimeMillis() + ".jpg");
//            Uri contentUri = FileProvider.getUriForFile(FormularioActivity.this, "com.naara.agenda.fileprovider", imageFile);
//            FormularioActivity.this.grantUriPermission("com.android.providers.media.MediaProvider", contentUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            // Bundle extras = data.getExtras();
//
//            Bitmap foto = BitmapFactory.decodeFile(imageFile.getPath());
//            ImageView view = (ImageView) findViewById(R.id.formulario_foto);
//            view.setImageBitmap(foto);

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        double nota = helper.pegaAluno().getNota();
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.pegaAluno();

                AlunoDAO dao = new AlunoDAO(this);

                if (aluno.getId() == null){
                    dao.insere(aluno);
                } else {
                    dao.alteraAluno(aluno);
                }

                dao.close();

                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " salvo!", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
