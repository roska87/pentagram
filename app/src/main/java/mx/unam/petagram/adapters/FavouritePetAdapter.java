package mx.unam.petagram.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.unam.petagram.R;
import mx.unam.petagram.model.FavouritePetConstructor;
import mx.unam.petagram.model.Pet;

public class FavouritePetAdapter extends RecyclerView.Adapter<FavouritePetAdapter.PetViewHolder>  {

    private List<Pet> pets;
    private Activity activity;
    private FavouritePetConstructor petConstructor;

    public FavouritePetAdapter(List<Pet> pets, Activity activity) {
        this.pets = pets;
        this.activity = activity;
        this.petConstructor = new FavouritePetConstructor(activity);
    }

    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PetViewHolder petViewHolderholder, int position) {
        final Pet pet = pets.get(position);
        petViewHolderholder.imageCV.setImageResource(pet.getPicture());
        petViewHolderholder.nameCV.setText(pet.getName());
        petViewHolderholder.textViewLikes.setText(String.valueOf(pet.getLikes()));

        /*
        petViewHolderholder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Like " + pet.getName(), Toast.LENGTH_SHORT).show();
                petConstructor.giveLike(pet);
                petViewHolderholder.textViewLikes.setText(String.valueOf(petConstructor.getLikes(pet)));
            }
        });
         */
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageCV;
        private TextView nameCV;
        private ImageButton imageButton;
        private TextView textViewLikes;

        public PetViewHolder(View itemView) {
            super(itemView);
            imageCV = (ImageView) itemView.findViewById(R.id.imgCVPet);
            nameCV = (TextView) itemView.findViewById(R.id.textViewCVName);
            imageButton = (ImageButton) itemView.findViewById(R.id.imgBtnCVLike);
            textViewLikes = (TextView) itemView.findViewById(R.id.textViewCVLikes);
        }
    }

}
