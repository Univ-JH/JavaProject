package DB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private MongoDatabase database;

    public MongoDBConnection(String dbName) {
        // MongoClients.create()를 사용하여 MongoDB 연결 생성
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");  // MongoDB 호스트와 포트 설정
        this.database = mongoClient.getDatabase(dbName);
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
