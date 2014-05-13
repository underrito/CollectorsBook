package mx.pig.database;

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
	public Cursor getConsultaBD(String sql)
	{
		Cursor result=null;
		result	= database.rawQuery(sql,null);
		result.moveToFirst();
		return result;
	}
	public void executeCommandBD(String sql)
	{
		database.execSQL(sql);		
	}
	
	public void initializeTables()
	{
			String dropItemCampoValor="DROP TABLE IF EXISTS PIG_ITEMCAMPOVALOR;";
			String dropItem="DROP TABLE IF EXISTS PIG_ITEM;";
			String dropCategoria="DROP TABLE IF EXISTS PIG_CATEGORIA;";
			String dropTipoVista="DROP TABLE IF EXISTS PIG_TIPOVISTA;";
			String dropItemCampo="DROP TABLE IF EXISTS PIG_ITEMCAMPO;";
			
			executeCommandBD(dropItemCampoValor);
			executeCommandBD(dropItem);
			executeCommandBD(dropCategoria);
			executeCommandBD(dropTipoVista);
			executeCommandBD(dropItemCampo);
			
			
		
			String tableItemCampo="CREATE TABLE IF NOT EXISTS PIG_ITEMCAMPO(ID_CAMPO INTEGER PRIMARY KEY ASC,DESCRCAMPO VARCHAR);";
			String tableTipoVista="CREATE TABLE IF NOT EXISTS PIG_TIPOVISTA(ID_TIPOVISTA INTEGER PRIMARY KEY ASC,DESCRTIPOVISTA VARCHAR);";
			String tableCategoria="CREATE TABLE IF NOT EXISTS PIG_CATEGORIA(ID_CATEGORIA INTEGER PRIMARY KEY ASC,DESCRCATEGORIA VARCHAR,ID_CATEGORIAPARENT INTEGER,ID_TIPOVISTA INTEGER,FOREIGN KEY(ID_TIPOVISTA) REFERENCES PIG_TIPOVISTA(ID_TIPOVISTA));";
			String tableItem="CREATE TABLE IF NOT EXISTS PIG_ITEM(ID_ITEM INTEGER PRIMARY KEY ASC,ID_CATEGORIA INTEGER,FOREIGN KEY(ID_CATEGORIA) REFERENCES PIG_CATEGORIA(ID_CATEGORIA));";
			String tableItemCampoValor="CREATE TABLE IF NOT EXISTS PIG_ITEMCAMPOVALOR(ID_ITEM INTEGER,ID_CAMPO INTEGER,VALOR VARCHAR,FOREIGN KEY(ID_ITEM) REFERENCES PIG_ITEM(ID_ITEM),FOREIGN KEY(ID_CAMPO) REFERENCES PIG_ITEMCAMPO(ID_CAMPO));";

			executeCommandBD(tableItemCampo);
			executeCommandBD(tableTipoVista);
			executeCommandBD(tableCategoria);
			executeCommandBD(tableItem);
			executeCommandBD(tableItemCampoValor);
		
			//String tableCategoria="CREATE TABLE IF NOT EXISTS PIG_CATEGORIA (ID INT,DESCRIPCION VARCHAR);";
			//executeCommandBD(tableCategoria);
			
			StringBuffer delete= new StringBuffer();
			delete.append("DELETE FROM PIG_CATEGORIA");
			executeCommandBD(delete.toString());
			
			
			
			
			/******************************************CARGA INICIAL********************************/
			StringBuffer inserts= new StringBuffer();			


			inserts.append("INSERT INTO PIG_ITEMCAMPO (ID_CAMPO,DESCRCAMPO) VALUES(1,'NOMBRE');");			
			executeCommandBD(inserts.toString());			
			inserts=new StringBuffer();
			
			
			
			inserts.append("INSERT INTO PIG_TIPOVISTA (ID_TIPOVISTA,DESCRTIPOVISTA) VALUES(1,'ICONOS');");
			inserts.append("INSERT INTO PIG_TIPOVISTA (ID_TIPOVISTA,DESCRTIPOVISTA) VALUES(2,'GRID');");
			executeCommandBD(inserts.toString());			
			inserts=new StringBuffer();
			
			
			 			
			inserts.append("INSERT INTO PIG_CATEGORIA (ID_CATEGORIA,DESCRCATEGORIA,ID_CATEGORIAPARENT,ID_TIPOVISTA) VALUES(1,'DEFAULT',0,2);");
			executeCommandBD(inserts.toString());			
			inserts=new StringBuffer(); 
			
			
			inserts.append("INSERT INTO PIG_ITEM(ID_ITEM,ID_CATEGORIA) VALUES(1,1);");
			executeCommandBD(inserts.toString());			
			inserts=new StringBuffer();
			
			inserts.append("INSERT INTO PIG_ITEMCAMPOVALOR(ID_ITEM,ID_CAMPO,VALOR) VALUES(1,1,'PRIMER ITEM');");
			executeCommandBD(inserts.toString());			
			inserts=new StringBuffer();
			
			
			
			
			
			
			StringBuffer select = new StringBuffer();
			select.append("SELECT * FROM PIG_CATEGORIA");
			Cursor cursor= getConsultaBD(select.toString());
			
			int idCategoriaIndex=cursor.getColumnIndex("ID_CATEGORIA");
			int descrCategoriaIndex=cursor.getColumnIndex("DESCRCATEGORIA");
			int idCategoriaParentIndex=cursor.getColumnIndex("ID_CATEGORIAPARENT");
			int idTipoVistaIndex=cursor.getColumnIndex("ID_TIPOVISTA");
			
			StringBuilder resultado= new StringBuilder();
			do
			{
				resultado.append("ID_CATEGORIA:");
				resultado.append(cursor.getInt(idCategoriaIndex));
				resultado.append(" DESCRCATEGORIA:");
				resultado.append(cursor.getString(descrCategoriaIndex));
				resultado.append(" ID_CATEGORIAPARENT:");
				resultado.append(cursor.getInt(idCategoriaParentIndex));
				resultado.append(" ID_TIPOVISTA:");
				resultado.append(cursor.getInt(idTipoVistaIndex));
				//resultado.append("\n");
			}
			while(cursor.moveToNext());
			Log.i("PIG", resultado.toString());
	}
}
