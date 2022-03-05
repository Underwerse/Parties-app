package fi.example.parties.room

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

/**
 * Converters Bitmap to ByteArray and vise versa
 * while working with members' images and SQLite
 */
class ImageConverter {

	@TypeConverter
	fun fromBitmap(bitmap: Bitmap): ByteArray {
		val outputStream = ByteArrayOutputStream()
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
		return outputStream.toByteArray()
	}
	
	@TypeConverter
	fun toBitmap(byteArray: ByteArray): Bitmap {
		return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
	}
}