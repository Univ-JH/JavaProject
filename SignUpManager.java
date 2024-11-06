package DB;

import com.mongodb.client.*;
import org.bson.Document;
import javax.swing.*;
import java.util.ArrayList;

public class SignUpManager {
    private MongoClient mongoClient;
    private MongoCollection<Document> collection;
    private String loginID , userPwd, userEmail, userName, userBirth;

    public SignUpManager() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("testUser");
        collection = database.getCollection("userCollect");
    }

    public void setUserCredentials(String loginID , String userPwd, String userEmail, String userName, String userBirth) {
        this.loginID  = loginID ;
        this.userPwd = userPwd;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userBirth = userBirth;

        if(!checkId(loginID )){
            Document userDocument = new Document("loginID ", loginID )
                    .append("password", userPwd)
                    .append("email", userEmail)
                    .append("nickname", userName)
                    .append("birthdate", userBirth)
                    .append("userID", java.util.UUID.randomUUID().toString())
                    .append("chatRooms", new ArrayList<String>())
                    .append("active", true);

            collection.insertOne(userDocument); // 정보 db 삽입
            JOptionPane.showMessageDialog(null, "회원가입 완료.", "성공", JOptionPane.INFORMATION_MESSAGE);
        }
        else{//id 중복
            JOptionPane.showMessageDialog(null, "아이디가 중복됩니다.", "중복 오류", JOptionPane.ERROR_MESSAGE);
        }


    }

    public boolean checkId(String userId) {
        FindIterable<Document> users = collection.find(new Document("userId", userId));
        return users.iterator().hasNext(); //중복되면 true
    }
}
