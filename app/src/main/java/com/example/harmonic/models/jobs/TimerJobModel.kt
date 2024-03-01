package com.example.harmonic.models.jobs

import com.example.harmonic.models.IJobInstanceModel
import com.example.harmonic.models.IJobModel
import com.example.harmonic.models.instances.TimerInstanceModel
import org.json.JSONObject
import java.util.UUID

enum class SegmentationTypes { NONE, SET, CAP }

class TimerJobModel(override var name: String,
    segmentationType: SegmentationTypes = SegmentationTypes.NONE,
    segmentationValue: Int = 1
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
        return instances
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

    override fun removeInstance(index: Int) {
        instances.removeAt(index)
    }

    override fun getShareable(): JSONObject {
        TODO("Not yet implemented")
    }

    override fun getActiveInstances(): List<IJobInstanceModel> {
        return instances.filter{ instance -> instance.active }
    }

    override fun getFriendInstances(): List<IJobInstanceModel> {
        return instances.filter{ instance -> !instance.internal }
    }

    override fun getInternalInstances(): List<IJobInstanceModel> {
        return instances.filter{ instance -> instance.internal }
    }

    override fun intakeSharable(shareable: JSONObject) {
        TODO("Not yet implemented")
    }



}