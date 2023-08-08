package ru.binnyatoff.starwars.screens.favoriteScreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.binnyatoff.starwars.R
import ru.binnyatoff.starwars.data.database.ResultMainDTO
import ru.binnyatoff.starwars.screens.favoriteScreen.adapter.FavoriteScreenAdapter
import ru.binnyatoff.starwars.screens.homeScreen.adapter.ClickDelegate

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.obtainEvent(FavoriteEvent.ScreenInit)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FavoriteScreenAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter.attachDelegate(object : ClickDelegate {
            override fun onClick(resultMain: ResultMainDTO) {
                viewModel.obtainEvent(FavoriteEvent.DeleteItem(resultMain))
            }

        })

        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                FavoriteState.Empty -> TODO()
                is FavoriteState.Loaded -> adapter.submitList(state.resultMain)
                FavoriteState.Loading -> TODO()
            }
        }

    }
}