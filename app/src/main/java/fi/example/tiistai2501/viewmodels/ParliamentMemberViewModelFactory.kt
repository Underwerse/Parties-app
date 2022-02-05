package fi.example.tiistai2501.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ParliamentMemberViewModelFactory(private val selectedParty: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParliamentMemberViewModel::class.java)){
            return ParliamentMemberViewModel(selectedParty) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}