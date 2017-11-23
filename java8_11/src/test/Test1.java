package test;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @author itguang
 * @create 2017-11-23 14:54
 **/
public class Test1 {


    @Test
    public void test() {

        LocalDate date = LocalDate.of(2017, 11, 23);


        System.out.println("年份:" + date.getYear());

        Month month = date.getMonth();
        System.out.println("月份(单词):" + month);
        int monthValue = date.getMonthValue();
        System.out.println("月份(数字)" + monthValue);

        System.out.println("多少号:" + date.getDayOfMonth());


        System.out.println("返回由此日期表示的月份的长度:" + date.lengthOfMonth());
        System.out.println("返回由此日期表示的年份的长度:" + date.lengthOfYear());

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("星期几(单词):" + dayOfWeek);
        System.out.println("星期几(数字):" + dayOfWeek.getValue());


        System.out.println("一年中的第几天:" + date.getDayOfYear());


        System.out.println("是否是闰年:" + date.isLeapYear());

        LocalDate a = LocalDate.of(2012, 6, 30);
        System.out.println("检查此日期是否在指定日期之后:" + date.isAfter(a));
        System.out.println("检查此日期是否在指定日期之前:" + date.isBefore(a));
        System.out.println("检查此日期是否等于指定的日期。" + date.isEqual(a));


        System.out.println("将日期输出:" + date.toString());


    }


    @Test
    public void test2() {
        LocalDate today = LocalDate.now();

        int year = today.get(ChronoField.YEAR);
        int month2 = today.get(ChronoField.MONTH_OF_YEAR);
        int day = today.get(ChronoField.DAY_OF_MONTH);
        int secondDay = today.get(ChronoField.SECOND_OF_DAY);
        System.out.println(year + ":" + month2 + ":" + day);
        //System.out.println("第二天:"+secondDay);

    }


    @Test
    public void test3() {
        LocalTime now = LocalTime.now();

        LocalTime time = LocalTime.of(15, 41, 30);
        LocalTime time2 = LocalTime.of(15, 41);

        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println("小时:" + hour + "分钟:" + minute + "秒:" + second);

        System.out.println(now.toString());

    }

    @Test
    public void test4() {
        String date = "2017-11-23";
        String time = "15:51:30";
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);
        System.out.println(localDate);
        System.out.println(localTime);

    }

    /**
     * LocalDateTime ,LocalDate ,LocalTime 互相转换
     */
    @Test
    public void test5() {
        String date = "2017-11-23";
        String time = "15:51:30";
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);

        LocalDateTime localDateTime = LocalDateTime.of(2017, 11, 23, 16, 01, 30, 888);
        LocalDateTime localDateTime1 = LocalDateTime.of(localDate, localTime);

        //localDateTime-->LocalDate,LocalTime
        LocalDate localDate1 = localDateTime.toLocalDate();
        LocalTime localTime1 = localDateTime.toLocalTime();

        // LocalDate,LocalTime --> LocalDateTime
        LocalDateTime localDateTime2 = localDate.atTime(16, 02, 30);
        LocalDateTime localDateTime3 = localTime.atDate(localDate);

    }

    /**
     * Instant
     */
    @Test
    public void test6() {
        Instant instant = Instant.ofEpochSecond(3);
        //2秒加上10亿纳秒(1秒)
        Instant instant1 = Instant.ofEpochSecond(2, 1_000_000_000);

        //4秒减去10亿纳秒(1秒)
        Instant instant2 = Instant.ofEpochSecond(4, -1_000_000_000);

        System.out.println(instant);
        System.out.println(instant1);
        System.out.println(instant2);


        Instant now = Instant.now();
        System.out.println("当前日期时间戳:" + now.getNano());


    }

    /**
     *
     */
    @Test
    public void test7() {
        LocalDate date = LocalDate.of(2014, 12, 18);
        LocalDate date2 = date.withYear(2017);
        System.out.println(date2);//2017-12-18
        LocalDate date3 = date2.withDayOfMonth(25);
        System.out.println(date3);//2017-12-25
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println(date4);//2017-09-25


    }

    /**
     *
     */
    @Test
    public void test8() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.plusWeeks(1);
        System.out.println(date2);//2014-03-25
        LocalDate date3 = date2.plusYears(3);
        System.out.println(date3);//2017-03-25
        LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);
        System.out.println(date4);//2017-09-25


    }

    /**
     *
     */
    @Test
    public void test9() {
        LocalDate date = LocalDate.of(2017, 11, 23);
        //调整日期到本周日
        LocalDate localDate = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(localDate);//2017-11-26
        //调整日期到本月最后一天
        LocalDate localDate1 = localDate.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(localDate1);//2017-11-30


    }  /**
     *
     */
    @Test
    public void test11() {
        LocalDate date = LocalDate.of(2017, 11, 23);
        System.out.println(date);

        String format = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(format);//20171123

        String format1 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(format1);//2017-11-23




    }
    /**
     *
     */
    @Test
    public void test12() {
        LocalDate localDate = LocalDate.parse("20171123", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);//2017-11-23

        LocalDate localDate1 = LocalDate.parse("2017-11-23", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(localDate1);//2017-11-23





    }

    /**
     *
     */
    @Test
    public void test13() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");// dd天 ,MM月 ,yyyy年
        LocalDate date = LocalDate.of(2017, 11, 23);
        String format = date.format(pattern);
        System.out.println(format);//23/11/2017

    }

    /**
     *
     */
    @Test
    public void test10() {
        LocalDate date = LocalDate.of(2017, 11, 23);
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    //读取当前天数
                    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    //正常情况增加1天
                    int dayToAdd = 1;
                    //如果当天是周五,增加三天
                    if (dow == DayOfWeek.FRIDAY) {
                        dayToAdd = 3;
                    }
                    //如果当天是周六增加两天
                    if (dow == DayOfWeek.SATURDAY) {
                        dayToAdd = 2;
                    }
                    //增加恰当的天数后,返回修改的日期
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                });
        date = date.with(nextWorkingDay);
    }


}
