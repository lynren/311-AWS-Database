package com.meme.db.getmeme;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.google.gson.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class getmeme implements RequestStreamHandler {

	private HikariConfig cfg;
	private ResultSet resultSet = null;
	private ArrayList<String> memeList;
	public JSONParser parser = new JSONParser();
	
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) 
    {
    	LambdaLogger logger = context.getLogger();
    	logger.log("populating meme list");
    	try { populateMemeList(); } 
    	catch (Exception e) { e.printStackTrace(); }
    	
    	JSONObject resp = new JSONObject();
    	String respCode = "200";
    	resp.put("isBase64Encoded", false);
    	resp.put("statusCode", respCode);
    	resp.put("headers", null);
    	resp.put("body", memeList.toString());
    	logger.log("returning json");
    	logger.log(resp.toJSONString());
    	
    	
    	try {
    	OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
    	writer.write(resp.toJSONString());
    	writer.close();}
    	catch(Exception e) { e.printStackTrace();}
    	
    	
    }
    
    public void populateMemeList() throws Exception
    {
    	cfg = new HikariConfig();
		memeList = new ArrayList<String>();
		cfg.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //Database no longer exists
		cfg.setJdbcUrl("jdbc:mysql://memedb.cxpr1cju3gmf.us-east-2.rds.amazonaws.com/memeproject");
		cfg.setUsername("appuser");
		cfg.setPassword("1234");
		
		try (HikariDataSource pool = new HikariDataSource(cfg); Connection connect = pool.getConnection();
			Statement stmt = connect.createStatement())
		{
			stmt.executeQuery("SELECT img_url FROM meme");
			resultSet = stmt.getResultSet();
			while(resultSet.next())
				memeList.add(resultSet.getString(1));
		}
		catch(Exception e) { e.printStackTrace(); }
		//add meme URLs to memeList
    }
}
