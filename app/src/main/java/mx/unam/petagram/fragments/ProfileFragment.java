package mx.unam.petagram.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import mx.unam.petagram.pojo.Pet;
import mx.unam.petagram.R;
import mx.unam.petagram.adapters.PetAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements IPetListFragmentView {

    private List<Pet> myPets;
    private RecyclerView myPetPictures;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        myPetPictures = (RecyclerView) view.findViewById(R.id.recyclerFragmentMyPet);

        initializePetList();
        initializeAdapter(createAdapter(myPets));
        generateLayout();

        // Inflate the layout for this fragment
        return view;
    }

    public void initializePetList(){
        String myPetName = getResources().getString(R.string.text_cardViewMyPet);
        myPets = new ArrayList<>();
        myPets.add(new Pet(R.drawable.img_1863, myPetName));
        myPets.add(new Pet(R.drawable.img_1969, myPetName));
        myPets.add(new Pet(R.drawable.img_2089, myPetName));
        myPets.add(new Pet(R.drawable.img_2221, myPetName));
        myPets.add(new Pet(R.drawable.img_2625, myPetName));
    }

    @Override
    public void initializeAdapter(PetAdapter adapter) {
        myPetPictures.setAdapter(adapter);
    }

    @Override
    public void generateLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        myPetPictures.setLayoutManager(gridLayoutManager);
    }

    @Override
    public PetAdapter createAdapter(List<Pet> pets) {
        return new PetAdapter(pets, getActivity());
    }
}