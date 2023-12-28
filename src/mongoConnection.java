import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings("unused")
public class mongoConnection {
	
    private MongoClient mongoClient = null;
    private MongoDatabase database = null;
    private MongoCollection<Document> collection = null;
    
    
	
	public void connect()
	{
			String url = "your mongo db connection string here";
	    	try {
	    		mongoClient = MongoClients.create(url);
	    		database = mongoClient.getDatabase("Cluster0");
	    		collection = database.getCollection("sales");
	    		
	    	}
	    	catch(MongoException e1){
	    		e1.printStackTrace();
	    	}
		
	}
	
	
	
	
    
    public boolean isConnected() {
        try {
            // Ping the server to check if the connection is alive
            mongoClient.getDatabase("admin").runCommand(new Document("ping", 1));
            return true;
        } catch (MongoException e) {
            // Handle the exception
            return false;
        } catch (Exception e) {
            // Handle the exception
            return false;
        }
    }

    public MongoDatabase getDatabase()
    {
        return database;
    }
    
    public MongoCollection<Document> getCollection()
    {
        return collection;
    }

    public void close() 
    {
    		try {
				// Close the MongoClient
				mongoClient.close();
			} catch (NullPointerException e) {
				
			}		
    }

    public void insert(Document document)
    {
        // Insert the document into the collection
        collection.insertOne(document);
    }
	
}