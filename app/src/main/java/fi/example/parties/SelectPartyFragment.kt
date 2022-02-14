package fi.example.parties

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import fi.example.parties.databinding.FragmentSelectPartyBinding
import fi.example.parties.viewmodels.AddPartyMemberViewModel

class SelectPartyFragment : Fragment() {
    private lateinit var binding: FragmentSelectPartyBinding
    private val parliamentMembers = ParliamentMembersData.members
    private val partiesList = parliamentMembers.map { it.party }.toSet().toList()
    private lateinit var viewModelPartyMemberAdd: AddPartyMemberViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_select_party,container,false)

        val bundle = Bundle()
        viewModelPartyMemberAdd = ViewModelProvider(this).get(AddPartyMemberViewModel::class.java)

        binding.tvParties.text = partiesList.joinToString()

        binding.btnRandom.setOnClickListener { view : View ->
            val inputParty = binding.etInputParty.text.toString()
            bundle.putString("selectedParty", inputParty)
            view.findNavController()
                .navigate(R.id.action_selectPartyFragment_to_partyDetailsFragment, bundle)
        }

        binding.btnAddToDb.setOnClickListener {
            parliamentMembers.forEach {
                viewModelPartyMemberAdd.addPartyMember(
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
            }
            Toast.makeText(requireContext(), "All the members have been added.", Toast.LENGTH_SHORT).show()
        }

        binding.btnCleanDb.setOnClickListener {
            viewModelPartyMemberAdd.cleanDb()
            Toast.makeText(requireContext(), "DB has been cleaned.", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}

class PartiesViewModel: ViewModel() {
    val partiesList = getPartiesList()

    @JvmName("getPartiesList1")
    fun getPartiesList(): List<String> {
        return ParliamentMembersData.members.map { it.party }.toSet().toList()
    }
}
