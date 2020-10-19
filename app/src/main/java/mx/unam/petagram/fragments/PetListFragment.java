package mx.unam.petagram.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import mx.unam.petagram.Pet;
import mx.unam.petagram.R;
import mx.unam.petagram.adapters.PetAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetListFragment extends Fragment {

    private List<Pet> pets;
    private RecyclerView listaMascotas;

    public PetListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_pet_list, container, false);

        listaMascotas = (RecyclerView) view.findViewById(R.id.recyclerFragmentPetList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(linearLayoutManager);

        initializePetList();
        initializeAdapter();

        return view;
    }

    public void initializeAdapter(){
        PetAdapter adapter = new PetAdapter(pets, getActivity());
        listaMascotas.setAdapter(adapter);
    }

    public void initializePetList(){
        pets = new ArrayList<>();
        pets.add(new Pet(R.drawable.fox, "Foxy"));
        pets.add(new Pet(R.drawable.bear, "Yogi"));
        pets.add(new Pet(R.drawable.elephant, "Donphy"));
        pets.add(new Pet(R.drawable.sheep, "Sheepy"));
        pets.add(new Pet(R.drawable.monkey, "Cesar"));
    }

}