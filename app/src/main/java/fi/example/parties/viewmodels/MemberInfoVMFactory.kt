package fi.example.parties.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 */
class MemberInfoVMFactory(private val application: Application,
                           private val memberPersNumber: Int
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemberInfoVM::class.java)){
            return MemberInfoVM(application, memberPersNumber) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}