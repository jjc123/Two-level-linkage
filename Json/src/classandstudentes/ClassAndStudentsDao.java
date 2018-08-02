package classandstudentes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Class;
import bean.Student;
import jdbcUtils.JdbcUtils;

public class ClassAndStudentsDao {
	private Connection connection;
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;

	public List<Class> findClasses() {
		List<Class> list = new ArrayList<Class>();
		Class newClass = null;
		String sql = "SELECT *FROM classes";
		try {
			connection = JdbcUtils.getConnection();
			prepareStatement = connection.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				newClass = new Class();
				newClass.setId(resultSet.getInt("id"));
				newClass.setName(resultSet.getString("name"));
				list.add(newClass);
			}
			JdbcUtils.close(connection, prepareStatement, resultSet);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public List<Student> findStudents(int id) {
		List<Student> list = new ArrayList<Student>();
		Student newStudent = null;
		String sql = "SELECT *FROM students WHERE class_id=?";
		try {
			connection = JdbcUtils.getConnection();
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				newStudent = new Student();
				newStudent.setId(resultSet.getInt("id"));
				newStudent.setClass_id(resultSet.getInt("class_id"));
				newStudent.setName(resultSet.getString("name"));
				list.add(newStudent);
			}
			JdbcUtils.close(connection, prepareStatement, resultSet);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
