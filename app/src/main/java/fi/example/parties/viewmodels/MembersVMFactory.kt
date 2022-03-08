package fi.example.parties.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 */
class MembersVMFactory(private val application: Application,
                       private val selectedParty: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MembersVM::class.java)){
            return MembersVM(application, selectedParty) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}