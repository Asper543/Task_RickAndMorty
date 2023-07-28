package com.example.rickandmorty.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.CHARACTER_ID
import com.example.rickandmorty.R
import com.example.rickandmorty.RickAndMortyApplication
import com.example.rickandmorty.ui.adapters.CharactersRecycleViewAdapter
import com.example.rickandmorty.view_models.RickAndMortyViewModel
import javax.inject.Inject

class CharactersFragment : Fragment() {
    @Inject
    lateinit var model: RickAndMortyViewModel
    override fun onAttach(context: Context) {
        (context.applicationContext as RickAndMortyApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.loadCharacters()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.charcters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler: RecyclerView = view.findViewById(R.id.characters_recycler_view)
        recycler.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        val characterAdapter = CharactersRecycleViewAdapter {
            clickListener(it)
        }
        recycler.adapter = characterAdapter
        model.charactersData.observe(this) { data ->
            characterAdapter.setCharacters(data)
        }
    }

    @SuppressLint("ResourceType")
    private fun clickListener(id: Int) {
        val bundle = Bundle().apply { putInt(CHARACTER_ID, id) }
        bundle.putInt(CHARACTER_ID, id)
        findNavController().navigate(R.id.to_character_details, bundle)
    }
}