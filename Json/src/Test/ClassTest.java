package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import bean.Class;
import bean.Student;
import jdbcUtils.JdbcUtils;

class ClassTest {
	private Connection connection;
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	@Test
	void test() {
		List<Student> list = new ArrayList<Student>();
		Student newStudent = null;
		String sql = "SELECT *FROM students WHERE class_id=?";
		try {
			connection = JdbcUtils.getConnection();
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, 4);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				newStudent = new Student();
				newStudent.setId(resultSet.getInt("id"));
				newStudent.setClass_id(resultSet.getInt("class_id"));
				newStudent.setName(resultSet.getString("name"));
				list.add(newStudent);
			}
			System.out.println(list.size());
			JdbcUtils.close(connection, prepareStatement, resultSet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
