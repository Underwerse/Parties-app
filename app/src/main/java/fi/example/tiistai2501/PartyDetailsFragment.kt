package fi.example.tiistai2501

import android.annotation.SuppressLint
import android.graphics.ImageDecoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import fi.example.tiistai2501.databinding.FragmentPartyDetailsBinding

class PartyDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPartyDetailsBinding
    private lateinit var viewModel: ParliamentMemberViewModel
    private lateinit var selectedParty: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_party_details,container,false)

        Log.i("ParliamentMemberViewModel", "Called ViewModelProvider.get()")
        selectedParty = arguments?.getString("selectedParty") ?: ""

        viewModel = ViewModelProvider(this).get(ParliamentMemberViewModel::class.java)
        viewModel.currentParty = selectedParty

        if (viewModel.currentParty != "")
            binding.tvPartyTitle.text = "Selected party: " + viewModel.currentParty
        else binding.tvPartyTitle.text = "Selected party: nothing selected"

        updateUI()

        binding.btnToBack.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_partyDetailsFragment_to_selectPartyFragment)
        }

        binding.btnToMain.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_partyDetailsFragment_to_titleFragment)
        }

        binding.btnRandom.setOnClickListener { view : View ->
            viewModel.getMember(selectedParty)
            updateUI()
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    fun updateUI() {
        val imgResName = "@drawable/" + selectedParty
        val imageID = resources.getIdentifier(imgResName, "drawable", activity?.getPackageName())
        binding.imgParty.setImageResource(imageID)

        binding.tvMemberName.text = viewModel.currentMember.last +
                ", " + viewModel.currentMember.first
        binding.tvMemberBirthYear.text = "Borned in " +
                viewModel.currentMember.bornYear.toString()
        binding.tvDistrict.text = "District: " +
                viewModel.currentMember.constituency
        binding.tvMemberTwitter.text = "Twitter: " +
                if (viewModel.currentMember.twitter != "") viewModel.currentMember.twitter else "none"

    }
}