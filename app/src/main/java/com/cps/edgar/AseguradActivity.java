package com.cps.edgar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AseguradActivity extends AppCompatActivity {


    ListView ls;
    String IP = "http://192.168.9.18/scz/servaseg/index.php?cod=";
    String GET_ESPEC = IP + "/e_espec.php";
    String GET_MEDI = IP + "/e_medi.php";
    String GET_HORA = IP + "/e_hora.php";

    JSONArray especJSON=null;
    private SimpleAdapter adpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asegurad);

        Ninjas2 ninjas_datos[] = new Ninjas2[]{
                new Ninjas2(R.mipmap.ic_launcher,"Edgar Enrique Cabello Peña", "Asegurado"),
                new Ninjas2(R.mipmap.ic_launcher,"Enly Yhara Cabello Peña" ,"Beneficiario"),
                new Ninjas2(R.mipmap.ic_launcher,"Lidia Peña" ,"Beneficiario"),
                new Ninjas2(R.mipmap.ic_launcher,"Enrique Cabello Cruz", "Beneficiario"),
        };

        NinjasAdapter adapter = new NinjasAdapter(this,R.layout.renglonaseg, ninjas_datos);

        ls = (ListView) findViewById(R.id.listView);
        ls.setAdapter(adapter);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                TextView v = (TextView) arg1.findViewById(R.id.titulo);
                //TextView v1 = (TextView) arg1.findViewById(R.id.subtitulo);
                //Toast.makeText(getApplicationContext(), v.getText(), Toast.LENGTH_SHORT).show();
                Intent ir = new Intent(getApplicationContext(), EspecialidadActivity.class);
                startActivity(ir);
            }

        });

      //  Bundle bundle = getIntent().getExtras();
      //  String matraseg = bundle.getString("matricula");
        //Toast.makeText(getApplicationContext(), matraseg, Toast.LENGTH_SHORT).show();
       // IP = IP + matraseg;
       // adpt = new SimpleAdapter(this, new ArrayList<Ninjas>());

      //  ListView ls1 = (ListView) findViewById(R.id.listView);
       // assert ls1 != null;
      //  ls1.setAdapter(adpt);

       // (new AsyncListViewLoader()).execute(IP);

      //  ls1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
       //     @Override
      //      public void onItemClick(AdapterView<?>arg0, View arg1, int arg2, long arg3){
                //TextView g = (TextView) arg1.findViewById(R.id.nombre);
                //Toast.makeText(getApplicationContext(), g.getText(), Toast.LENGTH_SHORT).show();
        //        Intent ir = new Intent(getApplicationContext(), EspecialidadActivity.class);
      //           startActivity(ir);
        //    }

     //   });

    }

    public class AsyncListViewLoader extends AsyncTask<String, Void, List<Ninjas>> {

        private final ProgressDialog dialog = new ProgressDialog(AseguradActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dialog.setMessage("Downloading contacts...");
           // dialog.show();
        }

        @Override
        protected void onPostExecute(List<Ninjas> result) {
            super.onPostExecute(result);
            adpt.setItemList(result);
            adpt.notifyDataSetChanged();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(List<Ninjas> result) {
            super.onCancelled(result);
        }

        @Override
        protected List<Ninjas> doInBackground(String... params) {

            List<Ninjas> result = new ArrayList<Ninjas>();
            try
            {

                URL u = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                StringBuilder resulta = new StringBuilder();
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    resulta.append(line);
                }

               // JSONObject respuestaJSON = new JSONObject(resulta.toString());
               String resultJSON = resulta.toString();

                //JSONArray especJSON = new JSONArray(resulta.);
                String name = "valores";
                int tamano =resulta.length();
                for (int i=0; i<especJSON.length();i++)
                {
                    String p = especJSON.getString(i);
                    // String g = "Valido";
                    //new Ninjas(R.mipmap.ic_logocps,);
                    result.add(convertLista(p));
                    int logo = R.mipmap.ic_logocps;
                    name = name + p;
                }

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
    private Ninjas convertLista(String j) {

        // String g = "No se conecto";
        // Toast.makeText(getApplicationContext(), g, Toast.LENGTH_SHORT).show();
        int logo = R.mipmap.ic_logocps;
        String name = j;

        return new Ninjas(logo,name);
    }
}
