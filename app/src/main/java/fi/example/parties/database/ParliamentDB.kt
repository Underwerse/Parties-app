package fi.example.parties.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fi.example.parties.MainActivity

@Database(entities = [ParliamentMember::class], version = 1, exportSchema = false)
abstract class ParliamentDB: RoomDatabase() {
    abstract val parliamentMemberDao: ParliamentMemberDao

    companion object {

        @Volatile
        private var INSTANCE: ParliamentDB? = null

        fun getInstance(context: Context = MainActivity.appContext): ParliamentDB {
            synchronized(this) {
                var instance =
                    INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        ParliamentDB::class.java,
                        "parliament_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}