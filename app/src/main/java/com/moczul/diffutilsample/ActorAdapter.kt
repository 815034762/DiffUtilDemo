package com.moczul.diffutilsample

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ActorAdapter(initActorsList: List<Actor>) : RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    private val actors = mutableListOf<Actor>()

    init {
        actors.addAll(initActorsList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_actor, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = actors.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(actors[position].name)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {

        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        } else {
            var myViewHolder: ViewHolder = holder
            var bundle: Bundle = payloads.get(0) as Bundle//as 强转
            if (bundle.getString("name") != null) {
                myViewHolder.bind(bundle.getString("name"))
            }
        }
//        super.onBindViewHolder(holder, position, payloads)
    }


    fun changeNmae(mNewDatas: List<Actor>) {


        val diffCallback = ActorDiffCallback(this.actors, mNewDatas)
        //需不需要检查有item移除,true就是需要检查，false就不需要
        val diffResult = DiffUtil.calculateDiff(diffCallback, false)

        this.actors.clear()
        this.actors.addAll(mNewDatas)
        diffResult.dispatchUpdatesTo(this)
    }

    fun swap(mNewDatas: List<Actor>) {

        var startTime: Long = System.currentTimeMillis()
        Log.e("tag", " start time " + startTime)
        val diffCallback = ActorDiffCallback(this.actors, mNewDatas)
        //需不需要检查有item移除,true就是需要检查，false就不需要
        val diffResult = DiffUtil.calculateDiff(diffCallback, true)

        this.actors.clear()
        this.actors.addAll(mNewDatas)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.actor_name)

        fun bind(text: String) {
            name.text = text
        }

    }
}