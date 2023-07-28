package com.example.rickandmorty.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.rickandmorty.R
import com.example.rickandmorty.repository.data_models.EpisodeDescription

class CharacterDetailListAdapter(private val context: Context) : BaseAdapter() {
    private var dataList: List<EpisodeDescription> = emptyList()

    override fun getCount(): Int = dataList.size

    override fun getItem(position: Int): Any = dataList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater
            .from(parent?.context ?: context)
            .inflate(R.layout.episode_item, parent, false)
        val holder: ViewHolder = view.tag as? ViewHolder ?: ViewHolder(view)
        val item = dataList[position]
        with(holder) {
            name.text = item.episodeName
            date.text = item.episodeAirDate
        }
        return view
    }

    private class ViewHolder(view: View) {
        val name: TextView = view.findViewById(R.id.episode_name)
        val date: TextView = view.findViewById(R.id.episode_date)
    }

    fun setList(newDataList: List<EpisodeDescription>) {
        dataList = newDataList
    }

}




