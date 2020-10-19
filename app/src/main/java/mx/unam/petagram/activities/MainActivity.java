package mx.unam.petagram.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import mx.unam.petagram.Pet;
import mx.unam.petagram.adapters.PetAdapter;
import mx.unam.petagram.R;

public class MainActivity extends AppCompatActivity {

    private List<Pet> pets;
    private RecyclerView petList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar actionBar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionBar);

        petList = (RecyclerView) findViewById(R.id.recyclerListPets);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        petList.setLayoutManager(linearLayoutManager);

        initializePetList();
        initializeAdapter();
    }

    public void initializeAdapter(){
        PetAdapter adapter = new PetAdapter(pets, this);
        petList.setAdapter(adapter);
    }

    public void initializePetList(){
        pets = new ArrayList<>();
        pets.add(new Pet(R.drawable.fox, "Foxy"));
        pets.add(new Pet(R.drawable.bear, "Yogi"));
        pets.add(new Pet(R.drawable.elephant, "Donphy"));
        pets.add(new Pet(R.drawable.sheep, "Sheepy"));
        pets.add(new Pet(R.drawable.monkey, "Cesar"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.mAbout:
                intent = new Intent(this, DeveloperBio.class);
                break;
            case R.id.mContact:
                intent = new Intent(this, ContactActivity.class);
                break;
            case R.id.mFavourites:
                intent = new Intent(this, FavouritePetsActivity.class);
                break;
        }
        if(intent != null){
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }
}
