package fi.example.tiistai2501

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fi.example.tiistai2501.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val parliamentMembers = ParliamentMembersData.members

        var partiesList = mutableListOf<String>()
        parliamentMembers.forEach {
            partiesList.add(it.party)
        }

        partiesList = partiesList.toSet().toList() as MutableList<String>

        binding.tvParties.text = partiesList.joinToString()

        binding.btnRandom.setOnClickListener {
            val inputParty = binding.etInputParty.text.toString()
            if (partiesList.contains(inputParty)) {
                val definedPartiesList = parliamentMembers.filter {
                    it.party.equals(inputParty)
                }
                println(definedPartiesList.joinToString())

                val randomFoundMember = definedPartiesList.random()

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
                binding.tvMemberBirthYear.text = ""
                binding.tvDistrict.text = ""
                binding.tvMemberTwitter.text = ""
            }
        }
    }
}