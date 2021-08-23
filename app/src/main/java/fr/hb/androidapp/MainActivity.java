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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "mainActivity";

    //Composants graphique
    private Button bt_ajouter,bt_ajouter_plusieurs,bt_supprimer;
    private TextView tv_console;
    private EditText et_prenom,et_nom;
    private SeekBar sb;
    //Données
    ArrayList<String> listNoms;
    ArrayList<String> listPrenoms;

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

        listNoms = new ArrayList<>();
        listPrenoms = new ArrayList<>();

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

        for(int i = 0; i<nb; i++){
            //tv_console.append(nom + " "+ prenom + i + "\n");
            listNoms.add(nom);
            listPrenoms.add(prenom +i);
        }

    }
    public void deleteLast(){

        if(listNoms.isEmpty()){
            Toast.makeText(this, "La liste est vide", Toast.LENGTH_SHORT).show();
        } else {
            listNoms.remove(listNoms.size()-1);
            listPrenoms.remove(listPrenoms.size()-1);
        }
    }
    public  void refreshConsole(){
        String result ="";

        for(int i= 0 ; i< listNoms.size(); i++){
            String nom = listNoms.get(i);
            String prenom = listPrenoms.get(i);
            result += nom + " " + prenom + "\n";
        }

        tv_console.setText(result);
    }
}