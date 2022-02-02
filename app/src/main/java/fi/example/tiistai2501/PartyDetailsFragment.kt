package fi.example.tiistai2501

import android.graphics.ImageDecoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import fi.example.tiistai2501.databinding.FragmentPartyDetailsBinding

class PartyDetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle? ): View? {
        val binding = DataBindingUtil.inflate<FragmentPartyDetailsBinding>(inflater,
            R.layout.fragment_party_details,container,false)

        val parliamentMembers = ParliamentMembersData.members
        var partiesList = mutableListOf<String>()
        parliamentMembers.forEach {
            partiesList.add(it.party)
        }

        partiesList = partiesList.toSet().toList() as MutableList<String>
        val selectedParty = arguments?.getString("selectedParty")

            if (partiesList.contains(selectedParty)) {
                val definedPartiesList = parliamentMembers.filter {
                    it.party.equals(selectedParty)
                }
                println(definedPartiesList.joinToString())

                val randomFoundMember = definedPartiesList.random()

                val imgResName = "@drawable/" + selectedParty
                val imageID = resources.getIdentifier(imgResName, "drawable", activity?.getPackageName())
                binding.imgParty.setImageResource(imageID)

                binding.tvMemberName.text = randomFoundMember.last +
                        ", " + randomFoundMember.first
                binding.tvMemberBirthYear.text = "Borned in " +
                        randomFoundMember.bornYear.toString()
                binding.tvDistrict.text = "District: " +
                        randomFoundMember.constituency
                binding.tvMemberTwitter.text = "Twitter: " +
                        if (randomFoundMember.twitter != "") randomFoundMember.twitter else "none"
            } else {
                binding.tvMemberName.text = "nothing found, check your party input"
                Toast.makeText(activity, "Nothing found by \'" + selectedParty + "\' keyword", Toast.LENGTH_SHORT).show()
                binding.tvMemberBirthYear.text = ""
                binding.tvDistrict.text = ""
                binding.tvMemberTwitter.text = ""
            }

        binding.btnToBack.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_partyDetailsFragment_to_selectPartyFragment)
        }

        binding.btnToMain.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_partyDetailsFragment_to_titleFragment)
        }

        return binding.root
    }
}