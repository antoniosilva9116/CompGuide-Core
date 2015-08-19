package cguide.utils;

import cguide.execution.entities.Condition;
import cguide.execution.entities.ConditionSet;
import cguide.execution.entities.Duration;
import cguide.execution.entities.Periodicity;
import cguide.execution.entities.TriggerCondition;
import cguide.parser.Composite.CyclePartDefinitionComposite;
import cguide.parser.Composite.CyclePartPeriodicityComposite;
import cguide.parser.Composite.DurationComposite;
import cguide.parser.Composite.PeriodicityComposite;
import cguide.parser.Composite.WaitingTimeComposite;
import com.google.gson.Gson;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA. User: andre Date: 12/1/12 Time: 6:27 PM
 */
public class Utils {

    public static String generateRandomKey(int length) {
        SecureRandom random = new SecureRandom();
        String key = new BigInteger(130, random).toString(32);

        return key.substring(0, (key.length() > length) ? length : key.length() - 1);
    }

    public static String parseId(String iri) {
        Pattern pattern = Pattern.compile("#(.*?)#");
        Matcher matcher = pattern.matcher(iri);
        if (matcher.find()) {
            return (matcher.group(1));
        }
        return null;
    }

    public static Boolean alternateTaskTest(ArrayList<Condition> conditions, ArrayList<TriggerCondition> task) {

        Boolean flag = false;
        for (TriggerCondition tc : task) {
            for (ConditionSet cs : tc.getTriggerConditionSet()) {
                flag = testConditionSet(cs, conditions);
                if (flag) {
                    System.out.println("Selected according to Condition Set " + cs.getId());
                    return true;
                }
            }
        }

        return flag;
    }

    public static Boolean processDecision(ArrayList<Condition> conditions, ArrayList<ConditionSet> task) {

        Boolean flag = false;
        for (ConditionSet cs : task) {
            flag = testConditionSet(cs, conditions);
            if (flag) {
                System.out.println("Processed according to Condition Set " + cs.toJson());
                return true;
            }

        }

        return flag;
    }

    private static Boolean testConditionSet(ConditionSet conditionSet, ArrayList<Condition> conditions) {
        for (Condition c : conditionSet.getCondition()) {
            if (!containsCondition(c, conditions)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public static Boolean containsCondition(Condition c, ArrayList<Condition> cs) {
        Gson gson = new Gson();

        for (Condition tgc : cs) {
            if (c.getIsNumeric() && tgc.getIsNumeric()) {
                if (c.conditionCompareNumeric(tgc)) {
                    System.out.println("FOUND");
                    return Boolean.TRUE;
                }

            }
            if (!c.getIsNumeric() && !tgc.getIsNumeric()) {
                if (c.conditionCompare(tgc)) {
                    System.out.println("FOUND");
                    return Boolean.TRUE;
                }
            }
        }
        System.out.println("NOT FOUND");
        return Boolean.FALSE;
    }

    public static DurationComposite checkDuration(Duration duration) {
        DurationComposite durationComposite = new DurationComposite();
        WaitingTimeComposite waitingTimeComposite = new WaitingTimeComposite();

        durationComposite.setDuration(
                duration.getMinDurationValue(),
                duration.getMaxDurationValue(),
                duration.getDurationValue()
        );

        durationComposite.setTemporalUnit(duration.getTemporalUnit());
        durationComposite.check();

        return durationComposite;
    }

    public static PeriodicityComposite checkPeriodicity(Periodicity periodicity) {

        PeriodicityComposite periodicityComposite = new PeriodicityComposite();

        if (periodicity.asDuration()) {
            DurationComposite durationComposite = checkDuration(periodicity.getDuration());

            periodicityComposite.setPeriodicity(periodicity.getPeriodicityValue());
            periodicityComposite.setTemporalUnit(periodicity.getTemporalUnit());

            if (periodicity.haveCyclePart()) {
                CyclePartDefinitionComposite cyclePartDefinitionComposite
                        = checkCyclePartDefinition(
                                periodicity.getCyclePartDefinition()
                        );

                periodicityComposite.setCyclePartDefinition(
                        cyclePartDefinitionComposite.getCyclePartDefinition()
                );
            }
        }
        if (periodicity.asRepetition()) {
            periodicityComposite.setPeriodicity(
                    periodicity.getPeriodicityValue(),
                    periodicity.getRepetitionValue()
            );

            if (periodicity.haveCyclePart()) {
                CyclePartDefinitionComposite cyclePartDefinitionComposite
                        = checkCyclePartDefinition(
                                periodicity.getCyclePartDefinition()
                        );

                periodicityComposite.setCyclePartDefinition(
                        cyclePartDefinitionComposite.getCyclePartDefinition()
                );

            }
        }

        periodicityComposite.check();

        return periodicityComposite;
    }

    public static CyclePartDefinitionComposite checkCyclePartDefinition(cguide.execution.entities.CyclePartDefinition cyclePartDefinition) {
        CyclePartDefinitionComposite cyclePartDefinitionComposite = new CyclePartDefinitionComposite();

        if (cyclePartDefinition.asDuration()) {
            DurationComposite durationComposite = checkDuration(
                    cyclePartDefinition.getDuration()
            );

            cyclePartDefinitionComposite.setDuration(
                    durationComposite.getDuration()
            );
        }
        if (cyclePartDefinition.asPeriodicity()) {
            CyclePartPeriodicityComposite cyclePartPeriodicityComposite
                    = checkCyclePartPeriodicity(
                            cyclePartDefinition.getCyclePartPeriodicity()
                    );

            cyclePartDefinitionComposite.setCyclePartPeriodicity(
                    cyclePartPeriodicityComposite.getCyclePartPeriodicity()
            );
        }

        cyclePartDefinitionComposite.check();

        return cyclePartDefinitionComposite;
    }

    public static CyclePartPeriodicityComposite checkCyclePartPeriodicity(cguide.execution.entities.CyclePartPeriodicity cyclePartPeriodicity) {
        CyclePartPeriodicityComposite cyclePartPeriodicityComposite = new CyclePartPeriodicityComposite();

        cyclePartPeriodicity.setTemporalUnit(cyclePartPeriodicity.getTemporalUnit());

        if (cyclePartPeriodicity.asDuration()) {
            DurationComposite durationComposite = checkDuration(
                    cyclePartPeriodicity.getDuration()
            );

            cyclePartPeriodicityComposite.setCyclePartPeriodicity(
                    cyclePartPeriodicity.getPeriodicityValue()
            );
            cyclePartPeriodicityComposite.setDuration(
                    durationComposite.getDuration()
            );
        }

        if (cyclePartPeriodicity.asRepetition()) {
            cyclePartPeriodicityComposite.setCyclePartPeriodicity(
                    cyclePartPeriodicity.getRepetitionValue(),
                    cyclePartPeriodicity.getPeriodicityValue()
            );
        }

        cyclePartPeriodicityComposite.check();

        return cyclePartPeriodicityComposite;
    }
}
