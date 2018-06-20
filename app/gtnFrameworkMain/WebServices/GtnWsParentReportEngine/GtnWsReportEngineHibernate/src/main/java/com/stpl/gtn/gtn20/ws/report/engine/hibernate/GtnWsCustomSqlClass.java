/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn20.ws.report.engine.hibernate;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class GtnWsCustomSqlClass {

	private static SessionFactory sessionFactoryObj = buildSessionFactory();
	static Session sessionObj;
	private static GtnWsCustomSqlClass INSTANCE = null;

	private GtnWsCustomSqlClass() {
		super();
	}

	public static GtnWsCustomSqlClass getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GtnWsCustomSqlClass();
		}
		return INSTANCE;
	}

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration configObj = new Configuration();
			// configObj.configure("hibernate.cfg.xml");
			configObj.configure();
			// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
			ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
					.applySettings(configObj.getProperties()).build();
			// Creating Hibernate SessionFactory Instance
			sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);

			return sessionFactoryObj;
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void shutdown() {
		// Close caches and connection pools
		sessionFactoryObj.close();
	}

	public List executeQuery(String query) {
		List executeList = null;
		try {
			sessionObj = sessionFactoryObj.openSession();
			sessionObj.beginTransaction();
			SQLQuery sqlQuery = sessionObj.createSQLQuery(query);
			executeList = sqlQuery.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			sessionObj.getTransaction().rollback();
		} finally {
			sessionObj.close();
		}
		return executeList;
	}

	// public static void main(String[] args) {
	// String query = "Select * from helper_table";
	// List finalList = new CustomSqlClass().executeQuery(query);
	// System.out.println(" list size====" + finalList.size());
	// }
	public static void main(String[] args) {
		String query = "Select * from helper_table";
		List finalList = new GtnWsCustomSqlClass().executeQuery(query);
		System.out.println(" list size====" + finalList.size());
	}

}
