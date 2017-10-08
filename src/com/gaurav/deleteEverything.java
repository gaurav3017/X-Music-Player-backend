package com.gaurav;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

@Path("/master")
public class deleteEverything {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/deleteAll")
	public Response deleteAllSongs(@Context HttpHeaders httpHeaders) throws IOException {
	
		 
		DB db = (new MongoClient("localhost",27017)).getDB("xmusicdb");
	    DBCollection dbCollection = db.getCollection("catalog");
	    dbCollection.drop();
	    String textOK = "done";
	    
	    FileUtils.cleanDirectory(new File(MusicUpload.MUSIC_UPLOAD_DIR + File.separator));
	    System.out.println("Wiped xmusic database and delete all files in: " + MusicUpload.MUSIC_UPLOAD_DIR + File.separator);
	    
	    //This line is causing error on client side
	    return Response.ok(textOK, MediaType.TEXT_PLAIN).header("Access-Control-Allow-Origin", "*").build();
	    
	    
	    //return Response.ok(serialize).header("Access-Control-Allow-Origin", "*").build();
	    //return Response.status(200).entity(serialize).build();
	}
	
	
}
