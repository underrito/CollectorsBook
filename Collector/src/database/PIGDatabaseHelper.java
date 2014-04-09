package database;

import mx.pig.collector.R;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PIGDatabaseHelper 
{
	private static PIGDatabaseHelper 	instance;
	private static SQLiteDatabase 		database;
	public static PIGDatabaseHelper getInstance(Activity activity)
	{
		if(instance==null)
		{
			instance	= new PIGDatabaseHelper();
			database 	= activity.openOrCreateDatabase(activity.getResources().getString(R.string.database_name),Activity.MODE_PRIVATE, null);
		}
		return instance;
	}
	public static Cursor getConsultaBD(String sql)
	{
		Cursor result=null;
		result	= database.rawQuery(sql,null);
		result.moveToFirst();
		return result;
	}
	public static void executeCommandBD(String sql)
	{
		database.execSQL(sql);		
	}
	
	public static void initializeTables()
	{
			String tableCategoria="CREATE TABLE IF NOT EXISTS PIG_CATEGORIA (ID INT,DESCRIPCION VARCHAR);";
			executeCommandBD(tableCategoria);
			
			StringBuffer delete= new StringBuffer();
			delete.append("DELETE FROM PIG_CATEGORIA");
			executeCommandBD(delete.toString());
			
			StringBuffer inserts= new StringBuffer();			
			inserts.append("INSERT INTO PIG_CATEGORIA (ID,DESCRIPCION) VALUES(1,'HOLITA');");
			executeCommandBD(inserts.toString());			
			inserts=new StringBuffer();
			
			inserts.append("INSERT INTO PIG_CATEGORIA (ID,DESCRIPCION) VALUES(2,'ADIOS');");
			executeCommandBD(inserts.toString());
			inserts=new StringBuffer();
			
			inserts.append("INSERT INTO PIG_CATEGORIA (ID,DESCRIPCION) VALUES(3,'NUNCA');");
			executeCommandBD(inserts.toString());
			inserts=new StringBuffer();
			
			inserts.append("INSERT INTO PIG_CATEGORIA (ID,DESCRIPCION) VALUES(4,'PERRITO');");
			executeCommandBD(inserts.toString());
			inserts=new StringBuffer();
			
			StringBuffer select = new StringBuffer();
			select.append("SELECT * FROM PIG_CATEGORIA");
			Cursor cursor= getConsultaBD(select.toString());
			
			int idIndex=cursor.getColumnIndex("ID");
			int descripcionIndex=cursor.getColumnIndex("DESCRIPCION");
			StringBuilder resultado= new StringBuilder();
			do
			{
				resultado.append("index:");
				resultado.append(cursor.getInt(idIndex));
				resultado.append(" descr:");
				resultado.append(cursor.getString(descripcionIndex));
				//resultado.append("\n");
			}
			while(cursor.moveToNext());
			Log.i("PIG", resultado.toString());
	}
}
