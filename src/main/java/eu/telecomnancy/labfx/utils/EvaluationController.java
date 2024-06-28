package eu.telecomnancy.labfx.utils;

import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;

import java.util.ArrayList;
import java.util.Collections;

public class EvaluationController {
    private EvaluationController() {}


    public ArrayList<Evaluation> getEvaluationsByUserSorted (User user) {
        ArrayList<Evaluation> evaluationsByUser = user.getEvaluations();
        Collections.sort(evaluationsByUser, (o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt()));
        return evaluationsByUser;
    }


}
