package ru.binnyatoff.starwars.screens.homeScreen

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.binnyatoff.starwars.R
import ru.binnyatoff.starwars.data.database.ResultMainDTO
import ru.binnyatoff.starwars.screens.homeScreen.adapter.ClickDelegate
import ru.binnyatoff.starwars.screens.homeScreen.adapter.HomeScreenAdapter

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val searchView = view.findViewById<SearchView>(R.id.searchView)
        val circularProgressIndicator =
            view.findViewById<CircularProgressIndicator>(R.id.circularProgressIndicator)
        val adapter = HomeScreenAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val notFound = view.findViewById<ImageView>(R.id.notFound)
        val error = view.findViewById<ImageView>(R.id.error)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.attachDelegate(object :ClickDelegate{
            override fun onClick(resultMain: ResultMainDTO) {
                viewModel.obtainEvent(HomeEvent.AddToFavorite(resultMain))
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner) { homeState ->
            when (homeState) {
                HomeState.Empty -> {
                    notFound.visibility = View.VISIBLE
                    error.visibility = View.GONE
                    circularProgressIndicator.visibility = View.GONE
                }

                is HomeState.Error -> {
                    notFound.visibility = View.GONE
                    error.visibility = View.VISIBLE
                    circularProgressIndicator.visibility = View.GONE
                    Toast.makeText(requireContext(), homeState.e, Toast.LENGTH_LONG).show()
                }

                is HomeState.Loaded -> {
                    notFound.visibility = View.GONE
                    error.visibility = View.GONE
                    adapter.submitList(homeState.list)
                    circularProgressIndicator.visibility = View.GONE
                }

                HomeState.Loading -> {
                    notFound.visibility = View.GONE
                    error.visibility = View.GONE
                    circularProgressIndicator.visibility = View.VISIBLE
                }
            }
        }



        lifecycleScope.launch(Dispatchers.Main) {
            searchView.getQueryTextChangeStateFlow()
                .debounce(300)
                .distinctUntilChanged()
                .map {query->
                    if(query.isNotEmpty()){
                        viewModel.obtainEvent(HomeEvent.LoadPeople(query))
                    }
                }
                .collect()

        }

    }

    private fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {
        val query = MutableStateFlow("")

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length > 1) {
                    query.value = newText
                }
                return true
            }
        })
        return query
    }

}