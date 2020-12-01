package org.example.model

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.lookup.PlanningId
import org.optaplanner.core.api.domain.variable.PlanningVariable

@PlanningEntity
public class OB {
    @PlanningVariable(nullable = true, valueRangeProviderRefs = "timeslotRange")
    Timeslot start

    @PlanningId
    int obId

    OB(int obId) {
        this.obId = obId
    }

    OB() {}
}
