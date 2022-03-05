package fi.example.parties.room.entities

import androidx.room.*

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 * Entity of MemberRating-object for Room-DB
 */
@Entity(tableName = "party_members_ratings_table")
data class MemberRating (
    @PrimaryKey
    val personNumber: Int,
    val rating: Int,
    val note: String = "No notes"
)
