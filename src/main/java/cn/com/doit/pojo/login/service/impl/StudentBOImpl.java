//package cn.com.doit.pojo.login.service.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import cn.com.doit.pojo.login.student_info;
//import cn.com.doit.pojo.login.student_infoExample;
//import cn.com.doit.pojo.login.mapper.student_infoMapper;
//import cn.com.doit.pojo.login.service.StudentBO;
//
//@Component(value="studentBO")
//public class StudentBOImpl implements StudentBO{
//
//	@Autowired
//	private student_infoMapper student_infoMapper;
//	
//	public List<student_info> list(String name) {
//	
//		student_infoExample example = new student_infoExample();
//		if(!StringUtils.isEmpty(name)){
//			example.createCriteria().andNameLike("%"+name+"%");
//		}
//		
//		List<student_info> users = student_infoMapper.selectByExample(example);
//		return users;
//	}
//
//	public void add(student_info student) {		
//		student_infoMapper.insert(student);
//		
//	}
//
//	public void delete(Integer id) {
//		student_infoMapper.deleteByPrimaryKey(id);
//		
//	}
//
//	public void update(student_info student) {
//		student_infoMapper.updateByPrimaryKeySelective(student);
//	}
//
//}
