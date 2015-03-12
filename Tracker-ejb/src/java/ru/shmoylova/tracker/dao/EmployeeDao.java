package ru.shmoylova.tracker.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import ru.shmoylova.tracker.entity.Department;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.entity.Role;
import ru.shmoylova.tracker.interfaces.dao.IEmployeeDao;
import ru.shmoylova.tracker.util.GenericDaoHibernateImpl;

/**
 *
 * @author Ksiona
 */
public class EmployeeDao extends GenericDaoHibernateImpl<Employee> implements IEmployeeDao {

    private static final String PARAMETR_USER_NAME = "userName";
    //TODO find another way
    private static final String SQL_QUERY_LOGIN_CHECK = "Select * from tracker.dbo.employee where login = :userName And pass = CONVERT(varchar(max),  HashBytes('MD5', '";

    public EmployeeDao(SessionFactory factory) {
        super(factory);
    }

    @Override
    public List<Employee> find(String... searchTerm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> find(Department dept) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> find(Department dept, String jobTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> find(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee loginRequest(String login, String pass) {
        return (Employee) getSession().createSQLQuery(SQL_QUERY_LOGIN_CHECK + pass + "'))")//TODO: This is a point for 
                .addEntity(Employee.class)                                              //an SQL-injection attack. Always use prepared statements.
                //   .setString("userPass",  pass) 
                .setString(PARAMETR_USER_NAME, login.trim()).uniqueResult();
    }

}
