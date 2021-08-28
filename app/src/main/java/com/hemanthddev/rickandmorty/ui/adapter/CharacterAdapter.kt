package com.hemanthddev.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import com.hemanthddev.rickandmorty.data.model.Character
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hemanthddev.rickandmorty.databinding.ItemCharacterBinding
import javax.inject.Inject

class CharacterAdapter @Inject constructor() :
    PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterComparator) {

    var onCharacterClickListener: ((binding: ItemCharacterBinding, character: Character) -> Unit)? =
        null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterViewHolder(
            binding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onCharacterClickListener = onCharacterClickListener
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class CharacterViewHolder(
        private val binding: ItemCharacterBinding,
        onCharacterClickListener: ((binding: ItemCharacterBinding, character: Character) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onCharacterClickListener?.invoke(
                    binding,
                    getItem(absoluteAdapterPosition) as Character
                )
            }
        }

        fun bind(item: Character) = with(binding) {
            ViewCompat.setTransitionName(binding.ivAvatar, "avatar_${item.id}")
            ViewCompat.setTransitionName(binding.tvName, "name_${item.id}")
            ViewCompat.setTransitionName(binding.tvStatus, "status_${item.id}")
            character = item
        }
    }

    object CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }

}