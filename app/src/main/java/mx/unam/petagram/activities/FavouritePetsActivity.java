package mx.unam.petagram.activities;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mx.unam.petagram.Pet;
import mx.unam.petagram.adapters.PetAdapter;
import mx.unam.petagram.R;

public class FavouritePetsActivity extends AppCompatActivity {

    private List<Pet> petsFAV;
    private RecyclerView petListFAV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_pets);

        Toolbar actionBar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        petListFAV = (RecyclerView) findViewById(R.id.recyclerPetListFAV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        petListFAV.setLayoutManager(linearLayoutManager);

        initializePetList();
        initializeAdapter();
    }

    public void initializeAdapter(){
        PetAdapter adapter = new PetAdapter(petsFAV, this);
        petListFAV.setAdapter(adapter);
    }

    public void initializePetList(){
        petsFAV = new ArrayList<>();
        petsFAV.add(new Pet(R.drawable.fox, "Foxy"));
        petsFAV.add(new Pet(R.drawable.bear, "Yogi"));
        petsFAV.add(new Pet(R.drawable.elephant, "Donphy"));
        petsFAV.add(new Pet(R.drawable.sheep, "Sheepy"));
        petsFAV.add(new Pet(R.drawable.monkey, "Cesar"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

}
