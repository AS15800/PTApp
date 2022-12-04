package com.voidstudio.ptapp

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class WorkoutItems(val workout_title: String, val workout_reps: String, val workout_sets: String)

class WorkoutAdapter(private val ItemsList: List<WorkoutItems>): RecyclerView.Adapter<WorkoutAdapter.ViewHolder>(){
    private lateinit var mListener: OnItemClickListerner

    interface OnItemClickListerner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listerner: OnItemClickListerner){
        mListener = listerner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workout_card, parent, false)

        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val workoutItems = ItemsList[position]

        holder.workoutTitle.text = workoutItems.workout_title
        holder.workoutSets.text = workoutItems.workout_sets
        holder.workoutReps.text = workoutItems.workout_reps
    }

    override fun getItemCount(): Int{
        return ItemsList.size
    }

    class ViewHolder(itemView: View, listerner: OnItemClickListerner): RecyclerView.ViewHolder(itemView){

        val workoutTitle: TextView = itemView.findViewById(R.id.workout_title)
        val workoutSets: TextView = itemView.findViewById(R.id.workout_sets)
        val workoutReps: TextView = itemView.findViewById(R.id.workout_reps)

        init {
            itemView.setOnClickListener {
                listerner.onItemClick(adapterPosition)
            }
        }
    }

}