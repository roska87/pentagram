package mx.unam.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

import mx.unam.petagram.R;
import mx.unam.petagram.model.DataBaseConstants;
import mx.unam.petagram.pojo.Pet;

public class PetConstructor {

    private static final int LIKE = 1;

    private Context context;

    public PetConstructor(Context context){ this.context = context;}

    public List<Pet> getPets() {
        DataBase dataBase = new DataBase(context);
        return dataBase.getPets();
    }

    public void insertPets(){
        DataBase dataBase = new DataBase(context);
        if(!dataBase.isPetsEmpty()){
            return;
        }
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet(R.drawable.fox, "Foxy"));
        pets.add(new Pet(R.drawable.bear, "Yogi"));
        pets.add(new Pet(R.drawable.elephant, "Donphy"));
        pets.add(new Pet(R.drawable.sheep, "Sheepy"));
        pets.add(new Pet(R.drawable.monkey, "Cesar"));
        for(Pet pet : pets){
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstants.COLUMN_PETS_NAME, pet.getName());
            contentValues.put(DataBaseConstants.COLUMN_PETS_PICTURE, pet.getPicture());
            dataBase.insertPet(contentValues);
        }
    }

    public void giveLike(Pet pet) {
        DataBase dataBase = new DataBase(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.COLUMN_LIKES_ID_PET, pet.getId());
        contentValues.put(DataBaseConstants.COLUMN_LIKES_LIKES, LIKE);
        dataBase.insertLikes(contentValues);
    }

    public int getLikes(Pet pet) {
        DataBase dataBase = new DataBase(context);
        return dataBase.getLikes(pet);
    }

}