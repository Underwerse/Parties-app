package fi.example.parties.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "party_members_table")
data class PartyMember (
    @PrimaryKey
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
)
