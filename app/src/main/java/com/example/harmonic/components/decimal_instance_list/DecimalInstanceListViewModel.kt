package com.example.harmonic.components.decimal_instance_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.harmonic.data.DecimalInstance.DecimalInstanceRepository
import com.example.harmonic.models.instances.DecimalInstanceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class DecimalInstancesListViewModel
@Inject
constructor(
        savedStateHandle: SavedStateHandle,
        private val decimalInstanceRepository: DecimalInstanceRepository
) : ViewModel() {
    private val jobIdString: String = savedStateHandle.get<String>("jobId")!!
    val jobId: Int = jobIdString.toInt()
    val allDecimalInstancesFlow: Flow<List<DecimalInstanceModel>> =
            decimalInstanceRepository.observeInstancesForJob(jobId)
    val jobName: String = savedStateHandle.get<String>("jobName")!!

    suspend fun createNewDecimalInstance(instanceNum: Int): Int {
        println("Started creating new decimal instance")
        val newDecimalInstance = DecimalInstanceModel(internal = true)
        newDecimalInstance.updateJobInfo(id = jobId, name = jobName, num = instanceNum)

        val newKey = decimalInstanceRepository.insertLocal(newDecimalInstance)

        println("Created New Decimal Instance with id $newKey")
        return newKey
    }
}
