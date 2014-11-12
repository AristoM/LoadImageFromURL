package com.aristo.readimagefromurl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

public class FirstActivity extends Activity {

	ImageView img;
	Bitmap bmp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);

		img = (ImageView) findViewById(R.id.imageView1);

		/** 1. Simplest way without using Threads **/
		/*
		 * StrictMode.ThreadPolicy policy = new
		 * StrictMode.ThreadPolicy.Builder() .permitAll().build();
		 * StrictMode.setThreadPolicy(policy);
		 * 
		 * InputStream in = null; try { in = new URL(
		 * "http://cnet4.cbsistatic.com/hub/i/2011/10/27/a66dfbb7-fdc7-11e2-8c7c-d4ae52e62bcc/android-wallpaper5_2560x1600_1.jpg"
		 * ) .openStream(); } catch (IOException e) { e.printStackTrace(); }
		 * Bitmap bmp = BitmapFactory.decodeStream(in);
		 * 
		 * img.setImageBitmap(bmp);
		 */

		/** 2.We can also Thread **/
		/*
		 * Thread thread = new Thread(new Runnable() {
		 * 
		 * @Override public void run() { InputStream in = null; try { in = new
		 * URL(
		 * "http://cnet4.cbsistatic.com/hub/i/2011/10/27/a66dfbb7-fdc7-11e2-8c7c-d4ae52e62bcc/android-wallpaper5_2560x1600_1.jpg"
		 * ) .openStream(); } catch (IOException e) { e.printStackTrace(); } bmp
		 * = BitmapFactory.decodeStream(in);
		 * 
		 * } }); thread.start(); img.setImageBitmap(bmp);
		 */

		new ImageLoad().execute();

	}

	/** 3.Image load class to decode image form url Its most efficiant way **/
	private class ImageLoad extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {

			InputStream in = null;
			try {
				in = new URL(
						"http://cnet4.cbsistatic.com/hub/i/2011/10/27/a66dfbb7-fdc7-11e2-8c7c-d4ae52e62bcc/android-wallpaper5_2560x1600_1.jpg")
						.openStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bmp = BitmapFactory.decodeStream(in);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (bmp != null)
				img.setImageBitmap(bmp);
		}

	}

}
