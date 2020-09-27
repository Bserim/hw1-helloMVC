package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Customer;
import service.CustomerService;

/**
 * Servlet implementation class DoRegister
 */
@WebServlet("/doRegister")
public class DoRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CustomerService service = (CustomerService) CustomerService.getInstance();


		String page = "";
		
		String id = request.getParameter("id");
		String password = request.getParameter("id");
		
		
		if(id.indexOf(" ") != -1 || password.indexOf(" ") != -1) {
			page="/view/error.jsp";
		}


		if(id.isEmpty() || password.isEmpty()) {
			page="/view/error.jsp";
		}
			
		if(page.isEmpty()) {

			Customer customer = new Customer(request.getParameter("id")
					,request.getParameter("password")
					,request.getParameter("name")
					,request.getParameter("gender")
					,request.getParameter("email")
					);
			
			service.addCustomer(customer);
	        request.setAttribute("customer", customer);
	        
			page="/view/registerSuccess.jsp";
		}
			
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

}
