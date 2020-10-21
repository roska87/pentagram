package mx.unam.petagram.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import mx.unam.petagram.pojo.Pet;
import mx.unam.petagram.R;
import mx.unam.petagram.adapters.PetAdapter;
import mx.unam.petagram.presenters.IFragmentPresenter;
import mx.unam.petagram.presenters.PetsPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetListFragment extends Fragment implements IPetListFragmentView {

    private List<Pet> pets;
    private RecyclerView petList;
    private IFragmentPresenter iFragmentPresenter;

    public PetListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pet_list, container, false);
        petList = (RecyclerView) view.findViewById(R.id.recyclerFragmentPetList);
        iFragmentPresenter = new PetsPresenter(this, getContext());
        return view;
    }

    @Override
    public void initializeAdapter(PetAdapter adapter) {
        petList.setAdapter(adapter);
    }

    @Override
    public void generateLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        petList.setLayoutManager(linearLayoutManager);
    }

    @Override
    public PetAdapter createAdapter(List<Pet> pets) {
        return new PetAdapter(pets, getActivity());
    }
}