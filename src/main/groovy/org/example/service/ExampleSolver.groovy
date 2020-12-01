package org.example.service

import org.optaplanner.benchmark.api.PlannerBenchmark
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore
import org.optaplanner.core.api.score.ScoreManager
import org.optaplanner.core.api.solver.Solver
import org.optaplanner.core.api.solver.SolverFactory
import org.example.model.Timeslot
import org.example.model.OB
import org.example.model.Schedule

class ExampleSolver {
    void run() {
        println "***** Put 10 Observations into 10 TimeSlots"
        Schedule problem = new Schedule()
        problem.observations = (1..10).collect(it -> new OB(it))
        problem.timeslots = (1..10).collect(it -> new Timeslot(it))
        solve(problem) // => gives, as expected, 0hard/0medium/0soft

        println "***** Put 11 Observations into 10 TimeSlots"
        problem = new Schedule()
        problem.observations = (1..11).collect(it -> new OB(it))
        problem.timeslots = (1..10).collect(it -> new Timeslot(it))
        solve(problem) // => gives, as expected, 0hard/-1medium/0soft

        println "***** Put 12 Observations into 10 TimeSlots"
        problem = new Schedule()
        problem.observations = (1..12).collect(it -> new OB(it))
        problem.timeslots = (1..10).collect(it -> new Timeslot(it))
        solve(problem) // => gives, UNEXPECTEDLY, -1hard/-1medium/0soft
    }

    void solve(Schedule problem) {
        SolverFactory<Schedule> solverFactory = SolverFactory.createFromXmlResource("SolverConfig.xml")
        Solver<Schedule> solver = solverFactory.buildSolver()
        ScoreManager<Schedule, HardMediumSoftScore> scoreManager = ScoreManager.create(solverFactory)
        Schedule solution = solver.solve(problem)
        println scoreManager.explainScore(solution)
        if (solution.getScore().isFeasible()) {
            println "==> found solution with score ${solution.getScore()}"
        } else {
            println "==> only found infeasible with score ${solution.getScore()}"
        }
//        PlannerBenchmarkFactory benchmarkFactory =
//                PlannerBenchmarkFactory.createFromSolverConfigXmlResource("SolverConfig.xml")
//        PlannerBenchmark benchmark = benchmarkFactory.buildPlannerBenchmark(problem)
//        benchmark.benchmarkAndShowReportInBrowser()
    }
}
