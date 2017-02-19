//package cn.com.doit.bo.login.service;
//
//import static org.quartz.CronScheduleBuilder.cronSchedule;
//import static org.quartz.TriggerBuilder.newTrigger;
//
//import java.util.List;
//
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.TriggerKey;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import cn.com.doit.pojo.login.student_info;
////import cn.com.bsfit.doit.z.test.schedule.TestScheduleDongTai;
//import cn.com.doit.pojo.login.service.StudentBO;
//import cn.com.doit.util.ExtJSResponse;
//import cn.com.doit.util.ModelToView;
//
//
//
//@Controller
//@RequestMapping("/student")
//@CrossOrigin
//public class StudentService {
//
//	
//	@Autowired
//	@Qualifier(value="studentBO")
//	private StudentBO studentBO;
//	
//	@Autowired
//	private Scheduler scheduler;
//	
//	/**
//	 * 
//	 *
//	 * @author hong
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/list")
//	@ResponseBody
//	public ExtJSResponse list(String userName){
//	//-----娴嬭瘯spring mvc鑷甫鐨剆chedule鍔ㄦ�侀厤缃�
////		TestScheduleDongTai bb=new TestScheduleDongTai();
////		bb.myUpdateTest();
//		//娴嬭瘯Quartz鍔ㄦ�侀厤缃�
////		try {
////			scheduler.rescheduleJob(new TriggerKey("TestTriggerOne", "group1"), 
////					  newTrigger()
////				    .withIdentity("newTriggerOne", "group1")
////				    .withSchedule(cronSchedule("0/20 * * * * ?"))
////				    .build()
////				    );
////		} catch (SchedulerException e) {
////			e.printStackTrace();
////		}
//		List<student_info> users = studentBO.list(userName);
//		ExtJSResponse c=ExtJSResponse.successRes4Find(users, users.size());
//		return ExtJSResponse.successRes4Find(users, users.size());
//	}
//	
//	@RequestMapping("/tolist.pdf")
//	public String tolist(Model model){
//		System.out.println("=============================");
//		List<student_info> users = studentBO.list("");
//		model.addAttribute("whichsToShow", users);
//		return "student";
//		
//	}
//	
//	/**
//	 * 
//	 *
//	 * @author hong
//	 * @param user
//	 * @return
//	 */
//	@RequestMapping(value="/add",method={RequestMethod.POST})
//	@ResponseBody
//	public ExtJSResponse add(@RequestBody student_info user){
//		
//		studentBO.add(user);
//		return ExtJSResponse.success();
//	}
//	
//	@RequestMapping("/delete")
//	@ResponseBody
//	public ExtJSResponse dlete(@RequestBody student_info user){
////		System.out.println("______======"+id.substring(0, id.length()));
////		studentBO.delete(Integer.valueOf(id.substring(0, id.length())));
//		studentBO.delete(user.getId());
//		return ExtJSResponse.success();
//	}
//	
//	@RequestMapping("/update")
//	@ResponseBody
//	public ExtJSResponse update(@RequestBody student_info user){
//   System.out.println("==================");
//		studentBO.update(user);
//		return ExtJSResponse.success();
//	}
//
//	public StudentBO getStudentBO() {
//		return studentBO;
//	}
//
//	public void setStudentBO(StudentBO studentBO) {
//		this.studentBO = studentBO;
//	}
//
//	public Scheduler getScheduler() {
//		return scheduler;
//	}
//
//	public void setScheduler(Scheduler scheduler) {
//		this.scheduler = scheduler;
//	}
//
//}
