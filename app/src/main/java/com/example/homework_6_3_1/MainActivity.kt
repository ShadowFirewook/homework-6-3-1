package com.example.homework_6_3_1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_6_3_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var hashtagList = arrayListOf<String>()
    private var suggestionAdapter = SuggestionAdapter(hashtagList,this::onClick)
    private var oldHashTag = ""
    private var oldPositionCursor:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        savedHashTagsEditText()
        initListener()
    }

    private fun initListener() {
        binding.editText.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(text : CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                val words = text.toString().replace(","," ").split(" ")

                for (word in words){
                    if (word.startsWith("#")){
                        oldHashTag = word

                        val cursorPosition:Int = binding.editText.selectionEnd
                        oldPositionCursor = cursorPosition
                    }
                    binding.rvSuggestion.isVisible = word.startsWith("#")
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun initAdapter() {
        binding.rvSuggestion.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = suggestionAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun savedHashTagsEditText() {
        binding.btnSave.setOnClickListener {
            if (binding.editText.text.isNotEmpty()){
                val suggestions = binding.editText.text.toString().replace(",", " ")
                val words = suggestions.split(" ")

                for (word in words){
                    if (word.startsWith("#")){
                        hashtagList.add(word)
                    }
                }
                binding.editText.text.clear()
                suggestionAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this,"Поле не может быть пустым",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun onClick(hashTag:String){
        binding.rvSuggestion.isVisible = false
        binding.editText.setText(binding.editText.text.toString().replace(oldHashTag,hashTag))
        binding.editText.setSelection(binding.editText.text.length)
    }

}