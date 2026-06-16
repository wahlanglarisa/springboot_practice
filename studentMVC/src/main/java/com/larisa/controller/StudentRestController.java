package com.larisa.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.larisa.dto.ClassTime;
import com.larisa.dto.Course;
import com.larisa.dto.District;
import com.larisa.dto.GetStudentAddress;
import com.larisa.dto.GetStudentQualDetails;
import com.larisa.dto.Professor;
import com.larisa.dto.Qualification;
import com.larisa.dto.State;
import com.larisa.dto.Student;
import com.larisa.dto.StudentAddress;
import com.larisa.dto.User;
import com.larisa.service.ClassTimeService;
import com.larisa.service.CourseService;
import com.larisa.service.DistrictService;
import com.larisa.service.ProfessorService;
import com.larisa.service.QualificationService;
import com.larisa.service.StateService;
import com.larisa.service.StudentAddressService;
import com.larisa.service.StudentQualificationService;
import com.larisa.service.StudentService;
import com.larisa.service.UserService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class StudentRestController {
	public static int OTP;
	@Autowired
	private StateService stateService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private MailSender mailSender;
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentAddressService studentAddressService;
	@Autowired
	private StudentQualificationService studentQualificationService;
	@Autowired
	private ProfessorService professorService;
	private final static String ACCOUNT_SID = "ACc19d89f4a950cc4c81056d7e82be948f";
	private final static String AUTH_ID = "7bb5a22a54dda8aaf1ef29ad91c43a13";
	static int otpphone;
	static int otpemail;

	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}
	@Autowired
	private QualificationService qualificationService;
	@Autowired
	private ClassTimeService classTimeService;

	@RequestMapping(value = "/getState_codes", method = RequestMethod.GET)

	public @ResponseBody List<State> getStates(@RequestParam("countryCode") String countryCode) {
		return stateService.getStatesByCountry_code(countryCode);
	}

	@RequestMapping(value = "/getDistrict_codes", method = RequestMethod.GET)

	public @ResponseBody List<District> getDistricts(@RequestParam("stateCode") String stateCode) {
		return districtService.getDistrictsByState(stateCode);
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)

	public @ResponseBody User getUser(@RequestParam("email") String email) {
		System.out.println("Get User Called");
		return userService.getUserByEmail(email);
	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)

	public boolean sendMail(@RequestParam("email") String email) {
		Random random = new Random();
		System.out.println("send mail Called");
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		OTP = (10000 + random.nextInt(90000));
		System.out.println(OTP + "is otp for " + email);
		try {
			mailMessage.setTo(email);
			mailMessage.setSubject("OTP Verification");
			mailMessage.setText("Your otp is " + OTP);
			mailSender.send(mailMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		return true;

	}

	@RequestMapping(value = "/getStudent", method = RequestMethod.GET)

	public @ResponseBody Student getStudent(@RequestParam("phone_no") Long phone_no) {
		System.out.println("Get User Called");
		return studentService.findStudentByPhoneNo(phone_no);
	}

	@RequestMapping(value = "/getProfessorByPhoneNo", method = RequestMethod.GET)

	public @ResponseBody Professor getProfessorByPhoneNo(@RequestParam("phone_no") Long phone_no) {
		System.out.println("Get User Called");
		return professorService.getByPhoneNumber(phone_no);
	}

	@RequestMapping(value = "/validatePassword", method = RequestMethod.GET)

	public @ResponseBody boolean validatePassword(@RequestParam("email") String email,
			@RequestParam("oldPassword") String oldPassword) {
		System.out.println("Get User Called");
		return userService.validatePassword(email, oldPassword);
	}

	@RequestMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
		// Retrieve image data from database or file system
		byte[] image = studentService.getStudent(id).getProfile_picture();
		System.out.println(image);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<>(image, headers, HttpStatus.OK);
	}

	@RequestMapping("/getCourseByStudent/")
	public Page<Course> getCourseByStudent(@RequestParam("id") long id, @RequestParam("page") int page) {
		Student student = studentService.getStudent(id);
		Pageable pageable = PageRequest.of(page - 1, 3);
		return courseService.getCoursesByStudent(student, pageable);
	}

	@RequestMapping(value = "/verifyOTP", method = RequestMethod.GET)
	public boolean verifyOTP(@RequestParam("otp") int OTP) {
		if (StudentRestController.OTP == OTP) {
			System.out.println(OTP);
			System.out.println(OTP);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/verifyPhoneOTP", method = RequestMethod.GET)
	public boolean verifyPhoneOTP(@RequestParam("otp") int OTP) {
		if (StudentRestController.otpphone == OTP) {
			System.out.println(OTP);
			System.out.println(OTP);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/sendSMSOTP")
	public boolean sendSMSOTP(@RequestParam("phone") long phone) {
		try {
			Random random = new Random();
			System.out.println(phone);
			otpphone = (10000 + random.nextInt(90000));
			System.out.println("OTP for " + phone + "is " + otpphone);
			Message.creator(new PhoneNumber("+91" + Long.toString(phone)), new PhoneNumber("+15856325133"),
					"I'm just a coder practicing OTP verification.Don't interact with this message"
							+ ".The OTP for verifying the number is " + otpphone)
					.create();

			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
	}

	@RequestMapping(value = "/getStudentAddress", method = RequestMethod.GET)
	public GetStudentAddress getStudentAddress(@RequestParam("id") long id) {
		System.out.println(id);

		return studentAddressService.getAddressByStID(id);
	}

	@RequestMapping(value = "/getStudentQualifications", method = RequestMethod.GET)
	public List<Qualification> getQualification() {

		return qualificationService.getQualifications();
	}

	@RequestMapping(value = "/getStudentQualificationsByID", method = RequestMethod.GET)
	public List<GetStudentQualDetails> getQualificationById(@RequestParam("id") Long id) {

		return studentQualificationService.getStudentQualifications(id);
	}

	@RequestMapping(value = "/getAvailableClassTime", method = RequestMethod.GET)
	public List<ClassTime> getAvailableClassTime(@RequestParam("day") String day) {

		return classTimeService.getAvailableClassTimes(day);
	}
}
