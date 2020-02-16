package com.summerchill.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;

/**
 * get date give it to shell
 * 在shell中的使用方式:
 * CURRENT_MONTH_BEGIN=$(java -jar ${AZKABAN_AGENT_HOME}/shelldate-1.0-SNAPSHOT-jar-with-dependencies.jar "CURRENT_MONTH_BEGIN" ${today})
 */
public class DateUtil {
    public static void main(String[] args) {
        if (args.length == 2) {
            LocalDate currentDate = LocalDate.parse(args[1]);
            switch (args[0]) {
                //当月初
                case "CURRENT_MONTH_BEGIN":
                    System.out.println(getCurrentMonthBeginEnd(currentDate, "BEGIN"));
                    break;
                //当月末
                case "CURRENT_MONTH_END":
                    System.out.println(getCurrentMonthBeginEnd(currentDate, "END"));
                    break;
                //下月初
                case "NEXT_MONTH_BEGIN":
                    LocalDate current_month_begin_next = currentDate.plusMonths(1);
                    System.out.println(getCurrentMonthBeginEnd(current_month_begin_next, "BEGIN"));
                    break;
                //下月末
                case "NEXT_MONTH_END":
                    LocalDate current_month_end_next = currentDate.plusMonths(1);
                    System.out.println(getCurrentMonthBeginEnd(current_month_end_next, "END"));
                    break;
                //上月初
                case "LAST_MONTH_BEGIN":
                    LocalDate current_month_begin_last = currentDate.minusMonths(1);
                    System.out.println(getCurrentMonthBeginEnd(current_month_begin_last, "BEGIN"));
                    break;
                //上月末
                case "LAST_MONTH_END":
                    LocalDate current_month_end_last = currentDate.minusMonths(1);
                    System.out.println(getCurrentMonthBeginEnd(current_month_end_last, "END"));
                    break;


                //本季度初
                case "CURRENT_QUARTER_BEGIN":
                    System.out.println(getCurrentQuarterBeginEnd(currentDate, "BEGIN"));
                    break;
                //本季度末
                case "CURRENT_QUARTER_END":
                    System.out.println(getCurrentQuarterBeginEnd(currentDate, "END"));
                    break;
                //下季度初
                case "NEXT_QUARTER_BEGIN":
                    LocalDate next_quarter_date_begin = currentDate.plus(1, IsoFields.QUARTER_YEARS);
                    System.out.println(getCurrentQuarterBeginEnd(next_quarter_date_begin, "BEGIN"));
                    break;
                //下季度末
                case "NEXT_QUARTER_END":
                    LocalDate next_quarter_date_end = currentDate.plus(1, IsoFields.QUARTER_YEARS);
                    System.out.println(getCurrentQuarterBeginEnd(next_quarter_date_end, "END"));
                    break;
                //上季度初
                case "LAST_QUARTER_BEGIN":
                    LocalDate last_quarter_date_begin = currentDate.minus(1, IsoFields.QUARTER_YEARS);
                    System.out.println(getCurrentQuarterBeginEnd(last_quarter_date_begin, "BEGIN"));
                    break;
                //上季度末
                case "LAST_QUARTER_END":
                    // get previous quarter
                    LocalDate last_quarter_date_end = currentDate.minus(1, IsoFields.QUARTER_YEARS);
                    System.out.println(getCurrentQuarterBeginEnd(last_quarter_date_end, "END"));
                    break;


                //本周初
                case "CURRENT_WEEK_BEGIN":
                    System.out.println(getCurrentWeekBeginEnd(currentDate, "BEGIN"));
                    break;
                //本周末
                case "CURRENT_WEEK_END":
                    System.out.println(getCurrentWeekBeginEnd(currentDate, "END"));
                    break;
                //下周初
                case "NEXT_WEEK_BEGIN":
                    LocalDate next_week_date_begin = currentDate.plusWeeks(1);
                    System.out.println(getCurrentWeekBeginEnd(next_week_date_begin, "BEGIN"));
                    break;
                //下周末
                case "NEXT_WEEK_END":
                    LocalDate next_week_date_end = currentDate.plusWeeks(1);
                    System.out.println(getCurrentWeekBeginEnd(next_week_date_end, "END"));
                    break;
                //上周初
                case "LAST_WEEK_BEGIN":
                    LocalDate last_week_date_begin = currentDate.minusWeeks(1);
                    System.out.println(getCurrentWeekBeginEnd(last_week_date_begin, "BEGIN"));
                    break;
                //上周末
                case "LAST_WEEK_END":
                    LocalDate last_week_date_end = currentDate.minusWeeks(1);
                    System.out.println(getCurrentWeekBeginEnd(last_week_date_end, "END"));
                    break;
            }

        } else {
            System.out.println("您输入的参数长度不为1,请修改..!");
            System.out.println(-1);
        }

    }

    //获得月末 月初 (本次,上次,下次)
    private static String getCurrentMonthBeginEnd(LocalDate currentDate, String type) {
        if ("BEGIN".equalsIgnoreCase(type)) {
            LocalDate current_month_begin = currentDate.withDayOfMonth(1);
            return current_month_begin.toString();
        } else {
            LocalDate current_month_end = currentDate.plusMonths(1).withDayOfMonth(1).minusDays(1);
            return current_month_end.toString();
        }
    }

    //获得季末 季初 (本次,上次,下次)
    private static String getCurrentQuarterBeginEnd(LocalDate currentDate, String type) {
        if ("BEGIN".equalsIgnoreCase(type)) {
            LocalDate current_quarter_begin = currentDate.with(currentDate.getMonth().firstMonthOfQuarter())
                    .with(TemporalAdjusters.firstDayOfMonth());
            return current_quarter_begin.toString();
        } else {
            LocalDate current_quarter_begin_current = currentDate.with(currentDate.getMonth().firstMonthOfQuarter())
                    .with(TemporalAdjusters.firstDayOfMonth());
            LocalDate current_quarter_end = current_quarter_begin_current.plusMonths(2)
                    .with(TemporalAdjusters.lastDayOfMonth());
            return current_quarter_end.toString();
        }
    }

    //获得周末 周初 (本次,上次,下次)
    private static String getCurrentWeekBeginEnd(LocalDate currentDate, String type) {
        if ("BEGIN".equalsIgnoreCase(type)) {
            LocalDate monday = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            return monday.toString();
        } else {
            LocalDate sunday = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            return sunday.toString();
        }
    }
}