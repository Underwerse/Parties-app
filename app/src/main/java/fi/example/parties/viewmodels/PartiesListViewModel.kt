package fi.example.parties.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fi.example.parties.database.PartyMemberRepository

class PartiesListViewModel(application: Application) : AndroidViewModel(application) {
    val partiesList = PartyMemberRepository.parties
}