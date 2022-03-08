package fi.example.parties.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fi.example.parties.MainActivity
import fi.example.parties.room.entities.MemberRating
import fi.example.parties.room.entities.MemberRatingDao
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.entities.PartyMemberDao
import fi.example.parties.room.entities.Image
import fi.example.parties.room.entities.ImageDao

@Database(
    entities = [PartyMember::class, MemberRating::class, Image::class],
    version = 5,
    exportSchema = false
)

/**
 * @TypeConverters-annotation for auto-convert Bitmap to ByteArray and vise versa
 * while working with members' images and SQLite
 */
@TypeConverters(ImageConverter::class)
abstract class DB: RoomDatabase() {
    abstract val partyMemberDao: PartyMemberDao
    abstract val memberRatingDao: MemberRatingDao
    abstract val imageDao: ImageDao

    companion object {

        @Volatile
        private var INSTANCE: DB? = null

        fun getInstance(context: Context = MainActivity.appContext): DB {
            synchronized(this) {
                var instance =
                    INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        DB::class.java,
                        "party_members_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}