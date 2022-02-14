package fi.example.parties.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ParliamentMemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMember(parliamentMember: ParliamentMember)

    @Query("select * from parliament_members_table order by first")
    fun getAllMembers(): LiveData<List<ParliamentMember>>

    @Query("select * from parliament_members_table where party = :key")
    fun getMemberByParty(key: String): LiveData<List<ParliamentMember>>

    @Query("select distinct party from parliament_members_table order by party")
    fun getParties(): LiveData<List<String>>

    @Query("select * from parliament_members_table where personNumber = :key")
    fun getMemberByPersonNumber(key: Int): ParliamentMember

    @Query("delete from parliament_members_table")
    suspend fun deleteAll()
}