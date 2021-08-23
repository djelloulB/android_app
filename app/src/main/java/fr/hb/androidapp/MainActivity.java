package fr.hb.androidapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "mainActivity";

    //Composants graphique
    private Button bt_ajouter,bt_ajouter_plusieurs,bt_supprimer;
    private TextView tv_console;
    private EditText et_prenom,et_nom;
    private SeekBar sb;
    //Données
    ArrayList<EleveBean> eleves;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_console = findViewById(R.id.tv_console);

        bt_ajouter_plusieurs = findViewById(R.id.bt_ajouter_plusieurs);
        bt_supprimer= findViewById(R.id.bt_supprimer);
        bt_ajouter = findViewById(R.id.bt_ajouter);
        et_nom = findViewById(R.id.et_nom);
        et_prenom= findViewById(R.id.et_prenom);
        sb = findViewById(R.id.seekBar);

        //Redirige le clic sur la methode Onclick
        bt_ajouter.setOnClickListener(this);
        bt_supprimer.setOnClickListener(this);
        bt_ajouter_plusieurs.setOnClickListener(this);

        eleves = new ArrayList<>();


        tv_console.setText("");
    }

    @Override
    public void onClick(View view) {
        String nom, prenom;
        switch(view.getId()){
            case R.id.bt_ajouter:
                 nom = et_nom.getText().toString();
                 prenom = et_prenom.getText().toString();

                ajouter(nom,prenom,1);

                refreshConsole();

                break;
            case R.id.bt_ajouter_plusieurs:
                 nom = et_nom.getText().toString();
                 prenom = et_prenom.getText().toString();
                int nb = sb.getProgress();
                ajouter(nom,prenom,nb);

                refreshConsole();

                break;
            case R.id.bt_supprimer:
                    deleteLast();
                    refreshConsole();
                break;

            default :
                break;

        }
    }


    public void ajouter(String nom, String prenom,int nb){
        if(nom.length() == 0){
            Toast.makeText(this, "Le nom est vide", Toast.LENGTH_SHORT).show();
            return;
        }
        if(prenom.length() == 0){
            Toast.makeText(this, "Le prénom est vide", Toast.LENGTH_SHORT).show();
            return;
        }

        Random rd = new Random();
        for(int i = 0; i<nb; i++){
            //tv_console.append(nom + " "+ prenom + i + "\n");

            EleveBean eleve = new EleveBean();
            eleve.setNom(nom);
            //eleve.nom = nom;
            eleve.setPrenom(prenom + eleves.size());
            //eleve.prenom = prenom + eleves.size();
            eleve.setAge(rd.nextInt(100));
            //eleve.age = rd.nextInt(100);

            eleves.add(eleve);
        }

    }
    public void deleteLast(){

        if(eleves.isEmpty()){
            Toast.makeText(this, "La liste est vide", Toast.LENGTH_SHORT).show();
        } else {
            eleves.remove(eleves.size() -1);

        }
    }
    public  void refreshConsole(){
        String result ="";


///////////////////////// old Method /////////////////////////////////////

       /* for(int i= 0 ; i< listNoms.size(); i++){
            String nom = listNoms.get(i);
            String prenom = listPrenoms.get(i);
            result += nom + " " + prenom + "\n";
        }*/


/////////////////////////////////////////////////////////////////////////
//        for(int i= 0 ; i< eleves.size(); i++){
//            EleveBean eleve = eleves.get(i);
//            result += eleve.nom + " " + eleve.prenom + "\n";
//        }


        //***************** forEach *************//
        for (EleveBean eleve: eleves  ) {
            result += eleve.getNom() + " " + eleve.getPrenom() + "\n";
        }

        tv_console.setText(result);
    }
 ////////////////////////////////////////////////////////////////////////
}