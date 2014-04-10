package mx.pig.util;
import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
public class PIGUtil 
{
	public static byte[] getBitmapAsByteArray(Bitmap bitmap) 
	{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    bitmap.compress(CompressFormat.PNG, 0, outputStream);       
	    return outputStream.toByteArray();
	}
	public static Bitmap getBitmapFromByteArray(byte[] bitmap)
	{
		return BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length);
	}
}
