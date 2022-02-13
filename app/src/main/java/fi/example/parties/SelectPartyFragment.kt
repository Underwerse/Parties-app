package fi.example.parties

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import fi.example.parties.databinding.FragmentSelectPartyBinding

class SelectPartyFragment : Fragment() {
    private lateinit var binding: FragmentSelectPartyBinding
    private var partiesList =
        ParliamentMembersData.members.map { it.party }.toSet().toList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_select_party,container,false)

        val bundle = Bundle()

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

class PartiesViewModel: ViewModel() {
    val partiesList = getPartiesList()

    @JvmName("getPartiesList1")
    fun getPartiesList(): List<String> {
        return ParliamentMembersData.members.map { it.party }.toSet().toList()
    }
}
