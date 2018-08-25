package com.example.mounika.hitam_try;


public class FeedbackObject {

    private String Profession;
    private String FeedbackType;
    private String PersonName;
    private String CompDesc;
    private Float Rating;

    public FeedbackObject() {

    }

    FeedbackObject(String profession, String feedbackType, String personName, String compDesc, Float rating) {
        Profession = profession;
        FeedbackType = feedbackType;
        PersonName = personName;
        CompDesc = compDesc;
        Rating = rating;
    }


    public String getProfession() {
        return Profession;
    }

    public String getFeedbackType() {
        return FeedbackType;
    }

    public String getPersonName() {
        return PersonName;
    }

    public String getCompDesc() {
        return CompDesc;
    }

    public Float getRating() {
        return Rating;
    }
}
