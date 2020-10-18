package mx.unam.petagram;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>  {

    private List<Mascota> mascotas;
    private Activity activity;

    public MascotaAdaptador(List<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder mascotaViewHolderholder, int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolderholder.imgFotoCV.setImageResource(mascota.getFoto());
        mascotaViewHolderholder.nombreCV.setText(mascota.getNombre());
        mascotaViewHolderholder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mascota.setRate(mascota.getRate() + 1);
                Toast.makeText(activity, "Like " + mascota.getNombre(), Toast.LENGTH_SHORT ).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFotoCV;
        private TextView nombreCV;
        private ImageButton imageButton;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFotoCV = (ImageView) itemView.findViewById(R.id.imgCVMascota);
            nombreCV = (TextView) itemView.findViewById(R.id.textViewCVNombre);
            imageButton = (ImageButton) itemView.findViewById(R.id.imgBtnCVLike);
        }
    }

}
