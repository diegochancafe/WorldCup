package com.diegochancafe.worldcup.view.fragment.match.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diegochancafe.worldcup.databinding.ViewMatchItemBinding
import com.diegochancafe.worldcup.domain.model.MatchModelDomain
import com.diegochancafe.worldcup.view.callback.IMatchCallback
import java.util.ArrayList

// --
class MatchAdapter(private val listener: IMatchCallback, private val appContext: Context): RecyclerView.Adapter<MatchAdapter.ViewHolder>() {
    // --
    private var list = ArrayList<MatchModelDomain>()

    // --
    class ViewHolder(viewBinding: ViewMatchItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        // --
//        val item: LinearLayout = viewBinding.lyItem
        val itemCountryHome: TextView = viewBinding.tvCountryHome
        val itemCountryAway: TextView = viewBinding.tvCountryAway
        // --
        val itemScoreHome: TextView = viewBinding.tvScoreHome
        val itemScoreAway: TextView = viewBinding.tvScoreAway
        // --
        val itemPhotoHome: ImageView = viewBinding.ivPhotoHome
        val itemPhotoAway: ImageView = viewBinding.ivPhotoAway
    }

    // --
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewMatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    // --
    override fun getItemCount(): Int {
        return list.size
    }

    // --
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // --
        val data: MatchModelDomain = list[position]
        // --
        holder.itemCountryHome.text = data.homeTeamEn
        holder.itemCountryAway.text = data.awayTeamEn
        // --
        holder.itemScoreHome.text = data.homeScore.toString()
        holder.itemScoreAway.text = data.awayScore.toString()
        // --
        Glide.with(appContext)
            .load(data.homeFlag)
            .fitCenter()
            .into(holder.itemPhotoHome)

        Glide.with(appContext)
            .load(data.awayFlag)
            .fitCenter()
            .into(holder.itemPhotoAway)
//        // --
//        holder.item.setOnClickListener {
////            listener.onItemPokemonClicked(data)
//        }

    }

    // --
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<MatchModelDomain>) {
        // --
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}