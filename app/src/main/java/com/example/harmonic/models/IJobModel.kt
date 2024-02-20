package com.example.harmonic.models

import org.json.JSONObject
import java.util.UUID

interface IJobModel {
    val id: UUID
    var name: String

    fun getAllInstances(): List<IJobInstanceModel>
    fun addInstance(instance: IJobInstanceModel) : Unit
    fun removeInstance(index: Int): Unit
    fun getShareable(): JSONObject
    fun getFriendInstances(): List<IJobInstanceModel>
    fun getInternalInstances(): List<IJobInstanceModel>
    fun intakeSharable(shareable: JSONObject): Unit
}