package com.example.harmonic.models.jobs

import com.example.harmonic.models.IJobInstanceModel
import com.example.harmonic.models.IJobModel
import com.example.harmonic.models.instances.TimerInstanceModel
import org.json.JSONObject
import java.time.Duration
import java.util.UUID
import kotlin.reflect.typeOf

enum class SegmentationTypes { NONE, SET, CAP }

class TimerJobModel(override var name: String,
    segmentationType: SegmentationTypes = SegmentationTypes.NONE,
    segmentationValue: Int = 1,
    val maxTotalTime: Duration? = null
) : IJobModel {
    override val id = UUID.randomUUID()
    private val instances : MutableList<TimerInstanceModel> = mutableListOf()
    var segmentationType: SegmentationTypes
        private set
    var segmentationValue: Int
        private set

    init {
        this.segmentationType = segmentationType
        this.segmentationValue = segmentationValue
    }
    override fun getAllInstances(): List<IJobInstanceModel> {
        return instances as List<TimerInstanceModel>
    }

    override fun addInstance(instance: IJobInstanceModel) : Unit {
        if (instance !is TimerInstanceModel) {
            throw TypeCastException()
        }
        val segments = instance.getSegments()
        if ((segmentationType == SegmentationTypes.CAP && segments.size > segmentationValue) ||
            (segmentationType == SegmentationTypes.SET && segments.size != segmentationValue)
            ) {
            throw Exception("Number of segments is incorrect for Segmentation Type")
        }
        instances.add(instance)
    }

    override fun removeInstance(index: Int): Unit {
        instances.removeAt(index)
    }

    override fun getShareable(): JSONObject {
        TODO("Not yet implemented")
    }

    override fun getFriendInstances(): List<IJobInstanceModel> {
        return instances.filter{ instance -> !instance.internal } as List<TimerInstanceModel>
    }

    override fun getInternalInstances(): List<IJobInstanceModel> {
        return instances.filter{ instance -> instance.internal } as List<TimerInstanceModel>
    }

    override fun intakeSharable(shareable: JSONObject): Unit {
        TODO("Not yet implemented")
    }



}