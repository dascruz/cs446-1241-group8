package com.example.harmonic.models.jobs

import com.example.harmonic.models.IJobModel
import com.example.harmonic.models.IJobInstanceModel
import org.json.JSONObject

class CounterJobModel(override var name: String, val InitialValue : Int, override val id: Int? = null) : IJobModel{
    override fun getAllInstances(): List<IJobInstanceModel> {
        return emptyList()}
    override fun addInstance(instance: IJobInstanceModel) : Unit {

    }

    override fun removeInstance(index: Int) {

    }

    override fun getShareable(): JSONObject {
        TODO("Not yet implemented")
    }

    override fun getActiveInstances(): List<IJobInstanceModel> {
        return emptyList()
    }

    override fun getFriendInstances(): List<IJobInstanceModel> {
        return emptyList()
    }

    override fun getInternalInstances(): List<IJobInstanceModel> {
        return emptyList()
    }

    override fun intakeSharable(shareable: JSONObject) {
        TODO("Not yet implemented")
    }

}