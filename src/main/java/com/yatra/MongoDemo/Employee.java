package com.yatra.MongoDemo;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

/**
 * Hello world!
 *
 */
public class Employee {
	private static Scanner scanner=new Scanner(System.in);
	private  static  MongoCollection<Document> collection=null;
	public static void getDbConnection() {
		try {
			@SuppressWarnings("resource")
			MongoClient client=new MongoClient("localhost",27017);
			MongoDatabase database=client.getDatabase("Employee");
			collection = database.getCollection("EmployeeDetails");
		} catch (Exception e) {
			System.out.println("Connection is not created ");
			return ;
		}
	}
	public static void main( String[] args ) {

		getDbConnection();
		//Scanner scanner=new Scanner(System.in);
		boolean isContinue=true;
		while(isContinue) {
			
			System.out.println(" 1.Add Employee \n 2. Get All Employees \n 3. Update Employee \n 4. Delete Employee \n 5. Get Employee By Id ");
			System.out.println("choose choice: ");
			int choice=scanner.nextInt();

			switch(choice) {
					
					case 1:addEmployee();
							break;
					
					case 2: getAllEMployees();
							break;
					
					case 3:updateEmployee();
							break;
					
					case 4:deleteEmployee();
							break;
					case 5:getEmployeeById();
							break;
					default: System.out.println("Wrong choice");
							break;

			}
			
			System.out.println("you want continue: press y other wise press any key :");
			String status=scanner.next();
			if (!status.toLowerCase().equals("y")) {
				isContinue=false;
			}
			}
		scanner.close();
		collection=null;

		
		
		
		//Scanner scanner=null;
		/*Document object=null;
		MongoCollection<Document> collection=null;
		MongoCursor<Document> cursor=null;
		try {
			scanner=new Scanner(System.in);
			System.out.println("Enter employee id");
			int empId=scanner.nextInt();
			System.out.println("Enter employee name");
			String name=scanner.next();

			object=new Document();
			object.put("Name", name);
			object.put("EmpId", empId);
			collection=getColl();

			collection.insertOne(object);
			System.out.println("Employee inserted");
*/


			/* Document object1=new Document();
	      object1.put("EmpId", 5);
	      FindIterable<Document> data= collection.find(object1);
	      while ( ((Iterator<DBObject>) data).hasNext()) {
	    	    System.out.println( ((Iterator<DBObject>) data).next());
	    	}*/
			/*DBObject query = BasicDBObjectBuilder.start().add("EmpId", 5).get();
			FindIterable<Document> cursor = collection.find();
			while(((Iterator<DBObject>) cursor).hasNext()){
				System.out.println(((Iterator<DBObject>) cursor).next());
			}*/


			/*cursor =  collection.find().iterator();
			while (cursor.hasNext()) {

				System.out.println("Welcome "+cursor.next().getString("Name"));

			}
*/

			/* Document myDoc = collection.find(eq("EmpId", 5)).first();
	       System.out.println(myDoc.toJson());
			 */
		
	}
	private static void getEmployeeById() {
		System.out.println("Enter employee id want to update : ");
		int empId=scanner.nextInt();
		BasicDBObject searchQuery = new BasicDBObject().append("EmpId", empId);

		FindIterable<Document> cursor=collection.find(searchQuery);
		MongoCursor<Document> mongoCursor=cursor.iterator();
		
		mongoCursor.forEachRemaining(doc->System.out.println("Welcome :"+doc.getString("Name")
		+" with id of : "+doc.getInteger("EmpId")));
/*		cursor.forEachRemaining(doc->System.out.println("Welcome :"+doc.getString("Name")
		+" with id of : "+doc.getInteger("EmpId")));
*/		
	}
	private static void deleteEmployee() {
		//collection.deleteOne( { EmpId : { $eq : 5 } });
		System.out.println("Enter employee id want to update : ");
		int empId=scanner.nextInt();
		BasicDBObject searchQuery = new BasicDBObject().append("EmpId", empId);

		DeleteResult result=collection.deleteOne(searchQuery);
		
	}
	private static void updateEmployee() {
		
		System.out.println("Enter employee id want to update : ");
		int empId=scanner.nextInt();
		System.out.println("Enter employee new name: ");
		String empName=scanner.next();
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append("Name", empName));

		BasicDBObject searchQuery = new BasicDBObject().append("EmpId", empId);

		collection.updateOne(searchQuery, newDocument);
		
		/*collection.updateOne(
			    // query 
			    {
			        "EmpId" : 7

			    },
			    
			    // update 
			    {
			        $set:{"Name":"HemanKR"}
			    },
			    
			    // options 
			    {
			        "multi" : false,  // update only one document 
			        "upsert" : false  // insert a new document, if no existing document match the query 
			    }
			);
*/		/*collection.updateOne(
				{
					"EmpId":5
				},
				{
					$set:{
					"Name":"Hemanth"}
				}, 
			{
	        "multi" : false,  // update only one document 
	        "upsert" : false  // insert a new document, if no existing document match the query 
	    });*/
	}
	private static void getAllEMployees() {
		MongoCursor<Document> cursor=null;
		
		
		try {
			cursor =  collection.find().iterator();
			

			cursor.forEachRemaining(doc->System.out.println("Welcome : "+doc.getString("Name")+"with id of :"+doc.getInteger("EmpId")));
			
			/*Document doc=null;
			while (cursor.hasNext()) {
				 doc=cursor.next();
			System.out.println("Welcome "+doc.getString("Name") +" with id of :"+doc.getInteger("EmpId"));

			}
*/					
		
			} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occuered");
		} 
		
	}
	
	private static void addEmployee() {
		Document object=null;
		try {
			scanner=new Scanner(System.in);
			System.out.println("Enter employee id");
			int empId=scanner.nextInt();
			System.out.println("Enter employee name");
			String name=scanner.next();

			object=new Document();
			object.put("Name", name);
			object.put("EmpId", empId);

			collection.insertOne(object);
			System.out.println("Employee inserted");
		
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Error occuered");
	} 	}
	
}
