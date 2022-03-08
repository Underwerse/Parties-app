package fi.example.parties.room.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PartyMemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMember(member: PartyMember)
    
    @Query("select * from party_members_table where party = :key")
    fun getPartyMembersByParty(key: String): LiveData<List<PartyMember>>

    @Query("select * from party_members_table where personNumber = :key")
    fun getMemberByPersNumber(key: Int): LiveData<PartyMember>
    
    @Query("select distinct party from party_members_table order by party")
    fun getParties(): LiveData<List<String>>
    
    @Query("delete from party_members_table")
    suspend fun deleteAll()
}