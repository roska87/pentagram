package mx.unam.petagram.db;

import android.content.Context;
import java.util.List;
import mx.unam.petagram.pojo.Pet;

public class FavouritePetConstructor {

    private static final int LIMIT = 5;

    private Context context;

    public FavouritePetConstructor(Context context){ this.context = context;}

    public List<Pet> getFavouritePets() {
        DataBase dataBase = new DataBase(context);
        return dataBase.getFavouritePets(LIMIT);
    }

}