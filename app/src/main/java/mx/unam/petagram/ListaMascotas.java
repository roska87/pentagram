package mx.unam.petagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ListaMascotas extends AppCompatActivity {

    private List<Mascota> mascotas;
    private RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);

        Toolbar actionBar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionBar);

        listaMascotas = (RecyclerView) findViewById(R.id.recyclerListaMascotas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(linearLayoutManager);
        inicializarListaMascotas();
        inicializarAdaptador();
    }

    public void inicializarAdaptador(){

        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);

    }

    public void inicializarListaMascotas(){

        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.fox, "Kira"));
        mascotas.add(new Mascota(R.drawable.bear, "Nero"));
        mascotas.add(new Mascota(R.drawable.elephant, "Lola"));
        mascotas.add(new Mascota(R.drawable.sheep, "Gardfiel"));
        mascotas.add(new Mascota(R.drawable.monkey, "Simon"));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.mAbout:

                break;
            case R.id.mFavoritos:
                Intent i = new Intent(this, MascotasFavoritas.class);
                startActivity(i);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
