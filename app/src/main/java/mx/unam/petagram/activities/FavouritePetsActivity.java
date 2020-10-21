package mx.unam.petagram.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.unam.petagram.adapters.FavouritePetAdapter;

import mx.unam.petagram.model.Pet;
import mx.unam.petagram.R;
import mx.unam.petagram.presenters.FavouritePetPresenter;
import mx.unam.petagram.presenters.IFragmentPresenter;

public class FavouritePetsActivity extends AppCompatActivity implements IFavouritePetsActivityView {

    private List<Pet> petsFAV;
    private RecyclerView petListFAV;
    private IFragmentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_pets);

        Toolbar actionBar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        petListFAV = (RecyclerView) findViewById(R.id.recyclerPetListFAV);
        presenter = new FavouritePetPresenter(this, this);
    }

    @Override
    public void initializeAdapter(FavouritePetAdapter adapter){
        petListFAV.setAdapter(adapter);
    }

    @Override
    public void generateLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        petListFAV.setLayoutManager(linearLayoutManager);
    }

    @Override
    public FavouritePetAdapter createAdapter(List<Pet> pets) {
        return new FavouritePetAdapter(pets, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.mAbout:
                intent = new Intent(this, DeveloperBioActivity.class);
                break;
            case R.id.mContact:
                intent = new Intent(this, ContactActivity.class);
                break;
        }
        if(intent != null){
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

}
