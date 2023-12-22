package devandroid.dutra.appgaseta.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import devandroid.dutra.appgaseta.R;
import devandroid.dutra.appgaseta.apoio.UtilGasEta;
import devandroid.dutra.appgaseta.controller.CombustivelController;
import devandroid.dutra.appgaseta.model.Combustivel;

public class GasEtaActivity extends AppCompatActivity {
    CombustivelController controller;

    Combustivel combustivelGasolina;
    Combustivel combustivelEtanol;
    Combustivel combustivel;
    EditText editGasolina;
    EditText editEtanol;

    TextView txtResultado;

    Button btnCalcular;
    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    double precoGasolina;
    double precoEtanol;
    String recomendacao;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gaseta);
        controller = new CombustivelController(GasEtaActivity.this);


        editGasolina = findViewById(R.id.editGasolina);
        editEtanol = findViewById(R.id.editEtanol);

        txtResultado = findViewById(R.id.txtResultado);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnFinalizar = findViewById(R.id.btnFinalizar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isDadosok = true;

                if(TextUtils.isEmpty(editGasolina.getText())){
                    editGasolina.setError("* Obrigatório");
                    editGasolina.requestFocus();
                    isDadosok = false;
                }

                if(TextUtils.isEmpty(editEtanol.getText())){
                    editEtanol.setError("* Obrigatório");
                    editEtanol.requestFocus();
                    isDadosok = false;
                }

                if(isDadosok){
                    precoGasolina = Double.parseDouble(editGasolina.getText().toString());
                    precoEtanol = Double.parseDouble(editEtanol.getText().toString());

                    recomendacao = UtilGasEta.calcularMelhorOpcao(precoGasolina,precoEtanol);
                    txtResultado.setText(recomendacao);

                    btnSalvar.setEnabled(true);

                }else{
                    Toast.makeText(GasEtaActivity.this, "Por favor. Digite os dados obrigatórios!",
                            Toast.LENGTH_LONG).show();
                    btnSalvar.setEnabled(false);
                }

            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GasEtaActivity.this, "Boa Economia!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                combustivelGasolina = new Combustivel();
                combustivelEtanol = new Combustivel();

                combustivelGasolina.setNomeDoCombustivel("gasolina");
                combustivelGasolina.setPrecoDoCombustivel(precoGasolina);

                combustivelEtanol.setNomeDoCombustivel("Etanol");
                combustivelEtanol.setPrecoDoCombustivel(precoEtanol);

                combustivelGasolina.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina,precoEtanol));
                combustivelEtanol.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina,precoEtanol));

                controller.salvar(combustivelGasolina);
                controller.salvar(combustivelEtanol);

            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEtanol.setText("");
                editGasolina.setText("");

                btnSalvar.setEnabled(false);

                controller.limpar();

            }
        });

        Toast.makeText(GasEtaActivity.this,
                UtilGasEta.calcularMelhorOpcao(5.12, 3.99),
                Toast.LENGTH_LONG).show();
    }
}
