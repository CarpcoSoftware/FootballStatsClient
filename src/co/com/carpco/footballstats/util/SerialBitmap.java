/**
 * 
 */
package co.com.carpco.footballstats.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Carlos Rodriguez
 */
public class SerialBitmap implements Serializable {

  private static final long serialVersionUID = 2015231518864762956L;
  
  private Bitmap bitmap;

  public SerialBitmap(Bitmap bitmap) {
      this.bitmap = bitmap;
  }
  
  public Bitmap getBitmap() {
    return bitmap;
  }
  
  /**
   *  Converts the Bitmap into a byte array for serialization
   * @param out
   * @throws IOException
   */
  private void writeObject(java.io.ObjectOutputStream out) throws IOException {
    if (bitmap != null) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteStream);
        byte bitmapBytes[] = byteStream.toByteArray();
        out.write(bitmapBytes, 0, bitmapBytes.length);
    }
  }

  /**
   * Deserializes a byte array representing the Bitmap and decodes it
   * @param in
   * @throws IOException
   * @throws ClassNotFoundException
   */
  private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
      ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
      int b;
      while((b = in.read()) != -1)
          byteStream.write(b);
      byte bitmapBytes[] = byteStream.toByteArray();
      bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
  }
}