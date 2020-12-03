package org.example.service

import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore
import org.optaplanner.core.api.score.stream.Constraint
import org.optaplanner.core.api.score.stream.ConstraintFactory
import org.optaplanner.core.api.score.stream.ConstraintProvider
import org.example.model.OB

class ScheduleConstraintProvider implements ConstraintProvider {
    @Override
    Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        [
            notSameSlot(constraintFactory), 
            notAssignable(constraintFactory)
        ]
    }

    Constraint notSameSlot(ConstraintFactory constraintFactory) {
        constraintFactory.fromUniquePair(OB.class)
            .filter({ ob1, ob2 ->
                ob1.start !== null &&
                ob2.start !== null &&
                ob1.start.slot == ob2.start.slot
            })
            .penalize("overlap", HardMediumSoftScore.ONE_HARD)
    }

    Constraint notAssignable(ConstraintFactory constraintFactory) {
        constraintFactory.fromUnfiltered(OB.class)
            .filter(ob -> ob.start == null)
            .penalize("not assignable", HardMediumSoftScore.ONE_MEDIUM)
    }
}
