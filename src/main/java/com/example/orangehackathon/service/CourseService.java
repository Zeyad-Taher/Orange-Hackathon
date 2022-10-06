package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.CourseDTO;
import com.example.orangehackathon.dto.DashboardDTO;
import com.example.orangehackathon.entity.Course;
import com.example.orangehackathon.entity.Skill;
import com.example.orangehackathon.entity.Student;
import com.example.orangehackathon.entity.Supplier;
import com.example.orangehackathon.repository.CourseRepository;
import com.example.orangehackathon.utlities.CompareTwoDates;
import com.example.orangehackathon.utlities.EmailDetails;
import com.example.orangehackathon.utlities.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SkillService skillService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private CompareTwoDates compareTwoDates;
    @Autowired
    private EmailService emailService;

    public void addCourse(CourseDTO courseDTO) {
        Course course = new Course(courseDTO);
        courseRepository.save(course);
    }

    public ArrayList<Course> showAllCourses() {
        return (ArrayList<Course>) courseRepository.findAll();
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public void addSkillToCourse(Long courseId, Long skillId) {
        Course course = courseRepository.findById(courseId).get();
        Skill skill = skillService.findSkillById(skillId);
        ArrayList<Skill> skills= (ArrayList<Skill>) course.getSkills();
        skills.add(skill);
        course.setSkills(skills);
        courseRepository.save(course);
    }

    public void delSkillToCourse(Long courseId, Long skillId) {
        Course course = courseRepository.findById(courseId).get();
        Skill skill = skillService.findSkillById(skillId);
        ArrayList<Skill> skills= (ArrayList<Skill>) course.getSkills();
        skills.remove(skill);
        course.setSkills(skills);
        courseRepository.save(course);
    }

    public boolean checkConflict(Course course,ArrayList<Course> enrolled) throws ParseException {
        return compareTwoDates.compareDateAndTime(course, enrolled);
    }

    public boolean checkPrerequisites(Course course,ArrayList<Skill> studentSkills){
        ArrayList<Course> prerequisites= (ArrayList<Course>) course.getPrerequisites();
        for(Course pre : prerequisites){
            ArrayList<Skill> preSkills= (ArrayList<Skill>) pre.getSkills();
            for(Skill skill:preSkills){
                if(!studentSkills.contains(skill)){
                    return false;
                }
            }
        }
        return true;
    }

    public ResponseEntity<?> enrollStudentToCourse(Long courseId, Long studentId) throws ParseException {
        Course course = courseRepository.findById(courseId).get();
        Student student = studentService.findStudentById(studentId);
        ArrayList<Course> courses= (ArrayList<Course>) student.getCourses();
        if(!checkConflict(course,courses)){
            course.setProgress("Time conflict");
            ArrayList<Student> students = (ArrayList<Student>) course.getStudents();
            courses.add(course);
            students.add(student);
            course.setStudents(students);
            student.setCourses(courses);
            courseRepository.save(course);
            studentService.saveStudent(student);
            EmailDetails email = new EmailDetails();
            email.setRecipient(student.getEmail());
            email.setMsgBody("Hello "+student.getFirstname()+",\nSorry, your request to enroll "+course.getName()+" has been " +
                    "rejected due to time conflict with another course.\nBest of Luck.");
            email.setSubject("ODC - Course registration");
            emailService.sendSimpleMail(email);
            return new ResponseEntity<>("Can't enroll in course to due to time conflict.", HttpStatus.BAD_REQUEST);
        }
        else if (checkPrerequisites(course, (ArrayList<Skill>) student.getGainedSkills())){
            ArrayList<Student> students = (ArrayList<Student>) course.getStudents();
            course.setProgress("Not invited");
            courses.add(course);
            students.add(student);
            course.setStudents(students);
            student.setCourses(courses);
            courseRepository.save(course);
            studentService.saveStudent(student);
            EmailDetails email = new EmailDetails();
            email.setRecipient(student.getEmail());
            email.setMsgBody("Hello "+student.getFirstname()+",\nCongratulation, your request to enroll "+course.getName()+" has been " +
                    "accepted and we will send the interview details soon.\nBest of Luck.");
            email.setSubject("ODC - Course registration");
            emailService.sendSimpleMail(email);
            return new ResponseEntity<>("You have enrolled "+course.getName()+" successfully. You will receive email shortly.",HttpStatus.ACCEPTED);
        }
        else{
            course.setProgress("Criteria not met");
            ArrayList<Student> students = (ArrayList<Student>) course.getStudents();
            courses.add(course);
            students.add(student);
            course.setStudents(students);
            student.setCourses(courses);
            courseRepository.save(course);
            studentService.saveStudent(student);
            EmailDetails email = new EmailDetails();
            email.setRecipient(student.getEmail());
            email.setMsgBody("Hello "+student.getFirstname()+",\nSorry, your request to enroll "+course.getName()+" has been " +
                    "rejected because you don't have the minimum prerequisites.\nBest of Luck.");
            email.setSubject("ODC - Course registration");
            emailService.sendSimpleMail(email);
            return new ResponseEntity<>("You have enrolled "+course.getName()+" successfully. You will receive email shortly.",HttpStatus.ACCEPTED);
        }
    }

    public void unrollStudentToCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).get();
        Student student = studentService.findStudentById(studentId);
        ArrayList<Student> students= (ArrayList<Student>) course.getStudents();
        ArrayList<Course> courses= (ArrayList<Course>) student.getCourses();
        students.remove(student);
        courses.remove(course);
        course.setStudents(students);
        student.setCourses(courses);
        courseRepository.save(course);
        studentService.saveStudent(student);
    }

    public void addPrerequisiteToCourse(Long courseId, Long preId) {
        Course course=courseRepository.findById(courseId).get();
        Course prerequisite=courseRepository.findById(preId).get();
        ArrayList<Course> prerequisites= (ArrayList<Course>) course.getPrerequisites();
        prerequisites.add(prerequisite);
        course.setPrerequisites(prerequisites);
        courseRepository.save(course);
    }

    public void delPrerequisiteToCourse(Long courseId, Long preId) {
        Course course=courseRepository.findById(courseId).get();
        Course prerequisite=courseRepository.findById(preId).get();
        ArrayList<Course> prerequisites= (ArrayList<Course>) course.getPrerequisites();
        prerequisites.remove(prerequisite);
        course.setPrerequisites(prerequisites);
        courseRepository.save(course);
    }

    public void addSupplierToCourse(Long courseId, Long supId) {
        Course course=courseRepository.findById(courseId).get();
        Supplier supplier=supplierService.findSupplierById(supId);
        course.setSupplier(supplier);
        courseRepository.save(course);
    }

    public void delSupplierToCourse(Long courseId) {
        Course course=courseRepository.findById(courseId).get();
        course.setSupplier(new Supplier());
        courseRepository.save(course);
    }

    public void setNumberOfCourses(DashboardDTO dashboardDTO) {
        int courses=showAllCourses().size();
        dashboardDTO.setNumberOfCurrentCourses(courses);
    }

    public ResponseEntity<?> setStudentProgressInCourse(Long courseId,Long studentId,String progress){
        Course course=courseRepository.findById(courseId).orElse(null);
        if(course==null){
            return new ResponseEntity<>("Invalid course ID",HttpStatus.BAD_REQUEST);
        }
        Student student=studentService.findStudentById(studentId);
        ArrayList<Course> courses= (ArrayList<Course>) student.getCourses();
        int index=courses.indexOf(course);
        if(index==-1){
            return new ResponseEntity<>("This course is not enrolled by student",HttpStatus.BAD_REQUEST);
        }
        else{
            courses.remove(index);
            course.setProgress(progress);
            courses.add(course);
            studentService.saveStudent(student);
            EmailDetails email = new EmailDetails();
            email.setRecipient(student.getEmail());
            email.setMsgBody("Hello "+student.getFirstname()+",\nYour progress in "+course.getName()+" course has been " +
                    "updated to "+progress+".\nBest of Luck.");
            email.setSubject("ODC - Course registration");
            emailService.sendSimpleMail(email);
            return new ResponseEntity<>("Student Progress updated successfully. Email will be sent shortly.",HttpStatus.ACCEPTED);
        }
    }
}
