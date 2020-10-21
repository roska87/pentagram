package mx.unam.petagram.presenters;

import android.content.Context;

import java.util.List;

import mx.unam.petagram.activities.IFavouritePetsActivityView;
import mx.unam.petagram.db.FavouritePetConstructor;
import mx.unam.petagram.fragments.IPetListFragmentView;
import mx.unam.petagram.db.PetConstructor;
import mx.unam.petagram.pojo.Pet;

public class FavouritePetsPresenter implements IFragmentPresenter {

    private IFavouritePetsActivityView iFavouritePetsActivityView;
    private Context context;
    private FavouritePetConstructor petConstructor;
    private List<Pet> favouritePets;

    public FavouritePetsPresenter(Context context, IFavouritePetsActivityView iFavouritePetsActivityView) {
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