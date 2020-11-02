package org.example.model

import org.optaplanner.core.api.domain.lookup.PlanningId

class Timeslot {
    @PlanningId
    int slot

    Timeslot(int slot) {
        this.slot = slot
    }
}
