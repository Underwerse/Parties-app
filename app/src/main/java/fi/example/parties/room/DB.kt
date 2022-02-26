package fi.example.parties.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fi.example.parties.MainActivity
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.entities.PartyMemberDao

@Database(entities = [PartyMember::class], version = 4, exportSchema = false)
abstract class DB: RoomDatabase() {
    abstract val partyMemberDao: PartyMemberDao

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