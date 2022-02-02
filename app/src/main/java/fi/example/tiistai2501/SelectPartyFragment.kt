package fi.example.tiistai2501

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import fi.example.tiistai2501.databinding.FragmentSelectPartyBinding

class SelectPartyFragment : Fragment() {
    private lateinit var binding: FragmentSelectPartyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_select_party,container,false)

        val bundle = Bundle()


        val parliamentMembers = ParliamentMembersData.members
        var partiesList = mutableListOf<String>()
        parliamentMembers.forEach {
            partiesList.add(it.party)
        }

        partiesList = partiesList.toSet().toList() as MutableList<String>

        binding.tvParties.text = partiesList.joinToString()

        binding.btnRandom.setOnClickListener { view : View ->
            val inputParty = binding.etInputParty.text.toString()
            bundle.putString("selectedParty", inputParty)
            view.findNavController()
                .navigate(R.id.action_selectPartyFragment_to_partyDetailsFragment, bundle)
        }

        return binding.root
    }
}
