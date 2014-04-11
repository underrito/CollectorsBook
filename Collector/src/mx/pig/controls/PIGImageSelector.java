package mx.pig.controls;
import java.io.FileNotFoundException;
import java.io.InputStream;
import mx.pig.collector.R;
import mx.pig.util.PIGUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
public class PIGImageSelector extends LinearLayout
{
	Context 		context;
	ImageView 		photoSelectedImageView;
	ImageButton 	photoButton;
	ImageButton 	galleryButton;
	private static final int REQUEST_IMAGE_CAPTURE = 1;
	private static final int SELECT_PHOTO = 100;

	public PIGImageSelector(Context context, AttributeSet attrs) {
		super(context, attrs);
		

	}

	
}
