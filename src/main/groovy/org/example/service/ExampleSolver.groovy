package org.example.service

import org.optaplanner.core.api.score.ScoreManager
import org.optaplanner.core.api.solver.Solver
import org.optaplanner.core.api.solver.SolverFactory
import org.example.model.Timeslot
import org.example.model.OB
import org.example.model.Schedule

class ExampleSolver {
    void run() {
        // create 500 timeslots, 500 observations, solve
        println "***** Put 500 Observations into 500 TimeSlots"
        Schedule problem = new Schedule()
        problem.observations = (1..500).collect(it -> new OB(it))
        problem.timeslots = (1..500).collect(it -> new Timeslot(it))
        solve(problem) // ==> found solution with score 0hard/0medium/0soft

        // create 500 timeslots, 501 observations, solve
        println "***** Put 501 Observations into 500 TimeSlots"
        problem = new Schedule()
        problem.observations = (1..501).collect(it -> new OB(it))
        problem.timeslots = (1..500).collect(it -> new Timeslot(it))
        solve(problem) // ==> found solution with score 0hard/-1medium/0soft

        // create 500 timeslots, 502 observations, solve
        println "***** Put 502 Observations into 500 TimeSlots"
        problem = new Schedule()
        problem.observations = (1..502).collect(it -> new OB(it))
        problem.timeslots = (1..500).collect(it -> new Timeslot(it))
        solve(problem) // ==> found solution with score 0hard/-1medium/0soft
    }

    void solve(Schedule problem) {
        SolverFactory<Schedule> solverFactory = SolverFactory.createFromXmlResource("SolverConfig.xml")
        Solver<Schedule> solver = solverFactory.buildSolver()
        ScoreManager<Schedule> scoreManager = ScoreManager.create(solverFactory)
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
