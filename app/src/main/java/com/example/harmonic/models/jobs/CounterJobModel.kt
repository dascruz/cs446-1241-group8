package com.example.harmonic.models.jobs

import com.example.harmonic.models.IJobModel
import com.example.harmonic.models.IJobInstanceModel
import com.example.harmonic.models.instances.CounterInstanceModel

import org.json.JSONObject

class CounterJobModel(override var name: String,override val id: Int? = null) : IJobModel{
    private  val instances: MutableList<CounterInstanceModel> = mutableListOf()
    override fun getAllInstances(): List<IJobInstanceModel> {
        return instances}
    override fun addInstance(instance: IJobInstanceModel) : Unit {
        if (instance is CounterInstanceModel) {
            instances.add(instance)
        } else {
            throw TypeCastException("Instance is not of type CounterInstanceModel")
        }
    }

    override fun removeInstance(index: Int) {
        instances.removeAt(index)
    }

    override fun getShareable(): JSONObject {
        TODO("Not yet implemented")
    }

    override fun getActiveInstances(): List<IJobInstanceModel> {
        return instances.filter{ instance -> instance.active}
        }

    override fun getFriendInstances(): List<IJobInstanceModel> {
        return instances.filter{ instance -> !instance.internal}
    }

    override fun getInternalInstances(): List<IJobInstanceModel> {
        return instances.filter{ instance -> instance.internal}
    }

    override fun intakeSharable(shareable: JSONObject) {
        TODO("Not yet implemented")
    }

}