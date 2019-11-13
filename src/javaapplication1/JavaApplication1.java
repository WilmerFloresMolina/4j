/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;


import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.Node;


/**
 *
 * @author Wilmer
 */
public class JavaApplication1 {

    public enum NodeType implements Label{
    Person,Course;
}
    
    public enum RelationType implements RelationshipType{
        Knows,BelongsTo;
    }
   
 
   
    public static void main(String[] args) {
        // TODO code application logic here
       GraphDatabaseFactory dbFactory = new GraphDatabaseFactory() ;
       GraphDatabaseService graphDb = dbFactory.newEmbeddedDatabase(".graph.db");
       
        try(Transaction tx = graphDb.beginTx()) {
            Node bobNode = graphDb.createNode(NodeType.Person);
            bobNode.setProperty("PID", 5001);
            bobNode.setProperty("Name", "Bob");
            bobNode.setProperty("Age", 23);
            
            Node AliceNode = graphDb.createNode(NodeType.Person);
            AliceNode.setProperty("PID", 5003);
            AliceNode.setProperty("Name", "Alice");
            AliceNode.setProperty("Age", 20);
            
            Node eveNode = graphDb.createNode(NodeType.Person);
            eveNode.setProperty("PID", 5004);
            eveNode.setProperty("Name", "eve");
            eveNode.setProperty("Age", 28);
            
            Node itNode = graphDb.createNode(NodeType.Person);
            itNode.setProperty("PID", 5005);
            itNode.setProperty("Name", "it");
            itNode.setProperty("Age", 25);
            
            bobNode.createRelationshipTo(AliceNode, RelationType.Knows);
            
            Relationship bobRelIt = bobNode.createRelationshipTo(itNode, RelationType.BelongsTo);
            bobRelIt.setProperty("Function", "Student");
            
            
            Relationship AliceRelIt = bobNode.createRelationshipTo(itNode, RelationType.BelongsTo);
            bobRelIt.setProperty("Function", "Teacher");
            
            
            tx.success();
            
        } catch (Exception e) {
            System.out.println("Error");
        }
        graphDb.shutdown();
    }
    
    
    
}
