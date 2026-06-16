package com.larisa.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.larisa.config.RSAUtil;
import com.larisa.config.TwilioConfig;
import com.larisa.dto.Country;
import com.larisa.dto.DeleteStudentQualifications;
import com.larisa.dto.Student;
import com.larisa.dto.UpdatePassword;
import com.larisa.dto.User;
import com.larisa.dto.UserProfessor;
import com.larisa.dto.UserStudent;
import com.larisa.service.CountryService;
import com.larisa.service.CourseService;
import com.larisa.service.CourseStudentService;
import com.larisa.service.DistrictService;
import com.larisa.service.ProfessorService;
import com.larisa.service.QualificationService;
import com.larisa.service.StateService;
import com.larisa.service.StudentQualificationService;
import com.larisa.service.StudentService;
import com.larisa.service.UserService;
import com.larisa.service.UserStudentService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterAndLoginController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserStudentService userStudentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseStudentService courseStudentService;
	@Autowired
	private StateService stateService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private QualificationService qualificationService;
	@Autowired
	private TwilioConfig twilioConfig;
	@Autowired
	private StudentQualificationService studentQualificationService;
	@Autowired
	private MailSender mailSender;
	@Autowired
	private ProfessorService professorService;

	@GetMapping(value = "/")
	public ModelAndView loginPage() {
		System.out.println("in list student function");
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.setViewName("login");
		modelAndView.addObject("publicKey", RSAUtil.getPublicKey());

		return modelAndView;
	}

	@PostMapping(value = "/setSession", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView setSession(@ModelAttribute("student") UserStudent student,
			HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) {
		HttpSession session = httpServletRequest.getSession();
		System.out.println(httpServletRequest.getHeader("Referer"));
		if (!file.isEmpty()) {
			try {
				student.setProfile_picture(file.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.setAttribute("userstudent", student);
		// TODO: process POST request
		System.out.println(session.getAttribute("userstudent"));
		if (httpServletRequest.getHeader("Referer").equals("http://localhost:8080/studentMVC/addstudent")
				|| httpServletRequest.getHeader("Referer")
						.equals("http://localhost:8080/studentMVC/addstudent?error=true")) {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			Random random = new Random();
			int otpemail = (10000 + random.nextInt(90000));
			session.setAttribute("otpemail", otpemail);
			int otpphone = (10000 + random.nextInt(90000));
			session.setAttribute("otpphone", otpphone);

			System.out.println(
					"OTP for " + student.getPhone_no() + "is " + otpphone + " from " + twilioConfig.getPhoneNumber());
			System.out.println(otpemail + "is otp for " + student.getEmail());
			try {
				mailMessage.setTo(student.getEmail());
				mailMessage.setSubject("OTP Verification");
				mailMessage.setText("Your otp is " + otpemail);
				mailSender.send(mailMessage);

				Message.creator(new PhoneNumber("+91" + Long.toString(student.getPhone_no())),
						new PhoneNumber(twilioConfig.getPhoneNumber()),
						"I'm just a coder practicing OTP verification.Don't interact with this message"
								+ ".The OTP for verifying the number is " + otpphone)
						.create();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return verifyOTPPage(httpServletRequest);
		} else if (httpServletRequest.getHeader("Referer")
				.equals("http://localhost:8080/studentMVC/student/updatestudent/" + student.getSt_id())
				|| httpServletRequest.getHeader("Referer")
						.equals("http://localhost:8080/studentMVC/student/updatestudent/" + student.getSt_id()
								+ "?updateSuccess=true")
				|| httpServletRequest.getHeader("Referer")
						.equals("http://localhost:8080/studentMVC/student/updatestudent/" + student.getSt_id()
								+ "?updateSuccess=false")) {
			return saveStudent(httpServletRequest);
		} else {
			return null;
		}
	}

	@GetMapping(value = "/verifyOTPPage")
	public ModelAndView verifyOTPPage(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		System.out.println(httpServletRequest.getHeader("Referer"));
		// TODO: process POST request
		System.out.println(session.getAttribute("userstudent"));
		return new ModelAndView("verifyOTP");

	}

	@PostMapping(value = "/verifyOTP")
	public ModelAndView verifyOTP(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {

		HttpSession session = httpServletRequest.getSession();
		ModelAndView mView = new ModelAndView();

		int emailOTP = Integer.parseInt(httpServletRequest.getParameter("emailOTP"));
		int phoneOTP = Integer.parseInt(httpServletRequest.getParameter("phoneOTP"));
		int sessionEmailOTP = (int) session.getAttribute("otpemail");
		int sessionPhoneOTP = (int) session.getAttribute("otpphone");
		if (emailOTP == sessionEmailOTP && phoneOTP == sessionPhoneOTP) {

			return saveStudent(httpServletRequest);

		} else {
			String redirectString = "redirect:/verifyOTPPage?";
			if (emailOTP != sessionEmailOTP) {
				redirectString += "emailOTPinvalid=true";
			}
			if (phoneOTP != sessionPhoneOTP) {
				if (redirectString.endsWith("?")) {
					redirectString += "phoneOTPinvalid=true";
				} else {
					redirectString += "&phoneOTPinvalid=true";
				}
			}
			mView.setViewName(redirectString);
			return mView;

		}
	}

	@GetMapping(value = "/addstudent")
	public ModelAndView addStudent(HttpServletRequest httpServletRequest) {
		System.out.println("in list student function");
		ModelAndView modelAndView = new ModelAndView();
		UserStudent student = new UserStudent();
		try {
			modelAndView.setViewName("studentForm");
			student.setSt_id(Long.parseLong("0"));
			student.setUser_id("");
			List<Country> countries = countryService.getCountries();
			System.out.println(countries);
			modelAndView.addObject("student", student);
			modelAndView.addObject("publicKey", RSAUtil.getPublicKey());

			modelAndView.addObject("countries", countries);
		} catch (Exception e) {
			// TODO: handle exception
			modelAndView.setViewName(httpServletRequest.getHeader("Referer"));
		}
		return modelAndView;
	}

	@GetMapping(value = "/addprofessor")
	public ModelAndView addProfessor(HttpServletRequest httpServletRequest) {
		System.out.println("in list student function");
		ModelAndView modelAndView = new ModelAndView();
		UserProfessor professor = new UserProfessor();
		try {
			modelAndView.setViewName("addProfessor");
			professor.setProfID(null);
			professor.setUserID(null);
			List<Country> countries = countryService.getCountries();
			System.out.println(countries);
			modelAndView.addObject("professor", professor);
			modelAndView.addObject("publicKey", RSAUtil.getPublicKey());

			modelAndView.addObject("countries", countries);
		} catch (Exception e) {
			// TODO: handle exception
			modelAndView.setViewName(httpServletRequest.getHeader("Referer"));
		}
		return modelAndView;
	}

	@PostMapping("/saveProfessor")
	public ModelAndView saveProfessor(@ModelAttribute UserProfessor professor) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			professorService.saveProfessor(professor);
			modelAndView.setViewName("redirect:/addprofessor?success=true");
		} catch (DuplicateKeyException e) {
			modelAndView.setViewName("redirect:/addprofessor?phoneNoAlreadyExists=true");

			// TODO: handle exception
		}
		catch (Exception e) {
			User user=userService.getUserByEmail(professor.getEmail());
			if(user!=null) {
				userService.deleteUser(user.getUserid());
			}
			modelAndView.setViewName("redirect:/addprofessor?someErrorOccured=true");
			e.printStackTrace();
			// TODO: handle exception
		}
		return modelAndView;

	}

	@PostMapping(value = "/saveStudent")
	public ModelAndView saveStudent(HttpServletRequest httpServletRequest) {
		ModelAndView modelAndView = new ModelAndView();
		UserStudent student = (UserStudent) httpServletRequest.getSession().getAttribute("userstudent");
		modelAndView.addObject("student", student);

		if (student.getSt_id() <= 0 || student == null || student.getUser_id() == "") {
			try {
				if (student.getFirst_name() == "" || student.getLast_name() == "" || student.getPhone_no() == null
						|| student.getEmail() == "") {
					modelAndView.setViewName("redirect:/addstudent?emptyFields=true");
				} else {

					studentService.addStudent(student);
					modelAndView.setViewName("redirect:/addstudent?success=true");
				}

			} catch (DuplicateKeyException e) {
				// TODO: handle exception
				User user = userService.getUserByEmail(student.getEmail());
				Student student2 = studentService.findStudentByEmail(student.getEmail());
				if (user != null) {
					userService.deleteUserByEmail(student.getEmail());
				}
				if (student2 != null) {
					studentService.deleteStudent(student2);
				}
				System.out.println(e.getCause());
				String redirectString = "redirect:/addstudent?";
				if (userStudentService.getUserStudentbyEmail(student.getEmail()) != null)
					redirectString += "emailAlreadyExists=true&";
				if (studentService.findStudentByPhoneNo(student.getPhone_no()) != null)
					redirectString += "phoneNoAlreadyExists=true";
				modelAndView.setViewName(redirectString);

			} catch (Exception e) {
				e.printStackTrace();
				User user = userService.getUserByEmail(student.getEmail());
				Student student2 = studentService.findStudentByEmail(student.getEmail());
				if (user != null) {
					studentService.deleteStudent(student2);

					userService.deleteUserByEmail(student.getEmail());
				}
				if (student2 != null) {
					studentService.deleteStudent(student2);
				}
				modelAndView.setViewName("redirect:/addstudent?error=true");

			}

		}

		else {
			try {
				// student.setProfile_picture(student.getProFile().getBytes());
				System.out.println(student.getStudentQualifications());
				System.out.println(student.getIds());
				if (student.getIds().size() > 0) {
					studentQualificationService.deleteStudentQualificationsByID(student.getIds());
				}

				System.out.println("student profile picture controller " + student.getProfile_picture());
				studentService.updateStudent(student);
				modelAndView.addObject("student", userStudentService.getUserStudentbyEmail(student.getEmail()));

				modelAndView
						.setViewName("redirect:/student/updatestudent/" + student.getSt_id() + "?updateSuccess=true");

				// student.setProfile_picture(file.getBytes());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				modelAndView
						.setViewName("redirect:/student/updatestudent/" + student.getSt_id() + "?updateSuccess=false");
				e.printStackTrace();
			}

		}
		return modelAndView;

	}

	@GetMapping(value = "/changePassword/{email}")
	public ModelAndView changePassword(@PathVariable("email") String email, HttpServletRequest httpServletRequest) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			User user = userService.getUserByEmail(email);
			UpdatePassword password = new UpdatePassword();
			password.setEmail(email);
			UserStudent userStudent = userStudentService.getUserStudentbyEmail(email);

			modelAndView.addObject("userPassword", password);
			modelAndView.addObject("user", userStudent);
			modelAndView.addObject("publicKey", RSAUtil.getPublicKey());

			modelAndView.setViewName("changePassword");
		} catch (Exception e) {
			// TODO: handle exception
			modelAndView.setViewName(httpServletRequest.getHeader("Referer") + "?InternalServerError");
		}
		return modelAndView;

	}

	@PostMapping(value = "/updatePassword")
	public ModelAndView updatePassword(@ModelAttribute UpdatePassword updatePassword) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			userService.updateUserPassword(updatePassword);
			modelAndView.setViewName("redirect:/changePassword/" + updatePassword.getEmail() + "?passwordUpdated=true");

			return modelAndView;
		} catch (Exception e) {
			modelAndView.setViewName("redirect:/changePassword/" + updatePassword.getEmail() + "?errorOccured=true");
			// TODO: handle exception
			return modelAndView;
		}
	}
}
