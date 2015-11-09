package com.example.ogodoy.tvfreak;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    // JSON Node names
    private static final String PARTIDOS = "partidos";
    private static final String HORAESTADO = "horaEstado";
    private static final String IDPARTIDO = "idPartido";
    private static final String HORA = "hora";
    private static final String CIUDAD = "ciudad";
    private static final String IDESTADO = "idEstado";
    private static final String NOMBREARBITRO = "nombreArbitro";
    private static final String GOLLOCAL = "golLocal";
    private static final String IDARBITRO = "idArbitro";
    private static final String ESCUDOVISITA = "escudoVisita";
    private static final String GOLVISITA = "golVisita";
    private static final String IDLOCAL = "idLocal";
    private static final String ESTADIO = "estadio";
    private static final String IDVISITA = "idVisita";
    private static final String ESTADO = "estado";
    private static final String ESCUDOLOCAL = "escudoLocal";
    private static final String NOMBREVISITA = "nombreVisita";
    private static final String NOMBRELOCAL = "nombreLocal";
    private static final String FECHA = "fecha";
    private static final String IMAGEN = "imagen";

    private ProgressDialog pDialog;
    // URL to get contacts JSON
    private static String url = "http://54.221.221.249:8888/Futbol/1.0/Resumen/Fixture?campeonato=chile&estado=anteriores";
    // contacts JSONArray
    JSONArray partidos = null;
    // Hashmap for ListView
    ArrayList<HashMap<String, String>> listaPartidos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaPartidos = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();

        // Listview on item click listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                // Starting single contact activity
                Intent in = new Intent(getApplicationContext(),PartidoActivity.class);

//                Bundle bdl1 = new Bundle();
//                bdl1.putString(HORA, partido.get(HORA));
//                in.putExtra("bdl1", bdl1);

                HashMap<String, String> partido = listaPartidos.get(position);

                in.putExtra(IDESTADO, partido.get(IDESTADO));
                in.putExtra(ESTADO, partido.get(ESTADO));
                in.putExtra(FECHA, partido.get(FECHA));
                in.putExtra(HORA, partido.get(HORA));

                in.putExtra(IDPARTIDO, partido.get(IDPARTIDO));
                in.putExtra(HORAESTADO, partido.get(HORAESTADO));
                in.putExtra(CIUDAD, partido.get(CIUDAD));
                in.putExtra(ESTADIO, partido.get(ESTADIO));
                in.putExtra(IDARBITRO, partido.get(IDARBITRO));
                in.putExtra(NOMBREARBITRO, partido.get(NOMBREARBITRO));

                in.putExtra(ESCUDOVISITA, partido.get(ESCUDOVISITA));
                in.putExtra(IDVISITA, partido.get(IDVISITA));
                in.putExtra(NOMBREVISITA, partido.get(NOMBREVISITA));
                in.putExtra(GOLVISITA, partido.get(GOLVISITA));

                in.putExtra(ESCUDOLOCAL, partido.get(ESCUDOLOCAL));
                in.putExtra(IDLOCAL, partido.get(IDLOCAL));
                in.putExtra(NOMBRELOCAL, partido.get(NOMBRELOCAL));
                in.putExtra(GOLLOCAL, partido.get(GOLLOCAL));

                startActivity(in);

            }
        });

        // Calling async task to get json
        new GetPartidos().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetPartidos extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray jsonArr = new JSONArray(jsonStr);

                    JSONObject jsonObj = jsonArr.getJSONObject(1);

                    // Getting JSON Array node
                    partidos = jsonObj.getJSONArray(PARTIDOS);

                    // looping through All Contacts
                    for (int i = 0; i < partidos.length(); i++) {
                        JSONObject c = partidos.getJSONObject(i);

                        // tmp hashmap for single contact
                        HashMap<String, String> partido = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        partido.put(IDESTADO, c.getString(IDESTADO));
                        partido.put(ESTADO, c.getString(ESTADO));
                        partido.put(FECHA, c.getString(FECHA));
                        partido.put(HORA, c.getString(HORA));
                        partido.put(IDPARTIDO, c.getString(IDPARTIDO));
                        partido.put(HORAESTADO, c.getString(HORAESTADO));
                        partido.put(CIUDAD, c.getString(CIUDAD));
                        partido.put(ESTADIO, c.getString(ESTADIO));
                        partido.put(IDARBITRO, c.getString(IDARBITRO));
                        partido.put(NOMBREARBITRO, c.getString(NOMBREARBITRO));

                        partido.put(ESCUDOVISITA, c.getString(ESCUDOVISITA));
                        partido.put(IDVISITA, c.getString(IDVISITA));
                        partido.put(NOMBREVISITA, c.getString(NOMBREVISITA));
                        partido.put(GOLVISITA, c.getString(GOLVISITA));

                        partido.put(ESCUDOLOCAL, c.getString(ESCUDOLOCAL));
                        partido.put(IDLOCAL, c.getString(IDLOCAL));
                        partido.put(NOMBRELOCAL, c.getString(NOMBRELOCAL));
                        partido.put(GOLLOCAL, c.getString(GOLLOCAL));

                        // adding contact to contact list
                        listaPartidos.add(partido);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            ListAdapter adapter =
                    new SimpleAdapterClone(
                            MainActivity.this,
                            listaPartidos,
                            R.layout.list_item,
                            new String[] { FECHA, NOMBRELOCAL, NOMBREVISITA, GOLLOCAL, GOLVISITA ,ESCUDOLOCAL, ESCUDOVISITA},
                            new int[] { R.id.txtFecha, R.id.txtEquipoLocal, R.id.txtEquipoVisita, R.id.txtGolLocal, R.id.txtGolVisita, R.id.imgLocal, R.id.imgVisita});

            setListAdapter(adapter);

        }

    }

}