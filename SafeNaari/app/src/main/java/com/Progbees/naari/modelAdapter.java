package com.Progbees.naari;

import java.util.PrimitiveIterator;

public class modelAdapter {

   private String postDetails, postPic, PostTitle;

    public modelAdapter() {
    }

    public modelAdapter(String postDetails, String postPic, String postTitle) {
        this.postDetails = postDetails;
        this.postPic = postPic;
        PostTitle = postTitle;
    }

    public String getPostDetails() {
        return postDetails;
    }

    public void setPostDetails(String postDetails) {
        this.postDetails = postDetails;
    }

    public String getPostPic() {
        return postPic;
    }

    public void setPostPic(String postPic) {
        this.postPic = postPic;
    }

    public String getPostTitle() {
        return PostTitle;
    }

    public void setPostTitle(String postTitle) {
        PostTitle = postTitle;
    }
}
