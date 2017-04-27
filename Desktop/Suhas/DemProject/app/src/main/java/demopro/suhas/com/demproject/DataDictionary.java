package demopro.suhas.com.demproject;

import java.io.Serializable;

/**
 * Created by suhasvijay on 27/04/2017.
 */
public class DataDictionary implements Serializable{

    public DataDictionary() {

    }
    private String commitID, authorName,commitMessage,thumbnailUrl;

    public DataDictionary(String commitID, String AuthorName, String thumbnailUrl, String commitMessage) {
        this.commitID = commitID;
        this.authorName = AuthorName;
        this.commitMessage = commitMessage;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getCommitID() {
        return commitID;
    }

    public void setCommitID(String commitID) {
        this.commitID = commitID;
    }

    public  String getAuthorName(){
        return authorName;
    }

    public  void  setAuthorName(String authorName){
        this.authorName = authorName;
    }

    public String getCommitMessage(){
        return commitMessage;
    }
    public  void  setCommitMessage(String commitMessage){
        this.commitMessage = commitMessage;
    }
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


}

