package mx.unam.petagram.fragments;

import java.util.List;
import mx.unam.petagram.adapters.PetAdapter;
import mx.unam.petagram.model.Pet;

public interface IPetListFragmentView {

    public void initializeAdapter(PetAdapter adapter);

    public void generateLayout();

    public PetAdapter createAdapter(List<Pet> pets);
}
