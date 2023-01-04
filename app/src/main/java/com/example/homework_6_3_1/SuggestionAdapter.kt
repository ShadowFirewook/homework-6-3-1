package com.example.homework_6_3_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_6_3_1.databinding.ItemSuggestionBinding

class SuggestionAdapter(
    private var list: ArrayList<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionViewHolder {

            return SuggestionViewHolder(
                ItemSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false
                )
            )

        }

        override fun onBindViewHolder(holder: SuggestionViewHolder, position: Int) {

            holder.onBind(list[position])

        }

        override fun getItemCount() = list.size


        inner class SuggestionViewHolder(private val binding: ItemSuggestionBinding) : RecyclerView.ViewHolder(binding.root) {

            fun onBind(text: String) {
                binding.tvHashTags.text = text
                itemView.setOnClickListener{
                    onClick(text)
                }
            }

        }

}