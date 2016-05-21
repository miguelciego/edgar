package com.cps.edgar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class MedicoActivity extends AppCompatActivity {

    ListView ls2;

    private SimpleAdapter adpt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico);
        adpt = new SimpleAdapter(this, new ArrayList<Ninjas>());

        ListView ls1 = (ListView) findViewById(R.id.listView3);
        assert ls1 != null;
        ls1.setAdapter(adpt);

        (new AsyncListViewLoader()).execute("http://190.129.209.142:61183/mig/e_medico.php");

        ls1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>arg0, View arg1, int arg2, long arg3){
                //TextView g = (TextView) arg1.findViewById(R.id.nombre);
                //Toast.makeText(getApplicationContext(), g.getText(), Toast.LENGTH_SHORT).show();
                Intent ir = new Intent(getApplicationContext(), HoraFichaActivity.class);
                startActivity(ir);
            }

        });
    }

    public class AsyncListViewLoader extends AsyncTask<String, Void, List<Ninjas>> {

        private final ProgressDialog dialog = new ProgressDialog(MedicoActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dialog.setMessage("Downloading contacts...");
            //dialog.show();
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

                JSONObject respuestaJSON = new JSONObject(resulta.toString());
                String resultJSON = respuestaJSON.getString("Medicos");

                JSONArray especJSON = new JSONArray(resultJSON);
                String name = "valores";
                int tamano =especJSON.length();
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
