package io.dandan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.dandan.dao.DataElementConfigTool;
import io.dandan.entity.TableRecord;

@WebServlet(urlPatterns="/show/sql")
public class SqlServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();  
	    ServletContext context =getServletContext();  
	    List<TableRecord> tables =  DataElementConfigTool.dao();
        req.setAttribute("tables", tables); 
        req.getRequestDispatcher("/sql.jsp").forward(req, resp);
	}

}
