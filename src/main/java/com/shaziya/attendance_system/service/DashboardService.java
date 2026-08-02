package com.shaziya.attendance_system.service;
import com.shaziya.attendance_system.dto.FacultyDashboardResponse;
import com.shaziya.attendance_system.entity.Faculty;
import com.shaziya.attendance_system.dto.DashboardResponse;
import com.shaziya.attendance_system.dto.StudentDashboardResponse;
import com.shaziya.attendance_system.entity.Student;
import com.shaziya.attendance_system.repository.AttendanceRepository;
import com.shaziya.attendance_system.repository.FacultyRepository;
import com.shaziya.attendance_system.repository.LectureRepository;
import com.shaziya.attendance_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final LectureRepository lectureRepository;
    private final AttendanceRepository attendanceRepository;

    public DashboardService(
            StudentRepository studentRepository,
            FacultyRepository facultyRepository,
            LectureRepository lectureRepository,
            AttendanceRepository attendanceRepository
    ) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.lectureRepository = lectureRepository;
        this.attendanceRepository = attendanceRepository;
    }

    // ==========================
    // ADMIN DASHBOARD
    // ==========================
    public DashboardResponse getAdminDashboard() {

        return new DashboardResponse(
                studentRepository.count(),
                facultyRepository.count(),
                lectureRepository.count(),
                attendanceRepository.count()
        );
    }

    // ==========================
    // STUDENT DASHBOARD
    // ==========================
    public StudentDashboardResponse getStudentDashboard(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        long classesAttended =
                attendanceRepository.countByStudent(student);

        long totalClasses =
                lectureRepository.count();

        double percentage = 0;

        if (totalClasses > 0) {
            percentage =
                    ((double) classesAttended / totalClasses) * 100;
        }

        return new StudentDashboardResponse(
                student.getUser().getName(),
                student.getCourse(),
                student.getSemester(),
                classesAttended,
                totalClasses,
                percentage
        );
    }
    public FacultyDashboardResponse getFacultyDashboard(Long facultyId) {

        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() ->
                        new RuntimeException("Faculty not found"));

        long lecturesTaken =
                lectureRepository.countByFaculty(faculty);

        long studentsPresent =
                attendanceRepository.countByLectureFaculty(faculty);

        return new FacultyDashboardResponse(
                faculty.getUser().getName(),
                faculty.getDepartment(),
                faculty.getDesignation(),
                lecturesTaken,
                studentsPresent
        );
    }
}