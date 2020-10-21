package mx.unam.petagram.presenters;

import android.content.Context;

import java.util.List;

import mx.unam.petagram.activities.IFavouritePetsActivityView;
import mx.unam.petagram.model.FavouritePetConstructor;
import mx.unam.petagram.model.Pet;

public class FavouritePetPresenter implements IFragmentPresenter {

    private IFavouritePetsActivityView iFavouritePetsActivityView;
    private Context context;
    private FavouritePetConstructor petConstructor;
    private List<Pet> favouritePets;

    public FavouritePetPresenter(Context context, IFavouritePetsActivityView iFavouritePetsActivityView) {
        this.iFavouritePetsActivityView = iFavouritePetsActivityView;
        this.context = context;
        this.getPets();
    }

    @Override
    public void getPets() {
        petConstructor = new FavouritePetConstructor(context);
        favouritePets = petConstructor.getFavouritePets();
        this.showPets();
    }

    @Override
    public void showPets() {
        iFavouritePetsActivityView.initializeAdapter(iFavouritePetsActivityView.createAdapter(favouritePets));
        iFavouritePetsActivityView.generateLayout();
    }
}