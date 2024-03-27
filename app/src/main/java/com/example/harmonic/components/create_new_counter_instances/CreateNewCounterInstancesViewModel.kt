package com.example.harmonic.components.create_new_counter_instances


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.CounterInstance.CounterInstanceRepository
import com.example.harmonic.models.instances.CounterInstanceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateNewCounterInstanceViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val counterInstanceRepository: CounterInstanceRepository
) : ViewModel() {

    private val instanceIdString: String = savedStateHandle.get<String>("instanceId")!!
    private val instanceId: Int = instanceIdString.toInt()
    private val _instance = MutableStateFlow(CounterInstanceModel(id = instanceId))
    val instance = _instance.asStateFlow()

    suspend fun setCount(count : Int) {
        counterInstanceRepository.updateCount(instanceId, count)
    }


}