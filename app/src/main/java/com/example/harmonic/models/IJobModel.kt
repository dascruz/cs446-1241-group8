package com.example.harmonic.models

import org.json.JSONObject

interface IJobModel {
    val id: Int?
    var name: String

    fun getAllInstances(): List<IJobInstanceModel>
    fun addInstance(instance: IJobInstanceModel)
    fun removeInstance(index: Int)
    fun getShareable(): JSONObject
    fun getActiveInstances(): List<IJobInstanceModel>
    fun getFriendInstances(): List<IJobInstanceModel>
    fun getInternalInstances(): List<IJobInstanceModel>
    fun intakeSharable(shareable: JSONObject)
}