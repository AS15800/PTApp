package com.voidstudio.ptapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

internal class WorkoutAdapter (private var itemsTitle: List<List<String>>): RecyclerView.Adapter<WorkoutAdapter.MyViewHolder>(){
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTitle: TextView = view.findViewById(R.id.exercise_title)
        var itemSets: TextView = view.findViewById(R.id.exercise_sets)
        var itemReps: TextView = view.findViewById(R.id.exercise_reps)

    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.workout_card, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsTitle[position]
        holder.itemTitle.text = item[0]
        holder.itemSets.text = item[1]
        holder.itemReps.text = item[2]

    }
    override fun getItemCount(): Int {
        return itemsTitle.size
    }
}