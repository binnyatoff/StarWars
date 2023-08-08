package ru.binnyatoff.starwars.screens.homeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.binnyatoff.starwars.data.database.Dao
import ru.binnyatoff.starwars.data.database.ResultMainDTO
import ru.binnyatoff.starwars.data.network.Api
import ru.binnyatoff.starwars.data.network.toResultMainDTO
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val api: Api, private val dao: Dao) : ViewModel(),
    ObtainEvent {
    private val _viewState = MutableLiveData<HomeState>()
    val viewState: LiveData<HomeState> = _viewState

    override fun obtainEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadPeople ->  getResults(event.searchQuery)
            is HomeEvent.AddToFavorite -> addToFavorite(event.resultMain)
        }
    }

    private fun getResults(search: String) {
        _viewState.postValue(HomeState.Loading)
        viewModelScope.launch {
            try {
                val responsePeople = api.getPeople(search)
                val responseStarships = api.getStarships(search)
                val responsePlanets = api.getPlanets(search)
                if (responsePeople.isSuccessful && responseStarships.isSuccessful && responsePlanets.isSuccessful) {
                    val bodyPeople = responsePeople.body()
                    val bodyStarships = responseStarships.body()
                    val bodyPlanets = responsePlanets.body()
                    if (bodyPlanets != null || bodyStarships != null || bodyPeople != null) {
                        val peopleMainList: List<ResultMainDTO>? =
                            bodyPeople?.results?.map { resultPeople ->
                                resultPeople.toResultMainDTO()
                            }
                        val planetsMainList: List<ResultMainDTO>? =
                            bodyPlanets?.results?.map { resultPlanets ->
                                resultPlanets.toResultMainDTO()
                            }
                        val starshipsMainList: List<ResultMainDTO>? =
                            bodyStarships?.results?.map { resultStarships ->
                                resultStarships.toResultMainDTO()
                            }
                        val resultList = createResultList(
                            peopleMainList,
                            planetsMainList,
                            starshipsMainList
                        )
                        _viewState.postValue(
                            HomeState.Loaded(
                                resultList
                            )
                        )
                    } else {
                        _viewState.postValue(HomeState.Empty)
                    }
                } else {
                    _viewState.postValue(HomeState.Error("Error"))
                }
            } catch (e: Exception) {
                _viewState.postValue(HomeState.Error("$e"))
            }
        }
    }

    private fun createResultList(
        peopleMainList: List<ResultMainDTO>?,
        planetsMainList: List<ResultMainDTO>?,
        starshipsMainList: List<ResultMainDTO>?,
    ): List<ResultMainDTO> {
        val resultList = mutableListOf<ResultMainDTO>()
        if (peopleMainList != null) {
            resultList.addAll(peopleMainList)
        }
        if (planetsMainList != null) {
            resultList.addAll(planetsMainList)
        }
        if (starshipsMainList != null) {
            resultList.addAll(starshipsMainList)
        }
        return resultList
    }

    private fun addToFavorite(resultMain: ResultMainDTO) {
        viewModelScope.launch {
            dao.insertResult(resultMain)
        }
    }
}