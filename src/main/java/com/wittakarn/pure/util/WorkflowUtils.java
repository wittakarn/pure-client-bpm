/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wittakarn.pure.util;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Wittakarn
 */
public class WorkflowUtils implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @param calendar
     * @return String ("25530101010101")
     * @example String dateUI = DateUtils.convertCalendarToStringDatetime(
     * DateUtils.getCurrentDateCalendarEng() );
     */
    public static String convertCalendarToStringDateTime(Calendar calendar) {
        String yyyy = "";
        if (calendar.get(Calendar.YEAR) < 2400) {
            yyyy = String.valueOf(calendar.get(Calendar.YEAR) + 543);
        } else {
            yyyy = String.valueOf(calendar.get(Calendar.YEAR));
        }
        String mm = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String dd = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String hh = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String nn = String.valueOf(calendar.get(Calendar.MINUTE));
        String ss = String.valueOf(calendar.get(Calendar.SECOND));
        if (mm.length() < 2) {
            mm = "0" + mm;
        }
        if (dd.length() < 2) {
            dd = "0" + dd;
        }
        if (hh.length() < 2) {
            hh = "0" + hh;
        }
        if (nn.length() < 2) {
            nn = "0" + nn;
        }
        if (ss.length() < 2) {
            ss = "0" + ss;
        }

        return yyyy + mm + dd + hh + nn + ss;
    }

}
