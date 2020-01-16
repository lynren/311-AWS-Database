import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

public class MemeDB
{
	private Iterator<String> memeIter;
	private List<String> memeList;
	
	public MemeDB() throws IOException
	{
		String urls = readJsonFromUrl();
		memeList = Arrays.asList(urls.substring(1, urls.length()-1).split(", "));
		memeIter = memeList.iterator();
	}
	
	//returns URL for meme image
	public String nextMeme()
	{
		return memeIter.next();
	}
	
	//check if meme iterator has more elements
	public boolean hasNext()
	{
		return memeIter.hasNext();
	}
	
	//reset the meme iterator to the first meme
	public void resetIter()
	{
		memeIter = memeList.iterator();
	}
	
	//retrieves all meme URLs from database 
	private String readJsonFromUrl() throws IOException 
	{
		BufferedReader rd = null;
		try
		{
        //api is no longer active
		URL url = new URL("https://qzm4kaqipi.execute-api.us-east-1.amazonaws.com/default/getMeme2");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
	    String jsonText = readAll(rd);
	    return jsonText;
		} 
		finally { if(rd != null) rd.close();}
  	}	
	
	private String readAll(Reader rd) throws IOException 
	{
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1)
	      sb.append((char) cp);
	    return sb.toString();
	}
	
}
