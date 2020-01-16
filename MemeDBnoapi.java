import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.ArrayList;

public class MemeDB
{
	private Connection connect = null;
	private Statement stmt = null;
	private PreparedStatement prepstmt = null;
	private ResultSet resultSet = null;
	private ArrayList<String> memeList;
	private Iterator<String> memeIter;
	
	public MemeDB() throws Exception
	{
		memeList = new ArrayList<String>();
		
		try
		{
			//load MySQL driver
			Class.forName("com.mysql.cj.jdbc.Driver");
	
			//setup connection w/ db
			connect = DriverManager.getConnection("jdbc:mysql://memedb.cxpr1cju3gmf.us-east-2.rds.amazonaws.com/memeproject",
				"appuser", "1234");
			
			//get meme URLs
			stmt = connect.createStatement();
			resultSet = stmt.executeQuery("SELECT img_url FROM meme");
			
			//add meme URLs to memeList
			while(resultSet.next())
				memeList.add(resultSet.getString(1));
		}
		catch(Exception e) {throw e;}
		
		memeIter = memeList.iterator();
	}

	
	//returns an image url for a meme
	public String nextMeme()
	{
		return memeIter.next();
	}
	
	//check if meme iterator has more memes
	public boolean hasNext()
	{
		return memeIter.hasNext();
	}
	
	//reset the meme iterator
	public void resetIter()
	{
		memeIter = memeList.iterator();
	}
	
	//close db connection
	public void close()
	{
		try
		{
			if(resultSet != null)
				resultSet.close();
			if(stmt != null)
				stmt.close();
			if(prepstmt != null)
				prepstmt.close();
			if(connect != null)
				connect.close();
		}
		catch(Exception e) {}
	}
}