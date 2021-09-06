package com.example.jogtracker.ui.screens.jogslistfragment.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.JogEntity
import com.example.jogtracker.R
import com.example.jogtracker.databinding.JogsListGridItemBinding

class JogListAdapter(
    val updateJog: (jogEntity: JogEntity) -> Unit
) :
    ListAdapter<JogEntity, JogListAdapter.JogsViewHolder>(JogsDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.jogs_list_grid_item, parent, false)
        return JogsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JogsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class JogsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = JogsListGridItemBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(jog: JogEntity?) = with(itemView) {
            binding.jogEntity = jog

            binding.editImageButton.setOnClickListener {
                hideAndOpenTextInput(
                    binding.distanceTextView,
                    binding.dateTextView,
                    binding.timeTextView
                )
                Log.d("TAG", "bind: ${jog?.id} ")
                Log.d("TAG", "bind: ${jog?.userId} ")
                binding.updateImageButton.isVisible = !binding.updateImageButton.isVisible
            }

            binding.updateImageButton.setOnClickListener {
                updateJog(jog, binding)
            }
            binding.dateTextInput.setOnEditorActionListener(object :
                TextView.OnEditorActionListener {
                override fun onEditorAction(
                    v: TextView?,
                    actionId: Int,
                    event: KeyEvent?
                ): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        return true
                    }
                    return false
                }
            })
        }
    }

    private fun updateJog(jog: JogEntity?, binding: JogsListGridItemBinding) {
        if (jog != null) {
            updateJog(
                JogEntity(
                    id = jog.id,
                    userId = jog.userId,
                    distance = binding.distanceTextInput.text.toString().toDouble(),
                    time = binding.timeTextInput.text.toString().toInt(),
                    date = binding.dateTextInput.text.toString()
                )
            )
            hideAndOpenTextInput(
                binding.distanceTextView,
                binding.dateTextView,
                binding.timeTextView
            )
            binding.updateImageButton.isVisible = !binding.updateImageButton.isVisible
        }

    }

    private fun hideAndOpenTextInput(vararg view: View) {
        view.forEach {
            it.isEnabled = !it.isEnabled
        }
    }
}


private object JogsDiffCallback : DiffUtil.ItemCallback<JogEntity>() {
    override fun areItemsTheSame(oldItem: JogEntity, newItem: JogEntity): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: JogEntity, newItem: JogEntity): Boolean =
        oldItem.distance == newItem.distance
}

