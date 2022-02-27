package fi.example.parties.retrofit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Member(
	val personNumber: Int,
	val seatNumber: Int,
	val last: String,
	val first: String,
	val party: String,
	val minister:Boolean,
	val picture: String,
	val twitter: String,
	val bornYear:Int,
	val constituency: String
): Parcelable {
}
