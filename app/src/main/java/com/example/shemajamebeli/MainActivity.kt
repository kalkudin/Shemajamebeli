
package com.example.shemajamebeli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shemajamebeli.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding:ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val inputList: MutableList<String> = mutableListOf()
        val uniqueStrings = mutableSetOf<String>()

        binding.saveButton.setOnClickListener(){
            val input = binding.inputText.text.toString()
            if(checkEmptyField(input)){
                Toast.makeText(this, "Please Fill in the Field", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            inputList.add(input)
            binding.allInputCounter.text = "Input count: ${inputList.size}"
        }

        binding.outputButton.setOnClickListener(){
            binding.anagramCounter.text = "Anagrams Count: ${searchListForAnagrams(inputList, uniqueStrings)}"
        }
    }
    private fun checkEmptyField(input:String):Boolean{
        return input.isNullOrBlank()
    }

    private fun searchListForAnagrams(listToSearch:MutableList<String>, uniqueStrings:MutableSet<String>):Int{
        var totalCount = 0
        for (i in 0 until listToSearch.size) {
            val str1 = listToSearch[i]
            if (uniqueStrings.add(str1)) {
                for (j in i + 1 until listToSearch.size) {
                    val str2 = listToSearch[j]
                    if (areAnagrams(str1, str2)) {
                        totalCount++
                    }
                }
            }
        }
        return totalCount
    }
    private fun areAnagrams(element1: String, element2: String):Boolean {
        val cleanedElement1 = element1.replace(" ", "").lowercase(Locale.ROOT)
        val cleanedElement2 = element2.replace(" ", "").lowercase(Locale.ROOT)
        return cleanedElement1.toCharArray().sorted() == cleanedElement2.toCharArray().sorted()
    }
}
