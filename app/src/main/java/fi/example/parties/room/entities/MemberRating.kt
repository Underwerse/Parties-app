package fi.example.parties.room.entities

import androidx.room.*

@Entity(tableName = "party_members_ratings_table")
data class MemberRating (
    @PrimaryKey
    val personNumber: Int,
    val rating: Int
)
