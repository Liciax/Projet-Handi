package handicacces;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class requestServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		URL url;
		try {
			
			String p = req.getParameter("p1");
			int nbtonextpage = Integer.parseInt(req.getParameter("start"));

			
			url = new URL("https://www.googleapis.com/customsearch/v1?key=AIzaSyBU2lW6SS9BAIu_1rqLSmkXztNfcG-7v9Q&cx=007840350058366591309:jlizzfc_tam&q="+ p + "&start=" + nbtonextpage);
            
			URLConnection connection = url.openConnection();
			String line;
			StringBuilder builder = new StringBuilder();
			InputStreamReader inp = new InputStreamReader(connection.getInputStream(), "UTF-8");
			BufferedReader reader = new BufferedReader(inp);
			while((line = reader.readLine()) != null) {
			 builder.append(line);
			}

			JSONObject json = null;
			try {
				json = new JSONObject(builder.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
				
//----------------------------------------------------------------------------------------------------------------------------------------------//				

			try {
                JSONArray newitem = new JSONArray();
                JSONObject valIni;
                
              URL url2 = new URL("https://handicacces.appspot.com/_ah/api/handicacces/v1/website");
              URLConnection connection2 = url2.openConnection();
              String line2;
              StringBuilder builder2 = new StringBuilder();
              BufferedReader reader2 = new BufferedReader(new InputStreamReader(connection2.getInputStream(), "UTF-8"));
              while((line2 = reader2.readLine()) != null) {
            	  builder2.append(line2);
              }
              JSONObject listeDesAmenagements = null;
              try {
                listeDesAmenagements = new JSONObject(builder2.toString());
              } catch (JSONException e) {
                  // TODO Auto-generated catch block
                  //e.printStackTrace();
              }

                for(int i = 0; i < json.getJSONArray("items").length(); i++)
                {
                  
                  valIni = (JSONObject) json.getJSONArray("items").get(i);

                  JSONObject jsonEnrichit = null;
                  JSONArray listeVide = new JSONArray();

                  int j = 0;
                  while(j < listeDesAmenagements.getJSONArray("items").length()) {
                    if(listeDesAmenagements.getJSONArray("items").getJSONObject(j).getString("url").compareTo(valIni.getString("displayLink")) ==0) {
                      jsonEnrichit = listeDesAmenagements.getJSONArray("items").getJSONObject(j);
                      j = listeDesAmenagements.getJSONArray("items").length() +2;
                    }
                    j++;
                  }
                  if(j == listeDesAmenagements.getJSONArray("items").length()) {
                    jsonEnrichit = new JSONObject();
                    jsonEnrichit.put("url", valIni.getString("displayLink"));
                    jsonEnrichit.put("layouts", listeVide);
                    }
                  valIni.put("resp", jsonEnrichit);
                  newitem.put(valIni);
                }
              //----------------------------------------------------------------------------------------------------------------------------------------------//  
                
				json.getJSONObject("responseData").remove("items");
				json.getJSONObject("responseData").append("items",newitem);
				
				
				//----------------------------------------------------------------------------
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		    
			out.print(json);

				
			
		} finally {
			out.close();
		}
}

	

	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		URL url;
		int nbtonextpage = 0;
		try {
			
			String p = req.getParameter("p1");
			
			url = new URL(
				    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
				    + "start="+ nbtonextpage + "&q=" +p + "&userip=USERS-IP-ADDRESS");
			for(int j = 0; j<1; j++ )
			{
				
				URLConnection connection = url.openConnection();
	
				String line;
				StringBuilder builder = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = reader.readLine()) != null) {
				 builder.append(line);
				}
	
				JSONObject json = null;
				try {
					json = new JSONObject(builder.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				out.print(json);
				
			}
			
		} finally {
			out.close();
		}
	}

}
