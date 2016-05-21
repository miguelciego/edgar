package com.cps.edgar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Settings.Secure;


public class MainActivity extends AppCompatActivity {


    EditText ed_matricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_matricula = (EditText) findViewById(R.id.editText);

        Button boton2 = (Button) findViewById(R.id.acceso);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ir = new Intent(getApplicationContext(), AseguradActivity.class);
                ir.putExtra("matricula", ed_matricula.getText().toString());
                startActivity(ir);
            }
        });

        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {

                editText.setHint("");
            }
        });

    }
}
