package com.example.ogodoy.tvfreak;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class PartidoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido);


        TextView txtidEstado = (TextView)findViewById(R.id.txtidEstado);
        TextView txtestado = (TextView)findViewById(R.id.txtestado);
        TextView txtfecha = (TextView)findViewById(R.id.txtfecha);
        TextView txthora = (TextView)findViewById(R.id.txthora);
        TextView txtidPartido = (TextView)findViewById(R.id.txtidPartido);
        TextView txthoraEstado = (TextView)findViewById(R.id.txthoraEstado);
        TextView txtciudad = (TextView)findViewById(R.id.txtciudad);
        TextView txtestadio = (TextView)findViewById(R.id.txtestadio);
        TextView txtidArbitro = (TextView)findViewById(R.id.txtidArbitro);
        TextView txtnombreArbitro = (TextView)findViewById(R.id.txtnombreArbitro);

        ImageView imgescudoVisita = (ImageView)findViewById(R.id.imgescudoVisita);
        TextView txtidVisita = (TextView)findViewById(R.id.txtidVisita);
        TextView txtnombreVisita = (TextView)findViewById(R.id.txtnombreVisita);
        TextView txtgolVisita = (TextView)findViewById(R.id.txtgolVisita);

        ImageView imgescudoLocal = (ImageView)findViewById(R.id.imgescudoLocal);
        TextView txtgolLocal = (TextView)findViewById(R.id.txtgolLocal);
        TextView txtidLocal = (TextView)findViewById(R.id.txtidLocal);
        TextView txtnombreLocal = (TextView)findViewById(R.id.txtnombreLocal);



        Bundle bdl1 = this.getIntent().getExtras();

        txtidEstado.setText(bdl1.getString("idEstado"));
        txtestado.setText(bdl1.getString("estado"));
        txtfecha.setText(bdl1.getString("fecha"));
        txthora.setText(bdl1.getString("hora"));
        txtidPartido.setText(bdl1.getString("idPartido"));
        txthoraEstado.setText(bdl1.getString("horaEstado"));
        txtciudad.setText(bdl1.getString("ciudad"));
        txtestadio.setText(bdl1.getString("estadio"));
        txtidArbitro.setText(bdl1.getString("idArbitro"));
        txtnombreArbitro.setText(bdl1.getString("nombreArbitro"));

        new DownloadTask(imgescudoVisita).execute(bdl1.getString("escudoVisita"));
        txtidVisita.setText(bdl1.getString("idVisita"));
        txtnombreVisita.setText(bdl1.getString("nombreVisita"));
        txtgolVisita.setText(bdl1.getString("golVisita"));

        new DownloadTask(imgescudoLocal).execute(bdl1.getString("escudoLocal"));
        txtgolLocal.setText(bdl1.getString("golLocal"));
        txtidLocal.setText(bdl1.getString("idLocal"));
        txtnombreLocal.setText(bdl1.getString("nombreLocal"));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_partido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
