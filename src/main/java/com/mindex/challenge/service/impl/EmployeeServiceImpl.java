package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Node;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.BreadthFirstSearchAlgorithm;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
    
    public void buildSubTree(Node root,int i) {

    	Employee employee = employeeRepository.findByEmployeeId(root.getData());
    	List<Employee> list = employee.getDirectReports();
    	if(list != null) {
	    	for(Employee emp:list) {
	    		Node child = new Node(emp.getEmployeeId());
	    		buildSubTree(child,i+1);
	    		root.addChild(child);
	    	}
    	}
    	
    }
    
    @Override
    public ReportingStructure getReportingStructure(String id) {
    	
    	
    	Employee employee = employeeRepository.findByEmployeeId(id);
    	
    	Node root = new Node(employee.getEmployeeId());
    	buildSubTree(root,0);
    	
    	BreadthFirstSearchAlgorithm bfsExample = new BreadthFirstSearchAlgorithm();
    	
        int count = bfsExample.bfs(root);   
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployeeId(id);
        reportingStructure.setNumberOfReports(count);
        return reportingStructure;
    	
    }
}
