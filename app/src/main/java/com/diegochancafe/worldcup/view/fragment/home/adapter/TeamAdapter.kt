package com.diegochancafe.worldcup.view.fragment.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diegochancafe.worldcup.databinding.ViewBodyTeamItemBinding
import com.diegochancafe.worldcup.databinding.ViewHeaderTeamItemBinding
import com.diegochancafe.worldcup.domain.model.TeamModelDomain
import java.util.*

// --
private const val TYPE_HEADER: Int = 0
private const val TYPE_BODY: Int = 1
// --

class TeamAdapter(private val appContext: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    // --
    private var list = ArrayList<TeamModelDomain>()
    // --
    class HeadViewHolder(private val appContext: Context, private val viewBinding: ViewHeaderTeamItemBinding): RecyclerView.ViewHolder(viewBinding.root) {
        // --

        fun bind(teamModelDomain: TeamModelDomain) {
            // --
            val itemDescription: TextView = viewBinding.tvDescription
            itemDescription.text = teamModelDomain.nameEn
        }
    }

    // --
    class BodyViewHolder(private val appContext: Context, private val viewBinding: ViewBodyTeamItemBinding): RecyclerView.ViewHolder(viewBinding.root) {
        // --
        fun bind(teamModelDomain: TeamModelDomain) {
            // --
            val itemDescription: TextView = viewBinding.tvDescription
            val itemPhoto: ImageView = viewBinding.ivPhoto
            // --
            itemDescription.text = teamModelDomain.nameEn
            // --
            Glide.with(appContext)
                .load(teamModelDomain.flag)
                .into(itemPhoto)
        }
    }

    // --
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // --
        return if (viewType == TYPE_HEADER) {
            val view = ViewHeaderTeamItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            HeadViewHolder(appContext, view)
        } else {
            // --
            val view = ViewBodyTeamItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            // --
            BodyViewHolder(appContext, view)
        }
    }

    // --
    override fun getItemCount(): Int {
        return list.size
    }

    // --
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // --
        if (getItemViewType(position) == TYPE_HEADER) {
            (holder as HeadViewHolder).bind(list[position])
        } else {
            (holder as BodyViewHolder).bind(list[position])
        }
    }

    // --
    override fun getItemViewType(position: Int): Int {
        return if (list[position].index == 0) {
            TYPE_HEADER
        } else {
            TYPE_BODY
        }
    }

    // --
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<TeamModelDomain>) {
        // --
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}