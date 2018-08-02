package JsonServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import bean.Student;
import classandstudentes.ClassAndStudentsDao;

@WebServlet("/StudentsServlet")
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClassAndStudentsDao classAndStudentsDao = new ClassAndStudentsDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		List<Student> list = new ArrayList<Student>();

		String class_idString = request.getParameter("class_id");
		Integer class_id = Integer.parseInt(class_idString);

		list = classAndStudentsDao.findStudents(class_id);
		JSONArray jsonArray = new JSONArray(list);
		response.getWriter().print(jsonArray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
