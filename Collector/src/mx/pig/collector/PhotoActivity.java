package mx.pig.collector;
import java.io.FileNotFoundException;
import java.io.InputStream;
import mx.pig.util.PIGUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class PhotoActivity extends Activity {
	private static final int REQUEST_IMAGE_CAPTURE = 1;
	private static final int SELECT_PHOTO = 100;
	ImageView 		photoSelectedImageView;
	ImageButton 	photoButton;
	ImageButton 	galleryButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		photoButton					= (ImageButton)findViewById(R.id.photoButton);
		galleryButton				= (ImageButton)findViewById(R.id.galleryButton);
		photoSelectedImageView		= (ImageView)findViewById(R.id.photoSelectedImageView);
		photoButton.setOnClickListener(photoButtonClickListener);
		galleryButton.setOnClickListener(galleryButtonClickListener);
	}

	OnClickListener photoButtonClickListener= new OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			Intent intentPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			if(intentPhoto.resolveActivity(getPackageManager())!=null )
			{
				startActivityForResult(intentPhoto, REQUEST_IMAGE_CAPTURE);
			}
			
		}
	};
	
	OnClickListener galleryButtonClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			Intent galleryIntent = new Intent(Intent.ACTION_PICK);
			galleryIntent.setType("image/*");
			startActivityForResult(galleryIntent, SELECT_PHOTO);
			
		}
	};
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		if(resultCode==RESULT_OK)
		{
			switch(requestCode)
			{
				case REQUEST_IMAGE_CAPTURE:
				{
					Bundle extras = data.getExtras();
					Bitmap imageBitmap=(Bitmap)extras.get("data");
					byte[] imageByte=PIGUtil.getBitmapAsByteArray(imageBitmap);
					Log.i("PIG", "imagen:"+imageByte.toString());
					Bitmap laImagen= PIGUtil.getBitmapFromByteArray(imageByte);
					photoSelectedImageView.setImageBitmap(laImagen);
					break;
				}
				case SELECT_PHOTO:
				{
					Uri galleryImageUri 		= data.getData();
					InputStream imageStream		= null;
					try
					{
						imageStream		= getContentResolver().openInputStream(galleryImageUri);
					}
					catch(FileNotFoundException fnfe)
					{
						Log.i("PIG", "FileNotFound:"+fnfe.toString());
						imageStream		= null;
					}
					Bitmap imageBitmap=BitmapFactory.decodeStream(imageStream);
					byte[] imageByte=PIGUtil.getBitmapAsByteArray(imageBitmap);
					Log.i("PIG", "imagen:"+imageByte.toString());
					Bitmap laImagen= PIGUtil.getBitmapFromByteArray(imageByte);
					photoSelectedImageView.setImageBitmap(laImagen);
					break;
				}
				default:
					break;
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photo, menu);
		return true;
	}

}
