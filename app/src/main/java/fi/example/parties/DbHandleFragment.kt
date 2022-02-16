package fi.example.parties

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import fi.example.parties.databinding.FragmentDbHandleBinding
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.viewmodels.PartyMemberVM

class DbHandleFragment : Fragment() {
    private lateinit var binding: FragmentDbHandleBinding
    private lateinit var vmPartyMember: PartyMemberVM
    private val parliamentMembers = ParliamentMembersData.members

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_db_handle, container, false)

        vmPartyMember = ViewModelProvider(this).get(PartyMemberVM::class.java)

        binding.btnAddToDb.setOnClickListener {
            insertDataToDB()
        }

        binding.btnCleanDb.setOnClickListener {
            vmPartyMember.deleteAll()
            Toast.makeText(requireContext(), "DB has been cleaned.", Toast.LENGTH_SHORT).show()
//            view?.findNavController()
//                ?.navigate(R.id.action_dbHandleFragment_to_selectPartyFragment)
        }

        return binding.root
    }

    private fun insertDataToDB() {
        parliamentMembers.forEach {
            val member = PartyMember(
                it.personNumber,
                it.seatNumber,
                it.last,
                it.first,
                it.party,
                it.minister,
                it.picture,
                it.twitter,
                it.bornYear,
                it.constituency
            )
            vmPartyMember.addMember(member)
        }
        Toast.makeText(requireContext(), "All the members have been added.", Toast.LENGTH_SHORT).show()
//        view?.findNavController()
//            ?.navigate(R.id.action_dbHandleFragment_to_selectPartyFragment)
    }
}