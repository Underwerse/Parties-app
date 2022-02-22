package fi.example.parties.room.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PartyMemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMember(member: PartyMember)

    @Query("select * from party_members_table order by first")
    fun getAllMembers(): LiveData<List<PartyMember>>

    @Query("select * from party_members_table where party = :key")
    fun getPartyMemberByParty(key: String): LiveData<List<PartyMember>>

    @Query("select distinct party from party_members_table order by party")
    fun getParties(): LiveData<List<String>>

    @Query("select * from party_members_table where personNumber = :key")
    suspend fun getMemberByPersonNumber(key: Int): PartyMember?

    @Query("delete from party_members_table")
    suspend fun deleteAll()
}