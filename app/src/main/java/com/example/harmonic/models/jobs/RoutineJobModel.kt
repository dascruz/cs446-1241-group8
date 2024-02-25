package com.example.harmonic.models.jobs

import com.example.harmonic.models.IJobInstanceModel
import com.example.harmonic.models.IJobModel
import com.example.harmonic.models.instances.RoutineInstanceModel
import org.json.JSONObject
import java.util.UUID
import java.time.Duration

class RoutineJobModel(override var name: String)  : IJobModel {
    override val id = UUID.randomUUID()
    var maxRoutineTime: Duration? = null
        private set
    private val instances : MutableList<RoutineInstanceModel> = mutableListOf()
    override fun getAllInstances(): List<IJobInstanceModel> {
        return instances
    }

    override fun addInstance(instance: IJobInstanceModel) : Unit {
        if (instance !is RoutineInstanceModel) {
            throw TypeCastException()
        }
        if (maxRoutineTime != null && instance.getTotalTime() <= maxRoutineTime) {
            instances.add(instance)
        }
        // else throw some kind exception
    }

    override fun removeInstance(index: Int) {
        instances.removeAt(index)
    }

    override fun getShareable(): JSONObject {
        TODO("Not yet implemented")
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