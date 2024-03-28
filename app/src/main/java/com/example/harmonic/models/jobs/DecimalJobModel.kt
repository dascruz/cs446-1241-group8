package com.example.harmonic.models.jobs

import com.example.harmonic.models.IJobInstanceModel
import com.example.harmonic.models.IJobModel
import com.example.harmonic.models.instances.DecimalInstanceModel
import org.json.JSONObject

class DecimalJobModel(
        override val id: Int? = null,
        override var name: String,
        val instances: MutableList<DecimalInstanceModel> = mutableListOf(),
        var numDecimals: Int
) : IJobModel {

    override fun getAllInstances(): List<IJobInstanceModel> {
        return instances
    }

    override fun addInstance(instance: IJobInstanceModel) {
        if (instance !is DecimalInstanceModel) {
            throw TypeCastException()
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
        return instances.filter { instance -> instance.active }
    }

    override fun getFriendInstances(): List<IJobInstanceModel> {
        return instances.filter { instance -> !instance.internal }
    }

    override fun getInternalInstances(): List<IJobInstanceModel> {
        return instances.filter { instance -> instance.internal }
    }

    override fun intakeSharable(shareable: JSONObject) {
        TODO("Not yet implemented")
    }
}
