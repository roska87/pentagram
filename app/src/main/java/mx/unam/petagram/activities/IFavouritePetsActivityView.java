package mx.unam.petagram.activities;

import java.util.List;

import mx.unam.petagram.adapters.FavouritePetAdapter;
import mx.unam.petagram.model.Pet;

public interface IFavouritePetsActivityView {

    public void initializeAdapter(FavouritePetAdapter adapter);

    public void generateLayout();

    public FavouritePetAdapter createAdapter(List<Pet> pets);
}
