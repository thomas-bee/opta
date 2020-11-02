package org.example.model

import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty
import org.optaplanner.core.api.domain.solution.PlanningScore
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore

@PlanningSolution // this class contains all input and output data
class Schedule {
    @ValueRangeProvider(id = "timeslotRange")
    @ProblemFactCollectionProperty // problem facts do not change
    List<Timeslot> timeslots

    @PlanningEntityCollectionProperty
    List<OB> observations
    @PlanningScore
    HardMediumSoftScore score
}