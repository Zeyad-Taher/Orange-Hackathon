package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.CourseDTO;
import com.example.orangehackathon.dto.DashboardDTO;
import com.example.orangehackathon.entity.*;
import com.example.orangehackathon.repository.CourseRepository;
import com.example.orangehackathon.repository.StudentRepository;
import com.example.orangehackathon.utlities.CompareTwoDates;
import com.example.orangehackathon.utlities.EmailDetails;
import com.example.orangehackathon.utlities.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SkillService skillService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private CompareTwoDates compareTwoDates;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JobService jobService;

    public ResponseEntity<?> addCourse(CourseDTO courseDTO) {
        Course course = new Course(courseDTO);
        courseRepository.save(course);
        return new ResponseEntity<>(course,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> showAllCourses() {
        return new ResponseEntity<>(courseRepository.findAll(),HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> addSkillToCourse(Long courseId,Long skillId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if(course == null){
            return new ResponseEntity<>("Invalid course ID",HttpStatus.BAD_REQUEST);
        }
        Skill skill = skillService.findSkillById(skillId);
        if(skill == null){
            return new ResponseEntity<>("Invalid skill ID",HttpStatus.BAD_REQUEST);
        }
        List<Skill> skills= course.getSkills();
        skills.add(skill);
        course.setSkills(skills);
        courseRepository.save(course);
        return new ResponseEntity<>(course,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> delSkillToCourse(Long courseId, Long skillId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if(course == null){
            return new ResponseEntity<>("Invalid course ID",HttpStatus.BAD_REQUEST);
        }
        Skill skill = skillService.findSkillById(skillId);
        if(skill == null){
            return new ResponseEntity<>("Invalid skill ID",HttpStatus.BAD_REQUEST);
        }
        List<Skill> skills= course.getSkills();
        skills.remove(skill);
        course.setSkills(skills);
        courseRepository.save(course);
        return new ResponseEntity<>(course,HttpStatus.ACCEPTED);
    }

    public boolean checkConflict(Course course,List<Course> enrolled) throws ParseException {
        return compareTwoDates.compareDateAndTime(course, enrolled);
    }

    public boolean checkPrerequisites(Course course,List<Skill> studentSkills){
        List<Course> prerequisites= course.getPrerequisites();
        for(Course pre : prerequisites){
            List<Skill> preSkills= pre.getSkills();
            for(Skill skill:preSkills){
                if(!studentSkills.contains(skill)){
                    return false;
                }
            }
        }
        return true;
    }

    public ResponseEntity<?> enrollStudentToCourse(Long courseId, Long studentId) throws ParseException {
        Course course = courseRepository.findById(courseId).orElse(null);
        if(course == null){
            return new ResponseEntity<>("Invalid course ID",HttpStatus.BAD_REQUEST);
        }
        Student student = studentService.findStudentById(studentId);
        if(student == null){
            return new ResponseEntity<>("Invalid student ID",HttpStatus.BAD_REQUEST);
        }
        List<Course> courses= student.getCourses();
        if(!checkConflict(course,courses)){
            course.setProgress("Time conflict");
            List<Student> students = course.getStudents();
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
        else if (checkPrerequisites(course, student.getGainedSkills())){
            List<Student> students = course.getStudents();
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
            List<Student> students = course.getStudents();
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

    public ResponseEntity<?> addPrerequisiteToCourse(Long courseId, Long preId) {
        Course course=courseRepository.findById(courseId).orElse(null);
        if(course == null){
            return new ResponseEntity<>("Invalid course ID",HttpStatus.BAD_REQUEST);
        }
        Course prerequisite=courseRepository.findById(preId).orElse(null);
        if(prerequisite == null){
            return new ResponseEntity<>("Invalid course prerequisite ID",HttpStatus.BAD_REQUEST);
        }
        List<Course> prerequisites=course.getPrerequisites();
        prerequisites.add(prerequisite);
        course.setPrerequisites(prerequisites);
        courseRepository.save(course);
        return new ResponseEntity<>(course,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> delPrerequisiteToCourse(Long courseId, Long preId) {
        Course course=courseRepository.findById(courseId).orElse(null);
        if(course == null){
            return new ResponseEntity<>("Invalid course ID",HttpStatus.BAD_REQUEST);
        }
        Course prerequisite=courseRepository.findById(preId).orElse(null);
        if(prerequisite == null){
            return new ResponseEntity<>("Invalid course prerequisite ID",HttpStatus.BAD_REQUEST);
        }
        List<Course> prerequisites=course.getPrerequisites();
        prerequisites.remove(prerequisite);
        course.setPrerequisites(prerequisites);
        courseRepository.save(course);
        return new ResponseEntity<>(course,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> addSupplierToCourse(Long courseId, Long supId) {
        Course course=courseRepository.findById(courseId).orElse(null);
        if(course == null){
            return new ResponseEntity<>("Invalid course ID",HttpStatus.BAD_REQUEST);
        }
        Supplier supplier=supplierService.findSupplierById(supId);
        if(supplier == null){
            return new ResponseEntity<>("Invalid supplier ID",HttpStatus.BAD_REQUEST);
        }
        course.setSupplier(supplier);
        courseRepository.save(course);
        return new ResponseEntity<>(course,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> delSupplierToCourse(Long courseId) {
        Course course=courseRepository.findById(courseId).orElse(null);
        if(course == null){
            return new ResponseEntity<>("Invalid course ID",HttpStatus.BAD_REQUEST);
        }
        course.setSupplier(null);
        courseRepository.save(course);
        return new ResponseEntity<>(course,HttpStatus.ACCEPTED);
    }

    public void setNumberOfCourses(DashboardDTO dashboardDTO) {
        List<Course> courses= (List<Course>) courseRepository.findAll();
        dashboardDTO.setNumberOfCurrentCourses(courses.size());
    }

    public ResponseEntity<?> setStudentProgressInCourse(Long courseId,Long studentId,String progress){
        Course course=courseRepository.findById(courseId).orElse(null);
        if(course==null){
            return new ResponseEntity<>("Invalid course ID",HttpStatus.BAD_REQUEST);
        }
        Student student=studentService.findStudentById(studentId);
        if(student==null){
            return new ResponseEntity<>("Invalid student ID",HttpStatus.BAD_REQUEST);
        }
        List<Course> courses=student.getCourses();
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

    public ResponseEntity<?> finishCourseByStudent(Long courseId,Long studentId){
        Course course=courseRepository.findById(courseId).orElse(null);
        if(course==null){
            return new ResponseEntity<>("Invalid course ID",HttpStatus.BAD_REQUEST);
        }
        Student student=studentService.findStudentById(studentId);
        if(student==null){
            return new ResponseEntity<>("Invalid student ID",HttpStatus.BAD_REQUEST);
        }
        List<Course> courses=student.getCourses();
        int index=courses.indexOf(course);
        if(index==-1){
            return new ResponseEntity<>("This course is not enrolled by student",HttpStatus.BAD_REQUEST);
        }
        else{
            courses.remove(index);
            course.setProgress("Attended");
            courses.add(course);
            List<Skill> courseSkills=course.getSkills();
            List<Skill> studentSkills=student.getGainedSkills();
            for(Skill skill:courseSkills){
                if(!studentSkills.contains(skill)) {
                    studentSkills.add(skill);
                }
            }
            student.setGainedSkills(studentSkills);
            studentService.saveStudent(student);
            EmailDetails email = new EmailDetails();
            email.setRecipient(student.getEmail());
            email.setMsgBody("Hello "+student.getFirstname()+"Congratulation,\nYour progress in "+course.getName()+" course has come " +
                    "to an end.\nBest of Luck.");
            email.setSubject("ODC - Course registration");
            emailService.sendSimpleMail(email);
            return new ResponseEntity<>("Student Progress updated successfully. Email will be sent shortly.",HttpStatus.ACCEPTED);
        }
    }

    public ResponseEntity<?> recommendStudentsToCourse(Long courseId){
        List<Student> students=studentRepository.findAll();
        List<Student> recommended = new ArrayList<>();
        Course course=courseRepository.findById(courseId).orElse(null);
        if(course==null){
            return new ResponseEntity<>("Please enter a valid course ID",HttpStatus.BAD_REQUEST);
        }
        List<Skill> courseSkills= course.getSkills();
        for (Student student:students){
            List<Skill> studentSkills=student.getGainedSkills();
            boolean meetCriteria=checkPrerequisites(course,studentSkills);
            if(meetCriteria){
                recommended.add(student);
            }
        }
        if(recommended.isEmpty()){
            return new ResponseEntity<>("No student meet the course prerequisites.",HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>(recommended,HttpStatus.ACCEPTED);
        }
    }

    public ResponseEntity<?> recommendStudentsToJob(Long jobId){
        List<Student> students=studentRepository.findAll();
        List<Student> recommended = new ArrayList<>();
        Job job=jobService.findJobById(jobId);
        if(job==null){
            return new ResponseEntity<>("Please enter a valid job ID",HttpStatus.BAD_REQUEST);
        }
        List<Skill> jobSkills=job.getRequiredSkills();
        for (Student student:students){
            List<Skill> studentSkills=student.getGainedSkills();
            boolean meetCriteria=true;
            for (Skill skill:jobSkills){
                if(!studentSkills.contains(skill)){
                    meetCriteria=false;
                    break;
                }
            }
            if(meetCriteria){
                recommended.add(student);
            }
        }
        if(recommended.isEmpty()){
            return new ResponseEntity<>("No student meet the job required skills.",HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>(recommended,HttpStatus.ACCEPTED);
        }
    }
}
