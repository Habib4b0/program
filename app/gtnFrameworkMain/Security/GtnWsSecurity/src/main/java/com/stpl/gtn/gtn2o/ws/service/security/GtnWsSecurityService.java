package com.stpl.gtn.gtn2o.ws.service.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constant.GtnWsQueryConstant;
import com.stpl.gtn.gtn2o.ws.db.GtnDatabaseConnection;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnWsSecurityService {

	private GtnWsSecurityToken gtnWsSecurityToken = null;

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsSecurityService.class);

	public GtnWsSecurityToken getSecurityToken(String userId, String sessionId) {

		GtnDatabaseConnection gtnDatabaseConnection = new GtnDatabaseConnection();

		ResultSet resultSetforselectRow = null;
		try (Connection connection = gtnDatabaseConnection.createConnection();
				PreparedStatement statement = connection.prepareStatement(GtnWsQueryConstant.SELECT_QUERY)) {
			statement.setString(1, sessionId);
			statement.setString(2, userId);
			resultSetforselectRow = statement.executeQuery();

			while (resultSetforselectRow.next()) {
				gtnWsSecurityToken = new GtnWsSecurityToken();
				gtnWsSecurityToken.setUserId(resultSetforselectRow.getString("USER_NAME"));
				gtnWsSecurityToken.setSessionId(resultSetforselectRow.getString("SESSION_ID"));
				gtnWsSecurityToken.setGtnToken(resultSetforselectRow.getString("OAUTH_TOKEN"));
				gtnWsSecurityToken.setCreationDate(resultSetforselectRow.getString("CREATED_DATE"));
			}
			return gtnWsSecurityToken;

		} catch (SQLException | NullPointerException e) {
			logger.error("Exception in Get Token SQL ", e);
		} finally {
			try {
				if (resultSetforselectRow != null) {
					resultSetforselectRow.close();
				}
			} catch (SQLException e) {
				logger.error("Exception in Closing Connection ", e);
			}
		}
		return null;
	}

	public boolean insertSecurityToken(GtnWsSecurityToken tokenObj) {
		GtnDatabaseConnection gtnDatabaseConnection = new GtnDatabaseConnection();

		try (Connection connection = gtnDatabaseConnection.createConnection();
				PreparedStatement stmt = connection.prepareStatement(GtnWsQueryConstant.INSERT_QUERY)) {
			stmt.setString(1, tokenObj.getUserId());
			stmt.setString(2, tokenObj.getSessionId());
			stmt.setString(3, tokenObj.getGtnToken());
			stmt.executeUpdate();

			return true;
		} catch (SQLException | NullPointerException e) {
			logger.error("Exception in Insert Token SQL ", e);
			return false;
		}

	}

	public void deleteSecurityToken(String userId, String sessionId) {
		GtnDatabaseConnection gtnDatabaseConnection = new GtnDatabaseConnection();

		try (Connection connection = gtnDatabaseConnection.createConnection();
				PreparedStatement statement = connection.prepareStatement(GtnWsQueryConstant.DELETE_QUERY)) {
			statement.setString(1, sessionId);
			statement.setString(2, userId);
			statement.execute();
		} catch (SQLException | NullPointerException e) {
			logger.error("Exception in Deleting Token SQL ", e);
		}

	}

	public long getTimeDifferenceInMilliSecond(String toDate) {

		try {
			DateFormat dateFormat = new SimpleDateFormat(GtnWsQueryConstant.DATE_FORMAT);
			Date retriveddatefromdb = null;
			Date currentdate = null;
			currentdate = new Date();
			retriveddatefromdb = dateFormat.parse(toDate);
			return currentdate.getTime() - retriveddatefromdb.getTime();
		} catch (ParseException e) {
			logger.error("Unable to parse date  ", e);
		}
		return 0;

	}

}
