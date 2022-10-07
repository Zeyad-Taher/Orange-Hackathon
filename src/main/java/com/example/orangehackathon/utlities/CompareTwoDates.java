package com.example.orangehackathon.utlities;

import com.example.orangehackathon.entity.Course;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CompareTwoDates {

    public boolean compareDateAndTime(Course course, List<Course> enrolled) throws ParseException {
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
            if (((courseStartDate.compareTo(enrolledStartDate) >= 0 && courseStartDate.compareTo(enrolledEndDate) <= 0)
                    || (courseEndDate.compareTo(enrolledEndDate) <= 0 && courseEndDate.compareTo(enrolledStartDate) >= 0))&&(!Objects.equals(enrolledCourse.getProgress(), "Time conflict") && !Objects.equals(enrolledCourse.getProgress(), "Attended"))) {
                int courseStartTime=timeToHour(course.getStartTime());
                int courseEndTime=timeToHour(course.getEndTime());
                int enrolledStartTime=timeToHour(enrolledCourse.getStartTime());
                int enrolledEndTime=timeToHour(enrolledCourse.getStartTime());
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

    public int timeToHour(String time){
        String[] arrOfTime = time.split(":");
        return Integer.parseInt(arrOfTime[0]);
    }
}
