package devandroid.dutra.applistacurso.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import devandroid.dutra.applistacurso.R;
import devandroid.dutra.applistacurso.controller.CursoController;
import devandroid.dutra.applistacurso.controller.PessoaController;
import devandroid.dutra.applistacurso.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor listaVip;
    public static final String NOME_PREFERENCES = "pref_listavip";


    Pessoa pessoa;

    PessoaController controller;
    CursoController cursoController;

    List<String> nomesDosCursos;

    EditText editPrimeiroNome;
    EditText editSobreNome;
    EditText editNomeDoCurso;
    EditText editTelefoneDeContato;

    Button btnFinalizar;
    Button btnSalvar;
    Button btnLimpar;

    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);


        preferences = getSharedPreferences(NOME_PREFERENCES, 0);
        listaVip = preferences.edit();


        controller = new PessoaController(MainActivity.this);
        controller.toString();

        cursoController = new CursoController();
        nomesDosCursos = cursoController.dadosParaSpinner();

        pessoa = new Pessoa();
        controller.buscar(pessoa);


        pessoa.setPrimeiroNome("Victor");
        pessoa.setSobreNome("Antonio");
        pessoa.setCursoDesejado("Android");
        pessoa.setTelefoneContato("77988441546");

        Log.i("POOAndroid", pessoa.toString());

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobreNome = findViewById(R.id.editSobreNome);
        editNomeDoCurso = findViewById(R.id.editNomeDoCurso);
        editTelefoneDeContato = findViewById(R.id.editTelefoneDeContato);
        spinner = findViewById(R.id.spinner);

        editPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editSobreNome.setText(pessoa.getSobreNome());
        editNomeDoCurso.setText(pessoa.getCursoDesejado());
        editTelefoneDeContato.setText(pessoa.getTelefoneContato());

        btnFinalizar = findViewById(R.id.btnFinalizar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                cursoController.dadosParaSpinner());
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);


        editPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editSobreNome.setText(pessoa.getSobreNome());
        editNomeDoCurso.setText(pessoa.getCursoDesejado());
        editTelefoneDeContato.setText(pessoa.getTelefoneContato());

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPrimeiroNome.setText("");
                editSobreNome.setText("");
                editNomeDoCurso.setText("");
                editTelefoneDeContato.setText("");


                controller.limpar();

                listaVip.clear();
                listaVip.apply();


            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Volte sempre!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pessoa.setPrimeiroNome(editPrimeiroNome.getText().toString());
                pessoa.setSobreNome(editSobreNome.getText().toString());
                pessoa.setCursoDesejado(editNomeDoCurso.getText().toString());
                pessoa.setTelefoneContato(editTelefoneDeContato.getText().toString());


                Toast.makeText(MainActivity.this, "Salvo!" + pessoa.toString(), Toast.LENGTH_LONG).show();


                controller.salvar(pessoa);


            }
        });


    }
}