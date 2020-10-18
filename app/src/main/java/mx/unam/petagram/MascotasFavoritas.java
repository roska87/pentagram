package mx.unam.petagram;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MascotasFavoritas extends AppCompatActivity {

    private List<Mascota> mascotasFAV;
    private RecyclerView listaMascotasFAV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        Toolbar actionBar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotasFAV = (RecyclerView) findViewById(R.id.recyclerListaMascotasFAV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotasFAV.setLayoutManager(linearLayoutManager);
        inicializarListaMascotas();
        inicializarAdaptador();
    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotasFAV, this);
        listaMascotasFAV.setAdapter(adaptador);
    }

    public void inicializarListaMascotas(){
        mascotasFAV = new ArrayList<Mascota>();
        mascotasFAV.add(new Mascota(R.drawable.fox, "Kira"));
        mascotasFAV.add(new Mascota(R.drawable.bear, "Nero"));
        mascotasFAV.add(new Mascota(R.drawable.elephant, "Lola"));
        mascotasFAV.add(new Mascota(R.drawable.sheep, "Gardfiel"));
        mascotasFAV.add(new Mascota(R.drawable.monkey, "Simon"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_2, menu);
        return true;
    }

}
