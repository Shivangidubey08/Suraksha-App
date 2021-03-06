package com.example.suraksha.data;
import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;
public class contractClass {
    private contractClass() {}

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.suraksha";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
   public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_USERS = "users";


    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class UserEntry implements BaseColumns {

        /** The content URI to access the pet data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_USERS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
      public static final String CONTENT_LIST_TYPE =
               ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USERS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
       public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USERS;

        public final static String TABLE_NAME = "userdata";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_USER_NAME ="name";
        public final static String COLUMN_AGE = "age";
        public final static String COLUMN_ADHAR = "adharNumber";
        public final static String COLUMN_CONTACT_PERSON = "contactPersonName";
        public final static String COLUMN_CONTACT_NUMBER = "contactNumber";
        public final static String COLUMN_ADDRESS = "address";
        public final static String COLUMN_BLOODGROUP = "bloodgroup";
        public final static String COLUMN_DIABETES = "diabetes";
        public final static String COLUMN_POLICY_NIUMBER = "policyNumber";
        public final static String COLUMN_MEDICAL_FILE_URL = "medicalFileUrl";

        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

        public static boolean isValidGender(int gender) {
            if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
                return true;
            }
            return false;
        }
    }
}



