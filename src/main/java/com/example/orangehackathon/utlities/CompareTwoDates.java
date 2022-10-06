package com.example.orangehackathon.utlities;

import com.example.orangehackathon.entity.Course;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class CompareTwoDates {

    public boolean compareDateAndTime(Course course, ArrayList<Course> enrolled) throws ParseException {
        SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
        Date courseStartDate = sdformat.parse(course.getStartDate());
        Date courseEndDate = sdformat.parse(course.getEndDate());
        for (Course enrolledCourse : enrolled) {
            Date enrolledStartDate = sdformat.parse(enrolledCourse.getStartDate());
            Date enrolledEndDate = sdformat.parse(enrolledCourse.getEndDate());
            sdformat.format(courseStartDate);
            sdformat.format(courseEndDate);
            sdformat.format(enrolledStartDate);
            sdformat.format(enrolledEndDate);
            if ((courseStartDate.compareTo(enrolledStartDate) >= 0 && courseStartDate.compareTo(enrolledEndDate) <= 0)
                    || (courseEndDate.compareTo(enrolledEndDate) <= 0 && courseEndDate.compareTo(enrolledStartDate) >= 0)) {
                int courseStartTime=timeToMins(course.getStartTime());
                int courseEndTime=timeToMins(course.getEndTime());
                int enrolledStartTime=timeToMins(enrolledCourse.getStartTime());
                int enrolledEndTime=timeToMins(enrolledCourse.getStartTime());
                if((courseStartTime>=enrolledStartTime && courseStartTime<=enrolledEndTime)
                        ||(courseEndTime<=enrolledEndTime && courseEndTime>=enrolledStartTime)){
                    return false;
                }
                else{
                    return true;
                }
            }
            else
                return true;
        }
        return true;
    }

    public int timeToMins(String time){
        String[] arrOfTime = time.split(":");
        return Integer.parseInt(arrOfTime[0])*60+Integer.parseInt(arrOfTime[1]);
    }
}
