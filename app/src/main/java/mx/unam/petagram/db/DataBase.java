package mx.unam.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import mx.unam.petagram.model.Pet;

public class DataBase extends SQLiteOpenHelper {

    private Context context;

    public DataBase(Context context) {
        super(context, DataBaseConstants.DATABASE_NAME, null, DataBaseConstants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCreateTablePets = "CREATE TABLE " + DataBaseConstants.TABLE_PETS
                + "(" + DataBaseConstants.COLUMN_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataBaseConstants.COLUMN_PETS_NAME + " TEXT NOT NULL, "
                + DataBaseConstants.COLUMN_PETS_PICTURE + " INTEGER NOT NULL)";

        String queryCreteTableLikes = "CREATE TABLE " + DataBaseConstants.TABLE_LIKES
                + "(" + DataBaseConstants.COLUMN_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataBaseConstants.COLUMN_LIKES_ID_PET + " INTEGER NOT NULL, "
                + DataBaseConstants.COLUMN_LIKES_LIKES + " INTEGER NOT NULL, "
                + "FOREIGN KEY (" + DataBaseConstants.COLUMN_LIKES_ID_PET + ") "
                + "REFERENCES " + DataBaseConstants.TABLE_PETS + "("+ DataBaseConstants.COLUMN_PETS_ID +")"
                + ") ";

        db.execSQL(queryCreateTablePets);
        db.execSQL(queryCreteTableLikes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_PETS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_LIKES);
        onCreate(db);
    }

    public List<Pet> getPets() {
        List<Pet> pets = new ArrayList<>();

        String query = "SELECT * FROM " + DataBaseConstants.TABLE_PETS;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor registers = database.rawQuery(query, null);

        while (registers.moveToNext()) {
            Pet currentPet = new Pet();
            int petId = registers.getInt(0);
            currentPet.setId(petId);
            currentPet.setName(registers.getString(1));
            currentPet.setPicture(registers.getInt(2));

            String queryLikes = "SELECT SUM(" + DataBaseConstants.TABLE_LIKES + ")"
                    + " FROM " + DataBaseConstants.TABLE_LIKES
                    + " WHERE " + DataBaseConstants.COLUMN_LIKES_ID_PET + " = " + petId;

            Cursor likes = database.rawQuery(queryLikes, null);
            if (likes.moveToNext()) {
                currentPet.setLikes(likes.getInt(0));
            }else {
                currentPet.setLikes(0);
            }
            pets.add(currentPet);
        }
        database.close();
        return pets;
    }

    public void insertPet(ContentValues contentValues){
        SQLiteDatabase database = this.getReadableDatabase();
        database.insert(DataBaseConstants.TABLE_PETS, null, contentValues);
        database.close();
    }

    public boolean isPetsEmpty(){
        int count = 0;
        String query = "SELECT COUNT(*)" +
                " FROM " + DataBaseConstants.TABLE_PETS;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor register = database.rawQuery(query, null);
        if(register.moveToNext()) {
            count = register.getInt(0);
        }
        return count == 0;
    }

    public void insertLikes(ContentValues contentValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(DataBaseConstants.TABLE_LIKES, null, contentValues);
        database.close();
    }

    public int getLikes(Pet pet) {
        int likes = 0;
        String query = "SELECT COUNT(" + DataBaseConstants.COLUMN_LIKES_LIKES + ")" +
                " FROM " + DataBaseConstants.TABLE_LIKES +
                " WHERE " + DataBaseConstants.COLUMN_LIKES_ID_PET + " = " + pet.getId();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor register = database.rawQuery(query, null);
        if(register.moveToNext()) {
            likes = register.getInt(0);
        }
        database.close();
        return likes;
    }

    public List<Pet> getFavouritePets(int limit) {
        List<Pet> pets = new ArrayList<>();

        String query = "SELECT p.*, SUM(l." + DataBaseConstants.COLUMN_LIKES_LIKES + ") "
            + " from " + DataBaseConstants.TABLE_PETS + " p "
            + " join " + DataBaseConstants.TABLE_LIKES + " l on (p. " + DataBaseConstants.COLUMN_PETS_ID + " = l." + DataBaseConstants.COLUMN_LIKES_ID_PET + ")"
            + " group by p." + DataBaseConstants.COLUMN_PETS_ID
            + " order by l." + DataBaseConstants.COLUMN_LIKES_ID + " desc "
            + " limit " + limit;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor registers = database.rawQuery(query, null);

        while (registers.moveToNext()) {
            Pet currentPet = new Pet();
            currentPet.setId(registers.getInt(0));
            currentPet.setName(registers.getString(1));
            currentPet.setPicture(registers.getInt(2));
            currentPet.setLikes(registers.getInt(3));
            pets.add(currentPet);
        }
        database.close();
        return pets;
    }

}