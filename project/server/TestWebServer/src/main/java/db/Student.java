package db;

import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName("id")
    private Integer mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("group")
    private String mGroup;

    @SerializedName("score")
    private Integer mScore;

    public Student(Integer id, String name, String group, int score) {
        mId = id;
        mName = name;
        mGroup = group;
        mScore = score;
    }

    public Integer getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getGroup() {
        return mGroup;
    }

    public Integer getScore() {
        return mScore;
    }
}
