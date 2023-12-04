package com.Progbees.naari;

public class postHelper {
    String PostTitle,PostDetails,postPic;

    public postHelper(String postTitle, String postDetails, String postPic) {
        PostTitle = postTitle;
        PostDetails = postDetails;
        this.postPic = postPic;
    }

    public String getPostTitle() {
        return PostTitle;
    }

    public void setPostTitle(String postTitle) {
        PostTitle = postTitle;
    }

    public String getPostDetails() {
        return PostDetails;
    }

    public void setPostDetails(String postDetails) {
        PostDetails = postDetails;
    }

    public String getPostPic() {
        return postPic;
    }

    public void setPostPic(String postPic) {
        this.postPic = postPic;
    }
}
