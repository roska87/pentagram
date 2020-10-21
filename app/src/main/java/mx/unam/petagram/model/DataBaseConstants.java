package mx.unam.petagram.model;

public final class DataBaseConstants {

    public static final String DATABASE_NAME = "petagram";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_PETS = "pets";
    public static final String COLUMN_PETS_ID = "id";
    public static final String COLUMN_PETS_NAME = "name";
    public static final String COLUMN_PETS_PICTURE = "picture";

    public static final String TABLE_LIKES = "likes";
    public static final String COLUMN_LIKES_ID = "id";
    public static final String COLUMN_LIKES_ID_PET = "id_pet";
    public static final String COLUMN_LIKES_LIKES = "likes";

}


// select p.*, SUM(l.likes) from pets p join likes l on (p.id = l.id_pet) group by p.id order by l.id desc limit 5;