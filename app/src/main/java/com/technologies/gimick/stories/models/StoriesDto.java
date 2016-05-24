package com.technologies.gimick.stories.models;

/*
This is data model class for stories. This class is act like personal data type and only used for
variable declaration

 */

import java.io.Serializable;


public class StoriesDto implements Serializable {
    public String title;
    public String story;
    public String image_url;
    public String description;
    public String l_image_url;
}
