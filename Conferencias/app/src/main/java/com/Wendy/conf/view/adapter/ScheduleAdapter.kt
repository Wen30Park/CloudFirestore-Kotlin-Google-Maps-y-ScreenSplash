package com.jair.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jair.conf.R
import com.jair.conf.model.conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

open class ScheduleAdapter(val scheduleListener: ScheduleListener): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    var listConference = ArrayList<conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false))

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val Conference = listConference[position] as conference

        holder.tvConferenceName.text = Conference.title
        holder.tvConferenceSpeaker.text = Conference.speaker
        holder.tvConferenceTag.text = Conference.tag

        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = Conference.datetime
        val hourFormat = simpleDateFormat.format(Conference.datetime)

        holder.tvConferenceHour.text = hourFormat
        holder.tvConferenceAMPM.text = simpleDateFormatAMPM.format(Conference.datetime).toUpperCase()

        holder.itemView.setOnClickListener {
            scheduleListener.onConferenceClicked(Conference, position)
        }
    }

    override fun getItemCount() = listConference.size

    fun updateData(data: List<conference>) {
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceName)
        val tvConferenceSpeaker = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceSpeaker)
        val tvConferenceTag = itemView.findViewById<TextView>(R.id.tvItemScheduleTag)
        val tvConferenceHour = itemView.findViewById<TextView>(R.id.tvItemScheduleHour)
        val tvConferenceAMPM = itemView.findViewById<TextView>(R.id.tvItemScheduleAMPM)
    }

    open fun onConferenceClicked(conference: conference, position: Int) {}
}