package com.ballflight6463.ui.play

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ballflight6463.R
import com.ballflight6463.databinding.ItemLeaderBinding
import com.ballflight6463.models.Leader
import com.ballflight6463.utils.Utils

class PlayAdapter : RecyclerView.Adapter<PlayAdapter.MyViewHolder>() {

    var playerList = listOf<Leader>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(playerList[position])
    }

    override fun getItemCount(): Int = playerList.size

    inner class MyViewHolder(private val binding: ItemLeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(leader: Leader) {
            val context = binding.root.context
            val playerTextColor = if (leader.name == Utils.playerName)
                R.color.white
            else
                R.color.white
            val textColor = ContextCompat.getColor(context, playerTextColor)
            binding.tvPlayerName.text = leader.name
            binding.tvPoint.text = leader.score.toString()
            binding.tvPlayerName.setTextColor(textColor)
        }
    }
}