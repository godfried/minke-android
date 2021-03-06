package za.ac.sun.cs.hons.minke.db.helper;

import za.ac.sun.cs.hons.minke.utils.constants.DBConstants;
import za.ac.sun.cs.hons.minke.utils.constants.DEBUG;
import za.ac.sun.cs.hons.minke.utils.constants.TAGS;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BaseDBHelper extends SQLiteOpenHelper {

	public BaseDBHelper(Context context) {
		super(context, DBConstants.DATABASE_NAME, null,
				DBConstants.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		for (String c : DBConstants.CREATORS) {
			if (DEBUG.ON) {
				Log.v(TAGS.DB, "Creating table " + c);
			}
			database.execSQL(c);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (DEBUG.ON) {
			Log.v(TAGS.DB, "Upgrading database from version " + oldVersion
					+ " to " + newVersion
					+ ", which will destroy all old data");
		}
		for (String t : DBConstants.TABLES) {
			db.execSQL("DROP TABLE IF EXISTS " + t);
		}
		onCreate(db);
	}
}
