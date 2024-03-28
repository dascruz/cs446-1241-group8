package com.example.harmonic.models.instances

import com.example.harmonic.models.IJobInstanceModel
import java.math.BigDecimal
import java.time.Instant

class DecimalInstanceModel(
        override val id: Int? = null,
        override var active: Boolean = true,
        override val creationDateTime: Instant = Instant.now(),
        override var internal: Boolean = false,
        initJobId: Int? = null,
        initJobName: String? = null,
        initJobInstanceNum: Int? = null,
        initFriendId: Int? = null
) : IJobInstanceModel {
    var value: BigDecimal = BigDecimal.ZERO
    override var jobId: Int? = null
    override var jobName: String? = null
    override var jobInstanceNum: Int? = null
    override var friendId: Int? = null
    override val instanceTypeString = "Decimal"

    init {
        if (initFriendId != null) {
            friendId = initFriendId
        }

        if (initJobId != null && initJobName != null && initJobInstanceNum != null) {
            updateJobInfo(initJobId, initJobName, initJobInstanceNum)
        }
    }

    fun getValue(): BigDecimal {
        return value
    }

    fun updateCount(count: BigDecimal) {
        value = count
    }

    fun reset() {
        value = BigDecimal.ZERO
    }

    override fun compareTo(other: IJobInstanceModel): Int {
        return super.compareTo(other)
    }
}
