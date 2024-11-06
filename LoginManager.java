package DB;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;

public class LoginManager {
    private MongoClient mongoClient;
    private MongoCollection<Document> collection;
    private String loginID ;
    private String userPwd;

    public LoginManager() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("testUser");
        collection = database.getCollection("userCollect");
    }

    public void setUserCredentials(String loginID , String userPwd) {
        this.loginID  = loginID ;
        this.userPwd = userPwd;
    }

    public void checkLogin() {
        FindIterable<Document> users = collection.find(new Document("loginID ", loginID ));

        if (users.iterator().hasNext()) {
            for (Document user : users) {
                String storedPassword = user.getString("password");

                if (storedPassword.equals(userPwd)) {
                    JOptionPane.showMessageDialog(null, "로그인 성공");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
                    return;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "해당 ID가 존재하지 않습니다: " + loginID );
        }
    }

    public void close() {
        mongoClient.close();
    }
}

