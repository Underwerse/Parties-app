package fi.example.parties.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PartyMemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(member: PartyMember)

    @Query("select * from party_members_table order by first")
    fun getAllMembers(): LiveData<List<PartyMember>>

    @Query("select * from party_members_table where party = :key")
    fun getPartyMemberByParty(key: String): LiveData<PartyMember>

    @Query("select * from party_members_table where personNumber = :key")
    fun getPartyMemberByPersonNumber(key: Int): PartyMember

    @Query("delete from party_members_table")
    suspend fun deleteAllMembers()
}