package mx.unam.petagram.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.unam.petagram.Pet;
import mx.unam.petagram.R;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder>  {

    private List<Pet> pets;
    private Activity activity;

    public PetAdapter(List<Pet> pets, Activity activity) {
        this.pets = pets;
        this.activity = activity;
    }

    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PetViewHolder petViewHolderholder, int position) {
        final Pet pet = pets.get(position);
        petViewHolderholder.imageCV.setImageResource(pet.getPicture());
        petViewHolderholder.nameCV.setText(pet.getName());
        petViewHolderholder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Like " + pet.getName(), Toast.LENGTH_SHORT ).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageCV;
        private TextView nameCV;
        private ImageButton imageButton;

        public PetViewHolder(View itemView) {
            super(itemView);
            imageCV = (ImageView) itemView.findViewById(R.id.imgCVPet);
            nameCV = (TextView) itemView.findViewById(R.id.textViewCVName);
            imageButton = (ImageButton) itemView.findViewById(R.id.imgBtnCVLike);
        }
    }

}
