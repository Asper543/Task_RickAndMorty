package com.example.rickandmorty.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.rickandmorty.CHARACTER_ID
import com.example.rickandmorty.R
import com.example.rickandmorty.RickAndMortyApplication
import com.example.rickandmorty.ui.adapters.CharacterDetailListAdapter
import com.example.rickandmorty.view_models.CharacterDetailViewModel
import javax.inject.Inject

class CharacterDetailFragment : Fragment() {
    @Inject
    lateinit var model: CharacterDetailViewModel

    override fun onAttach(context: Context) {
        (context.applicationContext as RickAndMortyApplication).appComponent.inject(this)
        super.onAttach(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(CHARACTER_ID)?.let {
            model.loadCharacter(it)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_details_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image: ImageView = view.findViewById(R.id.character_detail_image)
        val name: TextView = view.findViewById(R.id.character_detail_name)
        val gender: TextView = view.findViewById(R.id.character_detail_gender)
        val species: TextView = view.findViewById(R.id.character_detail_species)
        val origin: TextView = view.findViewById(R.id.character_detail_origin)
        val listView: ListView = view.findViewById(R.id.episode_list_view)
        val adapterList = CharacterDetailListAdapter(requireContext())
        listView.adapter = adapterList
        model.episodesData.observe(this) {
            if (it!=null)adapterList.setList(it)
        }
        model.characterDetailData.observe(this){
            Glide.with(view).load(it.image).circleCrop().into(image)
            name.text= it.name
            gender.text=it.gender
            species.text= it.species
            origin.text = it.originInfo.name
        }
    }
}